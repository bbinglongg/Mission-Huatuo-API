package com.hase.huatuo.healthcheck.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hase.huatuo.healthcheck.dao.TripSurveyRepository;
import com.hase.huatuo.healthcheck.helper.ErrorHandleHelper;
import com.hase.huatuo.healthcheck.model.TripSurveyInfo;
import com.hase.huatuo.healthcheck.model.request.TripDetailReqBody;
import com.hase.huatuo.healthcheck.model.response.CommonResponse;

@Service
public class HuatuoTripDetailService {
	
	@Autowired
    private TripSurveyRepository tripSurveyRepository;
	
	public ResponseEntity getTripDetail(final TripDetailReqBody tripDetailReqBody) {
		
		Optional<TripSurveyInfo> tripSurveyInfo = tripSurveyRepository.findById(tripDetailReqBody.getSerailNumber());
		CommonResponse result = new CommonResponse();
		if (tripSurveyInfo.isPresent()) { 
			result.setCode("200");
			result.setMsg("success");
			result.setReturnObject(tripSurveyInfo.get());
		} else { 
			ErrorHandleHelper.getInstance().throwBadRequestRestException("Buss_ERROR", "Record not found", tripDetailReqBody.getSerailNumber());
		}
		return ResponseEntity.ok(result);
	}
}
