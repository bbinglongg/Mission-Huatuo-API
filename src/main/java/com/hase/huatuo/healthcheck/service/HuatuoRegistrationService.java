package com.hase.huatuo.healthcheck.service;

import java.security.SecureRandom;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hase.huatuo.healthcheck.dao.SMSInfoRepository;
import com.hase.huatuo.healthcheck.dao.StaffListRepository;
import com.hase.huatuo.healthcheck.dao.UserInfoRepository;
import com.hase.huatuo.healthcheck.helper.ErrorHandleHelper;
import com.hase.huatuo.healthcheck.model.SMSInfo;
import com.hase.huatuo.healthcheck.model.StaffList;
import com.hase.huatuo.healthcheck.model.UserInfo;
import com.hase.huatuo.healthcheck.model.request.RegistrationPostBody;
import com.hase.huatuo.healthcheck.model.response.CommonResponse;
import com.hase.huatuo.healthcheck.utils.SMSUtils;

@Service
public class HuatuoRegistrationService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private SMSInfoRepository smsInfoRepository;

    @Autowired
    private StaffListRepository staffListRepository;
    
    public ResponseEntity<CommonResponse> register(RegistrationPostBody registrationPostBody) {
        CommonResponse response = new CommonResponse();
        if(!checkStaffId(registrationPostBody.getStaffId())){
            response.setCode("-1");
            response.setMsg("输入的staff ID不存在");
            return ResponseEntity.ok(response);
        }
        List<UserInfo> registerRecords = userInfoRepository.searchRegisterRecord(registrationPostBody.getAppId(),registrationPostBody.getStaffId());
        if(registerRecords.size() != 0){
            System.out.println("输入的staff ID 已注册");
            response.setCode("-2");
            response.setMsg("输入的staff ID 已注册");
            return ResponseEntity.ok(response);
        }
        SMSInfo smsInfo = smsInfoRepository.getOne(registrationPostBody.getMobileNum());
        if(registrationPostBody.getVerifyCode().equals(smsInfo.getVerifyCode()) &&
                (new Date().getTime() - smsInfo.getCreatetime() < 90 * 1000)){
            UserInfo userInfo = new UserInfo();
            userInfo.setMobileNum(registrationPostBody.getMobileNum());
            userInfo.setAppId(registrationPostBody.getAppId());
            userInfo.setOpenId(registrationPostBody.getOpenId());
            userInfo.setStaffId(registrationPostBody.getStaffId());
            userInfoRepository.saveAndFlush(userInfo);
            response.setCode("200");
            response.setMsg("success");
            return ResponseEntity.ok(response);
        }else{
            response.setCode("-3");
            response.setMsg("验证码错误或已失效");
            return ResponseEntity.ok(response);
        }
    }

    public ResponseEntity<CommonResponse> sendVerifyCode(SMSInfo smsInfo){
        CommonResponse response = new CommonResponse();
        String regex = "^((13[0-9])|(14[5,7,9])|(15([0-3]|[5-9]))|(166)|(17[0,1,3,5,6,7,8])|(18[0-9])|(19[8|9]))\\d{8}$";
        Pattern pattern = Pattern.compile(regex);
        String mobileNum = smsInfo.getMobileNum();
        boolean mobileNumValid = pattern.matcher(mobileNum).matches();
        if(!mobileNumValid){
            response.setCode("-1");
            response.setMsg("please input valid mobile Number");
            return ResponseEntity.ok(response);
        }
        if(!checkBeforeSendSMS(smsInfo.getMobileNum())){
            response.setCode("-2");
            response.setMsg("The shortest resend interval is 60s");
            return ResponseEntity.ok(response);
        }
        String verifyCode = String.valueOf(new SecureRandom().nextInt(8999)+1000);
        System.out.println("SMS verify code "+verifyCode);
        boolean res = SMSUtils.sendSMSVerifyCode(smsInfo.getMobileNum(),verifyCode);
        if(res){
            smsInfo.setVerifyCode(verifyCode);
            smsInfo.setCreatetime(new Date().getTime());
            smsInfoRepository.saveAndFlush(smsInfo);
            response.setCode("200");
            response.setMsg("success");
            return ResponseEntity.ok(response);
        }else{
            response.setCode("-3");
            response.setMsg("SMS verify code send failed");
            return ResponseEntity.ok(response);
        }
    }

    public void validRegisterRequest(RegistrationPostBody registrationPostBody){
        if(registrationPostBody == null ) {
            ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "request body is null", null);
        }
        String staffId = registrationPostBody.getStaffId();
        if(staffId == null || staffId.length() != 8){
            ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "staffId is null or invalid", null);
        }
        String appId = registrationPostBody.getAppId();
        if(appId == null){
            ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "appId is null", null);
        }
        String verifyCode = registrationPostBody.getVerifyCode();
        if(verifyCode == null){
            ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "verifyCode is null", null);
        }
        String openId = registrationPostBody.getOpenId();
        if(openId == null){
            ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "openId is null", null);
        }
        String mobileNum = registrationPostBody.getMobileNum();
        if(mobileNum == null){
            ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "mobileNum is null", null);
        }
    }

    private boolean checkBeforeSendSMS(String mobileNumber){
        SMSInfo res = smsInfoRepository.findById(mobileNumber).orElse(null);
        return res == null || (new Date().getTime()-res.getCreatetime() > 60*1000);
    }

    /**
     * staffId Valid return true
     * @param staffId
     * @return
     */
    private boolean checkStaffId(String staffId){
        if(staffId != null && staffId.length()==8){
            //check staffID valid
            return true;
        }
        return false;
    }
    
    public boolean ifStaffInWhiteList(String staffId) {
    	try {
    		StaffList staffList = staffListRepository.findById(staffId).orElse(null);
    		if(staffList != null) {
    			return true;
    		}
    	} catch(Exception e) {

    	}

    	return false;
    }
}
