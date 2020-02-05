package com.hase.huatuo.healthcheck.service;

import com.hase.huatuo.healthcheck.dao.SMSInfoRepository;
import com.hase.huatuo.healthcheck.dao.StaffListRepository;
import com.hase.huatuo.healthcheck.dao.TripSurveyRepository;
import com.hase.huatuo.healthcheck.dao.UserInfoRepository;
import com.hase.huatuo.healthcheck.helper.ErrorHandleHelper;
import com.hase.huatuo.healthcheck.model.SMSInfo;
import com.hase.huatuo.healthcheck.model.StaffList;
import com.hase.huatuo.healthcheck.model.TripSurveyInfo;
import com.hase.huatuo.healthcheck.model.UserInfo;
import com.hase.huatuo.healthcheck.model.request.RegistrationPostBody;
import com.hase.huatuo.healthcheck.model.request.TripSurveyPostBody;
import com.hase.huatuo.healthcheck.model.response.CommonResponse;
import com.hase.huatuo.healthcheck.utils.SMSUtils;
import org.hibernate.id.UUIDGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;

@Service
public class HuatuoTripSurveyService {

    @Autowired
    private TripSurveyRepository tripSurveyRepository;

    public ResponseEntity<CommonResponse> addTripSurvey(TripSurveyPostBody tripSurveyPostBody) {
        CommonResponse response = new CommonResponse();
        String currentDate = LocalDate.now().toString();
        TripSurveyInfo tripSurveyInfo = tripSurveyRepository.getTripSurveyInfoByStaffId(tripSurveyPostBody.getStaffId(), currentDate);
        if (null != tripSurveyInfo) {
            tripSurveyRepository.deleteTripSurveyInfoBySerailNumber(tripSurveyInfo.getSerailNumber());
        }
        TripSurveyInfo tripSurveySaveInfo = new TripSurveyInfo();
        tripSurveySaveInfo.setAloneOrNot(tripSurveyPostBody.getAloneOrNot());
        tripSurveySaveInfo.setBodyHistory(tripSurveyPostBody.getBodyHistory());
        tripSurveySaveInfo.setContactHistory(tripSurveyPostBody.getContactHistory());
        tripSurveySaveInfo.setFamilyCondition(tripSurveyPostBody.getFamilyCondition());
        tripSurveySaveInfo.setFamilyContact(tripSurveyPostBody.getFamilyContact());
        tripSurveySaveInfo.setLocation(tripSurveyPostBody.getLocation());
        tripSurveySaveInfo.setMobileNumber(tripSurveyPostBody.getMobileNumber());
        tripSurveySaveInfo.setReportDate(currentDate);
        tripSurveySaveInfo.setSerailNumber(UUID.randomUUID().toString());
        tripSurveySaveInfo.setStaffId(tripSurveyPostBody.getStaffId());
        tripSurveySaveInfo.setTemperature(tripSurveyPostBody.getTemperature());
        tripSurveySaveInfo.setTransitCity(tripSurveyPostBody.getTransitCity());
        tripSurveySaveInfo.setTransitCityOfHuBei(tripSurveyPostBody.getTransitCityOfHuBei());
        tripSurveySaveInfo.setTransitDate(tripSurveyPostBody.getTransitDate());
        tripSurveySaveInfo.setTransitHuBei(tripSurveyPostBody.getTransitHuBei());
        tripSurveySaveInfo.setTransitMethod(tripSurveyPostBody.getTransitMethod());
        tripSurveySaveInfo.setTransitNo(tripSurveyPostBody.getTransitNo());
        tripSurveySaveInfo.setTransitWuHan(tripSurveyPostBody.getTransitWuHan());
        tripSurveyRepository.saveAndFlush(tripSurveySaveInfo);
        response.setCode("200");
        response.setMsg("success");
        return ResponseEntity.ok(response);
    }


}
