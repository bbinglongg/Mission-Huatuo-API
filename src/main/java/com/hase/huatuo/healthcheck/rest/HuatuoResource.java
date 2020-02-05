package com.hase.huatuo.healthcheck.rest;

import java.text.ParseException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hase.huatuo.healthcheck.model.request.TripDetailReqBody;
import com.hase.huatuo.healthcheck.model.request.TripHistoryReqBody;
import com.hase.huatuo.healthcheck.model.request.TripSurveyPostBody;
import com.hase.huatuo.healthcheck.model.response.CommonResponse;
import com.hase.huatuo.healthcheck.service.HuatuoTripDetailService;
import com.hase.huatuo.healthcheck.service.HuatuoTripHistoryService;
import com.hase.huatuo.healthcheck.service.HuatuoTripSurveyService;

import io.swagger.annotations.ApiOperation;


@RestController
@RequestMapping({"/api/hacn/","/api/hacn/v1"})
public class HuatuoHacnResource {


	@Autowired
    private HuatuoTripHistoryService huatuoTripHistoryService;
	
	@Autowired
    private HuatuoTripDetailService huatuoTripDetailService;
	
	@Autowired
    private HuatuoTripSurveyService huatuoTripSurveyService;


    @PostMapping("/trip/history")
    @ApiOperation(value = "hacn-trip-history", notes = "hacn trip history", httpMethod = "POST")
    public ResponseEntity getTripHistory(@Valid @RequestBody final TripHistoryReqBody tripHistoryBody) {
    	return huatuoTripHistoryService.getTripHistory(tripHistoryBody);
    }
    
    @PostMapping("/trip/detail")
    @ApiOperation(value = "hacn-trip-detail", notes = "hacn trip detail", httpMethod = "POST")
    public ResponseEntity getTripDetail(@Valid @RequestBody final TripDetailReqBody tripDetailReqBody) {
    	return huatuoTripDetailService.getTripDetail(tripDetailReqBody);
    }
    
    @PostMapping("/add_tripSurvey")
    @ApiOperation(value = "add_tripSurvey", notes = "add tripSurvey", httpMethod = "POST")
    public ResponseEntity<CommonResponse> addTripSurvey(@RequestBody final TripSurveyPostBody tripSurveyPostBody) throws ParseException {
//        huatuoTripSurveyService.validAddTripSurveyRequest(registerBody);
        return huatuoTripSurveyService.addTripSurvey(tripSurveyPostBody);
    }
    
}
