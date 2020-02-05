package com.hase.huatuo.healthcheck.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hase.huatuo.healthcheck.model.request.TripHistoryReqBody;
import com.hase.huatuo.healthcheck.model.response.CommonResponse;
import com.hase.huatuo.healthcheck.model.response.TripHistoryRespBody;
import com.hase.huatuo.healthcheck.service.HuatuoTripHistoryService;


@RestController
@RequestMapping({"/api/hacn/","/api/hacn/v1"})
public class HuatuoHacnResource {


	@Autowired
    private HuatuoTripHistoryService huatuoTripHistoryService;


    @PostMapping("/trip/history")
    public CommonResponse getTripHistory(@RequestBody final TripHistoryReqBody tripHistoryBody) {
    	return huatuoTripHistoryService.getTripHistory(tripHistoryBody);
    }
    
}
