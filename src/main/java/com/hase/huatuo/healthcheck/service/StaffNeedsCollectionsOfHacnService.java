package com.hase.huatuo.healthcheck.service;

import com.hase.huatuo.healthcheck.dao.StaffNeedsCollectionsRepository;
import com.hase.huatuo.healthcheck.helper.ErrorHandleHelper;
import com.hase.huatuo.healthcheck.model.StaffOfHacnNeedsCollection;
import com.hase.huatuo.healthcheck.model.request.StaffOfHacnNeedsPostBody;
import com.hase.huatuo.healthcheck.model.response.CommonResponse;
import com.hase.huatuo.healthcheck.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class StaffNeedsCollectionsOfHacnService {
    @Autowired
    private StaffNeedsCollectionsRepository staffNeedsCollections;

    public ResponseEntity saveStaffNeedsCollection(StaffOfHacnNeedsPostBody staffOfHacnNeeds){
        CommonResponse commonResponse = new CommonResponse();
        List<StaffOfHacnNeedsCollection> needsCollections = staffNeedsCollections.searchRegisterRecord(staffOfHacnNeeds.getStaffId(), DateUtils.getCurrentDate());
        if(needsCollections!=null && needsCollections.size() > 0){
            ErrorHandleHelper.getInstance().throwBadRequestRestException("Buss_ERROR", "ERROR_EXIST_RECORD_TODAY", staffOfHacnNeeds.getStaffId());
        }
        StaffOfHacnNeedsCollection staff = new StaffOfHacnNeedsCollection();
        staff.setCreateDate(DateUtils.getCurrentDate());
        staff.setExtraHelp(staffOfHacnNeeds.getExtraHelp());
        staff.setMaskNo(staffOfHacnNeeds.getMaskNo());
        staff.setMaterialType(staffOfHacnNeeds.getMaterialType());
        staff.setMobileNumber(staffOfHacnNeeds.getMobileNumber());
        staff.setWorkplace(staffOfHacnNeeds.getWorkplace());
        staff.setStaffId(staffOfHacnNeeds.getStaffId());
        staff.setMobileNumber(staffOfHacnNeeds.getMobileNumber());
        staff.setSerialNumber(UUID.randomUUID().toString());
        try {
            commonResponse.setCode("200");
            commonResponse.setReturnObject(staffNeedsCollections.saveAndFlush(staff).getSerialNumber());
        } catch (Exception e) {
            ErrorHandleHelper.getInstance().throwBadRequestRestException("DB_ERROR", "ERROR_SAVE_DATA_ERROR", staffOfHacnNeeds.getStaffId());
        }
        return ResponseEntity.ok(commonResponse);
    }
}
