package com.hase.huatuo.healthcheck.rest;

import com.hase.huatuo.healthcheck.helper.ErrorHandleHelper;
import com.hase.huatuo.healthcheck.model.SMSInfo;
import com.hase.huatuo.healthcheck.model.request.*;
import com.hase.huatuo.healthcheck.model.response.AreaReport;
import com.hase.huatuo.healthcheck.model.response.CommonResponse;
import com.hase.huatuo.healthcheck.model.response.DatadictGetResponse;
import com.hase.huatuo.healthcheck.model.response.HealthPostResponse;
import com.hase.huatuo.healthcheck.model.response.NewsInfoListResponse;
import com.hase.huatuo.healthcheck.model.response.VpnReportResponse;
import com.hase.huatuo.healthcheck.model.response.WechatLoginResponse;
import com.hase.huatuo.healthcheck.service.*;

import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping({"/huatuo/api","/huatuo/api/v1","/api","/api/v1"})
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
    private NewsInfoListService newsInfoListService;
    
    @Autowired
    private HuatuoNewsService huatuoNewsService;

    @Autowired
    private SurveyFormService surveyFormService;

    @Autowired
    private HuatuoSurveyFormService huatuoSurveyFormService;


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

    @PostMapping("/news-info/lists")
    public NewsInfoListResponse newsInfoList(@RequestBody final NewsInfoListRequestBody newsInfoListRequestBody) {
    	
    	return newsInfoListService.getNewsInfoList(newsInfoListRequestBody);
    }

    @PostMapping("/important-news")
    @ApiOperation(value = "important-news", notes = "important news and unread number", httpMethod = "POST")
    public ResponseEntity<CommonResponse> getImportantNewsList(@RequestBody final NewsNotReadRequest newsNotReadReq) throws WxErrorException {
        return huatuoNewsService.getImportantNewsList(newsNotReadReq);
    }
    
    @PostMapping("/survey/form")
    @ApiOperation(value = "survey form", notes = "get survey form structure", httpMethod = "POST")
    public ResponseEntity<CommonResponse> getSurveryForm(@RequestBody final SurveyFormReq surveyFormReq) throws WxErrorException {
        return surveyFormService.getForm(surveyFormReq);
    }
    
    @PostMapping("/survey/form/submit")
    @ApiOperation(value = "submit survey form", notes = "submit survey form", httpMethod = "POST")
    public ResponseEntity<CommonResponse> submitSurveryForm(@RequestBody final SurveyFormSubmitReq surveyFormSubmitReq) throws WxErrorException {
        return surveyFormService.submitForm(surveyFormSubmitReq);
    }

    @PostMapping("/surveyform/lists")
    @ApiOperation(value = "surveyform_lists", notes = "surveyform lists", httpMethod = "POST")
    public ResponseEntity<CommonResponse> getSurveyFormList(@RequestBody @Valid final SurveyFormListRequestBody surveyFormListRequestBody) throws WxErrorException {
        return huatuoSurveyFormService.getSurveyFormList(surveyFormListRequestBody);
    }

    @PostMapping("/surveyform/detail")
    @ApiOperation(value = "surveyform_detail", notes = "surveyform detail", httpMethod = "POST")
    public ResponseEntity<CommonResponse> getSurveyFormDetail(@RequestBody @Valid final SurveyFormDetailRequestBody surveyFormDetailRequestBody) throws WxErrorException {
        return huatuoSurveyFormService.getSurveyFormDetail(surveyFormDetailRequestBody);
    }
}
