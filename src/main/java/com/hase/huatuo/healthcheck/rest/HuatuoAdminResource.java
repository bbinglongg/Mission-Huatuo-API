package com.hase.huatuo.healthcheck.rest;

import com.hase.huatuo.healthcheck.model.VpnInfo;
import com.hase.huatuo.healthcheck.service.HuatuoAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping({"/huatuoAdmin/api","/api"})
public class HuatuoAdminResource {

    @Autowired
    private HuatuoAdminService huatuoAdminService;


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

}
