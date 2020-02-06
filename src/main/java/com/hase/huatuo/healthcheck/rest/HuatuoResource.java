package com.hase.huatuo.healthcheck.rest;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.hase.huatuo.healthcheck.model.SMSInfo;
import com.hase.huatuo.healthcheck.model.UserInfo;
import com.hase.huatuo.healthcheck.model.VpnInfo;
import com.hase.huatuo.healthcheck.model.request.*;
import com.hase.huatuo.healthcheck.model.response.*;
import com.hase.huatuo.healthcheck.service.*;
import com.hase.huatuo.healthcheck.utils.DateUtils;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hase.huatuo.healthcheck.helper.ErrorHandleHelper;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping({"/api","/api/v1"})
public class HuatuoResource {


	@Autowired
    private HuatuoHealthService huatuoHealthService;

    @Autowired
    private HuatuoVPNService huatuoVPNService;

    @Autowired
    private HealthReportService healthReportService;

    @Autowired
    private HuatuoRegistrationService huatuoRegistrationService;

    @Autowired
    private HuatuoWechatService huatuoWechatService;

    @PostMapping("/health")
    public HealthPostResponse updateHealth(@RequestBody final HealthPostBody healthPostBody) {
    	validHealthRequest(healthPostBody);
    	
    	return huatuoHealthService.setPersonHealth(healthPostBody);
    }
    
    @GetMapping("/health")
    public ResponseEntity<List<AreaReport>> requestHealth(@RequestHeader("X-IS-DUMMY") String isDummy) {
    	return healthReportService.enquiry(isDummy);
    }
    
    @PostMapping("/vpn")
    @ApiOperation(value = "vpnState", notes = "Save or update staff VPN state", httpMethod = "POST")
    public ResponseEntity submitVPNState(@RequestBody final VpnRequest vpnRequest) {
        huatuoVPNService.submitVPNState(vpnRequest);
    	return ResponseEntity.ok(null);
    }

    @PostMapping("/vpn/report")
    @ApiOperation(value = "vpnReport", notes = "Index VPN status report", httpMethod = "POST")
    public ResponseEntity<VpnReportResponse> loadVPNStateDashboard(@RequestBody final VpnReportRequest vpnReportRequest) throws ParseException {
        return ResponseEntity.ok(new VpnReportResponse(huatuoVPNService.loadVPNStateDashboard(vpnReportRequest)));
    }

    @GetMapping("/vpn/reportQuery")
    @ApiOperation(value = "vpnReportView", notes = "Index VPN status report", httpMethod = "GET")
    public ResponseEntity<VpnReportViewResponse> queryVPNReport(@RequestParam Map<String,Object> map) throws ParseException {
        VpnReportQueryRequest vpnReportQueryRequest = new VpnReportQueryRequest();
        Object staffId = map.get("staffId");
        Object location = map.get("location");
        Object internetISP = map.get("internetISP");
        Object lastUpatetime = map.get("lastUpatetime");
        if (staffId != null && !"".equals(staffId)) {
            vpnReportQueryRequest.setStaffId(Integer.parseInt(String.valueOf(staffId)));
        }
        if (location != null && !"".equals(location)) {
            vpnReportQueryRequest.setLocation("%"+String.valueOf(location)+"%");
        }
        if (internetISP != null && !"".equals(internetISP)) {
            vpnReportQueryRequest.setInternetISP(String.valueOf(internetISP));
        }
        if (lastUpatetime != null && !"".equals(lastUpatetime)) {
            vpnReportQueryRequest.setLastUpatetime(String.valueOf(lastUpatetime+"%"));
        }
        List<VpnInfo> vpnInfoList = huatuoVPNService.queryVPNReport(vpnReportQueryRequest);
        VpnReportViewResponse response = new VpnReportViewResponse();
        response.setItems(vpnInfoList);
        response.setTotal(vpnInfoList.size());
        response.setCode(20000);
        return ResponseEntity.ok(response);
    }
    
    @GetMapping("/datadict")
    @ApiOperation(value = "datadict", notes = "Get Datadict From API", httpMethod = "GET")
    public ResponseEntity<DatadictGetResponse> getDatadict() throws ParseException {
		return ResponseEntity.ok(new DatadictGetResponse());
    }

    @PostMapping("/sendSMSVerifyCode")
    @ApiOperation(value = "sendSMSVerifyCode", notes = "send SMS verify code", httpMethod = "POST")
    public ResponseEntity<CommonResponse> sendSMSVerifyCode(@RequestBody final SMSInfo smsInfo) throws ParseException {
        return huatuoRegistrationService.sendVerifyCode(smsInfo);
    }

    @PostMapping("/register")
    @ApiOperation(value = "register", notes = "register user", httpMethod = "POST")
    public ResponseEntity<CommonResponse> registration(@RequestBody final RegistrationPostBody registerBody) throws ParseException {
        huatuoRegistrationService.validRegisterRequest(registerBody);
        return huatuoRegistrationService.register(registerBody);
    }

    private void validHealthRequest(HealthPostBody healthPostBody) {
    	if(healthPostBody == null ) {
    		ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "request body is null", null);
    	}
    	
    	if(healthPostBody.getStaffId() == null ||
    			healthPostBody.getStaffId().length() < 5) {
    		ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "staff ID is error", null);
    	}
    	
    	// other field checking
    		
    }

    @PostMapping("/wechat-login")
    @ApiOperation(value = "wechat-login", notes = "wechat login", httpMethod = "POST")
    public ResponseEntity<WechatLoginResponse> login(@RequestBody final WechatLoginRequest wechatLoginRequest) throws WxErrorException {
        return ResponseEntity.ok(huatuoWechatService.login(wechatLoginRequest.getAppId(), wechatLoginRequest.getCode()));
    }
}
