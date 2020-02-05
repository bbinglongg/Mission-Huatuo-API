package com.hase.huatuo.healthcheck.rest;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hase.huatuo.healthcheck.dao.entity.HealthInfoHacn;
import com.hase.huatuo.healthcheck.helper.ErrorHandleHelper;
import com.hase.huatuo.healthcheck.model.SMSInfo;
import com.hase.huatuo.healthcheck.model.request.HealthInfoHacnEnquiryBody;
import com.hase.huatuo.healthcheck.model.request.HealthInfoHacnPostBody;
import com.hase.huatuo.healthcheck.model.request.HealthPostBody;
import com.hase.huatuo.healthcheck.model.request.RegistrationPostBody;
import com.hase.huatuo.healthcheck.model.request.VpnReportRequest;
import com.hase.huatuo.healthcheck.model.request.VpnRequest;
import com.hase.huatuo.healthcheck.model.request.WechatLoginRequest;
import com.hase.huatuo.healthcheck.model.response.AreaReport;
import com.hase.huatuo.healthcheck.model.response.CommonResponse;
import com.hase.huatuo.healthcheck.model.response.DatadictGetResponse;
import com.hase.huatuo.healthcheck.model.response.HealthPostResponse;
import com.hase.huatuo.healthcheck.model.response.VpnReportResponse;
import com.hase.huatuo.healthcheck.model.response.WechatLoginResponse;
import com.hase.huatuo.healthcheck.service.HealthReportService;
import com.hase.huatuo.healthcheck.service.HuatuoHealthHacnService;
import com.hase.huatuo.healthcheck.service.HuatuoHealthService;
import com.hase.huatuo.healthcheck.service.HuatuoRegistrationService;
import com.hase.huatuo.healthcheck.service.HuatuoVPNService;
import com.hase.huatuo.healthcheck.service.HuatuoWechatService;

import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;


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

    @Autowired
    private HuatuoHealthHacnService huatuoHealthhacnService;
    
    
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
    
    
    @PostMapping("/hacn/health/report")
    public HealthPostResponse uploadHealthHacn(@RequestBody final HealthInfoHacnPostBody healthPostBody) {
    	
    	return huatuoHealthhacnService.uploadHealthHacn(healthPostBody);
    }
    
    @PostMapping("/hacn/health/enquiry")
    public HealthInfoHacn getHealthInfoHacn(@RequestBody final HealthInfoHacnEnquiryBody enquiryBody) {
    	return huatuoHealthhacnService.getHealthInfoHacn(enquiryBody);
    }
}
