package com.hase.huatuo.healthcheck.service;

import com.hase.huatuo.healthcheck.dao.TripSurveyRepository;
import com.hase.huatuo.healthcheck.helper.ErrorHandleHelper;
import com.hase.huatuo.healthcheck.model.TripSurveyInfo;
import com.hase.huatuo.healthcheck.model.request.TripSurveyPostBody;
import com.hase.huatuo.healthcheck.model.response.CommonResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.lang.reflect.Field;
import java.time.LocalDate;
import java.util.UUID;

@Service
public class HuatuoTripSurveyService {

    @Autowired
    private TripSurveyRepository tripSurveyRepository;

    public ResponseEntity<CommonResponse> addTripSurvey(TripSurveyPostBody tripSurveyPostBody) {
        validation(tripSurveyPostBody);
        CommonResponse response = new CommonResponse();
        String currentDate = LocalDate.now().toString();
        TripSurveyInfo tripSurveyInfo = tripSurveyRepository.getTripSurveyInfoByStaffId(tripSurveyPostBody.getStaffId(), currentDate);
        if (null != tripSurveyInfo) {
            tripSurveyRepository.deleteTripSurveyInfoBySerailNumber(tripSurveyInfo.getSerailNumber());
        }
        TripSurveyInfo tripSurveySaveInfo = new TripSurveyInfo();
        BeanUtils.copyProperties(tripSurveyPostBody,tripSurveySaveInfo);
        tripSurveySaveInfo.setReportDate(currentDate);
        tripSurveySaveInfo.setSerailNumber(UUID.randomUUID().toString());
        tripSurveyRepository.saveAndFlush(tripSurveySaveInfo);
        response.setCode("200");
        response.setMsg("success");
        return ResponseEntity.ok(response);
    }

    private void validation(TripSurveyPostBody tripSurveyPostBody) {
        if(tripSurveyPostBody == null ) {
            ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "request body is null", null);
        }

        Class clazz = (Class)tripSurveyPostBody.getClass();
        Field fields[] = clazz.getDeclaredFields();
        for(Field field : fields){
            field.setAccessible(true);
            Object fieldValue = null;
            try {
                fieldValue = field.get(tripSurveyPostBody);
                String fieldName = field.getName();
                if(ObjectUtils.isEmpty(fieldValue)){
                    ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", fieldName +" is error", null);
                }
            } catch (IllegalArgumentException e) {
                ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", e.getMessage(), null);
            } catch (IllegalAccessException e) {
                ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", e.getMessage(), null);
            }
        }
    }
}
