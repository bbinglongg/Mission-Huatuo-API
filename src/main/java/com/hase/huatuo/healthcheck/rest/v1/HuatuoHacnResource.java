package com.hase.huatuo.healthcheck.rest.v1;

import java.text.ParseException;

import javax.validation.Valid;

import com.hase.huatuo.healthcheck.dao.DonationRepository;
import com.hase.huatuo.healthcheck.dao.entity.Donation;
import com.hase.huatuo.healthcheck.model.request.*;
import com.hase.huatuo.healthcheck.model.response.AreaReportForHacn;
import com.hase.huatuo.healthcheck.service.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.hase.huatuo.healthcheck.model.response.CommonResponse;
import com.hase.huatuo.healthcheck.model.response.HealthPostResponse;
import com.hase.huatuo.healthcheck.model.response.HealthReportEnquirytResponse;

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

	@Autowired
    private HuatuoHealthHacnService huatuoHealthhacnService;

    @Autowired
    private HealthReportOfHacnService healthReportOfHacnService;

    @Autowired
    private DonationRepository donationRepository;

    @Autowired
    private StaffNeedsCollectionsOfHacnService needsCollectionsOfHacnService;
	
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

    @PostMapping("/trip/survey")
    @ApiOperation(value = "hacn-trip-survey", notes = "hacn trip survey", httpMethod = "POST")
    public ResponseEntity<CommonResponse> addTripSurvey(@RequestBody final TripSurveyPostBody tripSurveyPostBody) throws ParseException {
        return huatuoTripSurveyService.addTripSurvey(tripSurveyPostBody);
    }

    @GetMapping("/health")
    public ResponseEntity<AreaReportForHacn> requestHealthOfHacn() {
        return healthReportOfHacnService.enquiry();
    }
    
    @PostMapping("/health/report")
    public HealthPostResponse uploadHealthHacn(@RequestBody final HealthInfoHacnPostBody healthPostBody) {
    	
    	return huatuoHealthhacnService.uploadHealthHacn(healthPostBody);
    }
    
    @PostMapping("/health/enquiry")
    public HealthReportEnquirytResponse getHealthInfoHacn(@RequestBody final HealthInfoHacnEnquiryBody enquiryBody) {
    	return huatuoHealthhacnService.getHealthInfoHacn(enquiryBody);
    }

    @PostMapping("/health/detail")
    @ApiOperation(value = "hacn-health-detail", notes = "hacn health detail", httpMethod = "POST")
    public ResponseEntity getHealthDetail(@Valid @RequestBody final HealthDetailReqBody healthDetailReqBody) {
        return huatuoHealthhacnService.getHealthDetail(healthDetailReqBody);
    }

    @PostMapping("/needs-collection")
    @ApiOperation(value = "hacn-needs-collection", notes = "hacn staff needs collection", httpMethod = "POST")
    public ResponseEntity needsCollectionOfHacnStaff(@Valid @RequestBody  StaffOfHacnNeedsPostBody staffOfHacnNeedsPostBody){
        return needsCollectionsOfHacnService.saveStaffNeedsCollection(staffOfHacnNeedsPostBody);
    }

    @PostMapping("/donation")
    @ApiOperation(value = "hacn-donation", notes = "hacn staff needs donation", httpMethod = "POST")
    public ResponseEntity hacnStaffDonation(@Valid @RequestBody DonationRequest donationRequest){
        Donation donation = new Donation();
        BeanUtils.copyProperties(donationRequest,donation);
        donationRepository.save(donation);
        CommonResponse commonResponse = new CommonResponse();
        commonResponse.setCode("200");
        commonResponse.setMsg("Success");
        return  ResponseEntity.ok(commonResponse);
    }
}
