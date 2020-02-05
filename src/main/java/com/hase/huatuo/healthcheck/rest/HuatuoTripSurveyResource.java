package com.hase.huatuo.healthcheck.rest;

import com.hase.huatuo.healthcheck.helper.ErrorHandleHelper;
import com.hase.huatuo.healthcheck.model.SMSInfo;
import com.hase.huatuo.healthcheck.model.request.*;
import com.hase.huatuo.healthcheck.model.response.*;
import com.hase.huatuo.healthcheck.service.*;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;


@RestController
@RequestMapping({"/api","/api/v1"})
public class HuatuoTripSurveyResource {


	@Autowired
    private HuatuoTripSurveyService huatuoTripSurveyService;

    @PostMapping("/add_tripSurvey")
    @ApiOperation(value = "add_tripSurvey", notes = "add tripSurvey", httpMethod = "POST")
    public ResponseEntity<CommonResponse> addTripSurvey(@RequestBody final TripSurveyPostBody tripSurveyPostBody) throws ParseException {
//        huatuoTripSurveyService.validAddTripSurveyRequest(registerBody);
        return huatuoTripSurveyService.addTripSurvey(tripSurveyPostBody);
    }
}
