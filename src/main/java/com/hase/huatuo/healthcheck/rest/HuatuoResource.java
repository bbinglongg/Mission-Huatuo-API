package com.hase.huatuo.healthcheck.rest;

import com.hase.huatuo.healthcheck.model.request.HealthPostBody;
import com.hase.huatuo.healthcheck.model.request.VPNReportRequest;
import com.hase.huatuo.healthcheck.model.request.VPNStatePostBody;
import com.hase.huatuo.healthcheck.model.response.HealthPostResponse;
import com.hase.huatuo.healthcheck.model.response.VPNReportResponse;
import com.hase.huatuo.healthcheck.service.HuatuoHealthService;
import com.hase.huatuo.healthcheck.service.HuatuoVPNService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
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
    	return huatuoHealthService.setPersonHealth(healthPostBody);
    }

    
    @GetMapping("/health")
    public ResponseEntity<List<AreaReport>> requestHealth(@RequestHeader("X-IS-DUMMY") String isDummy) {
    	return healthReportService.enquiry(isDummy);
    }
    
    @PostMapping("/vpn-state")
    public ResponseEntity submitVPNState(@RequestBody final VPNStatePostBody vpnStatePostBody) {
        huatuoVPNService.submitVPNState(vpnStatePostBody);
    	return ResponseEntity.ok(null);
    }

    @PostMapping("/vpn-state/report")
    public ResponseEntity<VPNReportResponse> loadVPNStateDashboard(@RequestBody final VPNReportRequest vpnReportRequest) throws ParseException {
        return ResponseEntity.ok(new VPNReportResponse(huatuoVPNService.loadVPNStateDashboard(vpnReportRequest)));
    }
}
