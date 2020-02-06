package com.hase.huatuo.healthcheck.rest;

import com.hase.huatuo.healthcheck.model.NewsInfo;
import com.hase.huatuo.healthcheck.model.NotifyStaffView;
import com.hase.huatuo.healthcheck.model.VpnInfo;
import com.hase.huatuo.healthcheck.model.request.NewsInfoListRequestBody;
import com.hase.huatuo.healthcheck.model.response.AdminNotifyListResponse;
import com.hase.huatuo.healthcheck.model.response.AdminResponse;
import com.hase.huatuo.healthcheck.model.response.VpnReportViewResponse;
import com.hase.huatuo.healthcheck.service.HuatuoAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
@RequestMapping({"/huatuo-admin/api"})
public class HuatuoAdminResource {

    @Autowired
    private HuatuoAdminService huatuoAdminService;


    @GetMapping("/vpn/reportQuery")
    @ApiOperation(value = "vpnReportView", notes = "Index VPN status report", httpMethod = "GET")
    public ResponseEntity<VpnReportViewResponse> queryVPNReport(@RequestParam Map<String,Object> map) {
        List<VpnInfo> vpnInfoList = huatuoAdminService.queryVPNReport(map);
        VpnReportViewResponse response = new VpnReportViewResponse();
        response.setItems(vpnInfoList);
        response.setTotal(vpnInfoList.size());
        response.setCode(20000);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/notify/fetchList")
    @ApiOperation(value = "notifyStaff", notes = "list for notify", httpMethod = "GET")
    public ResponseEntity<AdminNotifyListResponse> queryNotifyStaff(@RequestParam Map<String,Object> map) {
        List<NotifyStaffView> viewList = huatuoAdminService.findNotifyStaff();
        AdminNotifyListResponse response = new AdminNotifyListResponse();
        response.setItems(viewList);
        response.setTotal(viewList.size());
        response.setCode(20000);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/notify/save")
    @ApiOperation(value = "SaveNotifyStaff", notes = "save notify", httpMethod = "POST")
    public ResponseEntity<AdminResponse> saveNotifyStaff(@RequestBody NotifyStaffView notifyStaffView) {
        huatuoAdminService.saveNotifyStaff(notifyStaffView);
        AdminResponse response = new AdminResponse();
        response.setCode(20000);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/notify/delete/{staffId}")
    @ApiOperation(value = "delnotifyStaff", notes = "delete notify staff", httpMethod = "GET")
    public ResponseEntity<AdminResponse> queryNotifyStaff(@PathVariable String staffId) {
        huatuoAdminService.deleteNotifyStaff(staffId);
        AdminResponse response = new AdminResponse();
        response.setCode(20000);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/news/fecthList")
    @ApiOperation(value = "newsInfo", notes = "find all News", httpMethod = "GET")
    public ResponseEntity<AdminResponse> findNews(@RequestParam Map<String,Object> map) {
        huatuoAdminService.findNewsByParms(map);
        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setCode(20000);
        return ResponseEntity.ok(adminResponse);
    }

    @PostMapping("/news/save")
    @ApiOperation(value = "saveNews", notes = "save news", httpMethod = "POST")
    public ResponseEntity<AdminResponse> findNews(@RequestBody NewsInfo newsInfo) {
        huatuoAdminService.saveNews(newsInfo);
        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setCode(20000);
        return ResponseEntity.ok(adminResponse);
    }

    @GetMapping("/news/delete/{id}")
    @ApiOperation(value = "deleteNews", notes = "delete news", httpMethod = "GET")
    public ResponseEntity<AdminResponse> findNews(@PathVariable String id) {
        huatuoAdminService.deleteNews(id);
        AdminResponse adminResponse = new AdminResponse();
        adminResponse.setCode(20000);
        return ResponseEntity.ok(adminResponse);
    }

}
