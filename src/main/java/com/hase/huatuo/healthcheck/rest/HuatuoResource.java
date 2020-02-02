package com.hase.huatuo.healthcheck.rest;

import com.hase.huatuo.healthcheck.error.ErrorInfo;
import com.hase.huatuo.healthcheck.error.ErrorMessage;
import com.hase.huatuo.healthcheck.error.exception.BadRequestRestException;
import com.hase.huatuo.healthcheck.helper.ErrorHandleHelper;
import com.hase.huatuo.healthcheck.model.request.HealthPostBody;
import com.hase.huatuo.healthcheck.model.request.VPNStatePostBody;
import com.hase.huatuo.healthcheck.model.response.HealthPostResponse;
import com.hase.huatuo.healthcheck.model.response.VPNStatePostResponse;
import com.hase.huatuo.healthcheck.service.HuatuoHealthService;
import com.hase.huatuo.healthcheck.service.HuatuoVPNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import com.hase.huatuo.healthcheck.model.response.AreaReport;
import com.hase.huatuo.healthcheck.service.HealthReportService;
import org.springframework.http.ResponseEntity;


@RestController
@RequestMapping({"/api","/api/v1"})
public class HuatuoResource {

    @Autowired
    private HuatuoHealthService huatuoHealthService;

    @Autowired
    private HuatuoVPNService huatuoVPNService;

    @Autowired
    private HealthReportService healthReportService;
    @PostMapping("/health")
    public HealthPostResponse updateHealth(@RequestBody final HealthPostBody healthPostBody) {
    	validHealthRequest(healthPostBody);
    	
    	return huatuoHealthService.setPersonHealth(healthPostBody);
    }
    
    @GetMapping("/health")
    public ResponseEntity<List<AreaReport>> requestHealth(@RequestHeader("X-IS-DUMMY") String isDummy) {
    	return healthReportService.enquiry(isDummy);
    }
    
    @PostMapping("/vpnstate")
    public VPNStatePostResponse submitVPNState(@RequestBody final VPNStatePostBody vpnStatePostBody) {
    	return huatuoVPNService.setVPNState(vpnStatePostBody);
    }
    
    private void validHealthRequest(HealthPostBody healthPostBody) {
    	if(healthPostBody == null || healthPostBody.getPersonHealthInfo() == null) {
    		ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "request body is null", null);
    	}
    	
    	if(healthPostBody.getPersonHealthInfo().getStaffID() == null ||
    			healthPostBody.getPersonHealthInfo().getStaffID().length() < 5) {
    		ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "staff ID is error", null);
    	}
    	
    	// other field checking
    		
    }
    
  
}
