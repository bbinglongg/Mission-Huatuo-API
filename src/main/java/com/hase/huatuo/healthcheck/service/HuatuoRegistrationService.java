package com.hase.huatuo.healthcheck.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

import com.hase.huatuo.healthcheck.common.config.WxMaProperties;
import com.hase.huatuo.healthcheck.model.request.MiniProgramRegisterRequest;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.hase.huatuo.healthcheck.dao.SMSInfoRepository;
import com.hase.huatuo.healthcheck.dao.StaffListRepository;
import com.hase.huatuo.healthcheck.dao.UserInfoRepository;
import com.hase.huatuo.healthcheck.dao.entity.StaffListEntity;
import com.hase.huatuo.healthcheck.helper.ErrorHandleHelper;
import com.hase.huatuo.healthcheck.model.SMSInfo;
import com.hase.huatuo.healthcheck.model.UserInfo;
import com.hase.huatuo.healthcheck.model.request.RegistrationPostBody;
import com.hase.huatuo.healthcheck.model.response.CommonResponse;
import com.hase.huatuo.healthcheck.utils.SMSUtils;
import org.springframework.util.CollectionUtils;

@Service
public class HuatuoRegistrationService {

    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private SMSInfoRepository smsInfoRepository;

    @Autowired
    private StaffListRepository staffListRepository;

    private String hacnAppId = null;

    private HuatuoRegistrationService (@Autowired WxMaProperties properties) {
        this.hacnAppId = null;
        Optional<WxMaProperties.Config> hacnAppIdConfigOptional = properties.getConfigs().stream().filter(config -> "hacn".equalsIgnoreCase(config.getAppName())).findFirst();
        if (hacnAppIdConfigOptional.isPresent()) {
            hacnAppId = hacnAppIdConfigOptional.get().getAppid();
        }
    }

    public ResponseEntity<CommonResponse> register(RegistrationPostBody registrationPostBody) {
        CommonResponse response = new CommonResponse();
        if ("00000000".equals(registrationPostBody.getStaffId())) {
            UserInfo userInfo = new UserInfo();
            userInfo.setAppId(registrationPostBody.getAppId());
            userInfo.setOpenId(registrationPostBody.getOpenId());
            userInfo.setStaffId(registrationPostBody.getStaffId());
            userInfoRepository.saveAndFlush(userInfo);
            response.setCode("200");
            response.setMsg("success");
            return ResponseEntity.ok(response);
        }
        if (!StringUtils.equals(registrationPostBody.getAppId(), this.hacnAppId)) {
            SMSInfo smsInfo = smsInfoRepository.findById(registrationPostBody.getMobileNum()).orElse(null);
            if (smsInfo == null) {
                System.out.println("mobile number have not send verify code: " + registrationPostBody.getMobileNum());
                response.setCode("-2");
                response.setMsg("The verification code you entered is invalid or expired");
                return ResponseEntity.ok(response);
            }
            if (!registrationPostBody.getVerifyCode().equals(smsInfo.getVerifyCode()) ||
                    (System.currentTimeMillis() - smsInfo.getCreatetime() > 90 * 1000)) {
                response.setCode("-2");
                response.setMsg("The verification code you entered is invalid or expired");
                return ResponseEntity.ok(response);
            }
        }
        List<UserInfo> registerRecords = userInfoRepository.retrieveUserInfoByAppIdStaffId(registrationPostBody.getAppId(), registrationPostBody.getStaffId());
        if (registerRecords.size() != 0) {
            System.out.println("The staff ID entered is already registered, id= " + registrationPostBody.getAppId());
            response.setCode("-1");
            response.setMsg("The staff ID you entered is already registered");
            return ResponseEntity.ok(response);
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setMobileNum(registrationPostBody.getMobileNum());
        userInfo.setAppId(registrationPostBody.getAppId());
        userInfo.setOpenId(registrationPostBody.getOpenId());
        userInfo.setStaffId(registrationPostBody.getStaffId());
        userInfoRepository.saveAndFlush(userInfo);
        response.setCode("200");
        response.setMsg("success");
        return ResponseEntity.ok(response);
    }

    public ResponseEntity<CommonResponse> sendVerifyCode(SMSInfo smsInfo) {
        CommonResponse response = new CommonResponse();
        String regex = "^1\\d{10}$";
        Pattern pattern = Pattern.compile(regex);
        String mobileNum = smsInfo.getMobileNum();
        boolean mobileNumValid = pattern.matcher(mobileNum).matches();
        if (!mobileNumValid) {
            response.setCode("-1");
            response.setMsg("please input valid mobile Number");
            return ResponseEntity.ok(response);
        }
        if (!checkBeforeSendSMS(smsInfo.getMobileNum())) {
            response.setCode("-2");
            response.setMsg("The shortest resend interval is 60s");
            return ResponseEntity.ok(response);
        }
        String verifyCode = String.valueOf(new SecureRandom().nextInt(8999) + 1000);
        smsInfo.setVerifyCode(verifyCode);
        smsInfo.setCreatetime(System.currentTimeMillis());
        smsInfoRepository.saveAndFlush(smsInfo);
        System.out.println("SMS verify code " + verifyCode);
        boolean res = SMSUtils.sendSMSVerifyCode(smsInfo.getMobileNum(), verifyCode);
        if (res) {
            response.setCode("200");
            response.setMsg("success");
            return ResponseEntity.ok(response);
        } else {
            response.setCode("-3");
            response.setMsg("SMS verify code send failed");
            return ResponseEntity.ok(response);
        }
    }

    public void validRegisterRequest(RegistrationPostBody registrationPostBody) {
        if (registrationPostBody == null) {
            ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "request body is null", null);
        }
        String staffId = registrationPostBody.getStaffId();
        if (staffId == null || staffId.length() != 8) {
            ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "staffId is null or invalid", null);
        }
        String appId = registrationPostBody.getAppId();
        if (appId == null) {
            ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "appId is null", null);
        }
        String openId = registrationPostBody.getOpenId();
        if (openId == null) {
            ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "openId is null", null);
        }
        if (!StringUtils.equals(appId, this.hacnAppId)) {
            String verifyCode = registrationPostBody.getVerifyCode();
            if (verifyCode == null) {
                ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "verifyCode is null", null);
            }
            String mobileNum = registrationPostBody.getMobileNum();
            if (mobileNum == null) {
                ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "mobileNum is null", null);
            }
            if (!ifStaffMobileNumInWhiteList(registrationPostBody.getStaffId(), registrationPostBody.getMobileNum())) {
                ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "Invalid input data", null);
            }
        } else {
            if (!ifStaffInWhiteList(registrationPostBody.getStaffId())) {
                ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "Invalid input data", null);
            }
        }
    }

    private boolean checkBeforeSendSMS(String mobileNumber) {
        SMSInfo res = smsInfoRepository.findById(mobileNumber).orElse(null);
        return res == null || (System.currentTimeMillis() - res.getCreatetime() > 60 * 1000);
    }

    public boolean ifStaffMobileNumInWhiteList(String staffId, String mobileNum) {
        try {
            StaffListEntity staffList = staffListRepository.findById(staffId).orElse(null);
            if (staffList != null) {
                if (staffList.getMobileNum() != null && staffList.getMobileNum().length() != 0) {
                    return staffList.getMobileNum().equals(mobileNum);
                }
                return true;
            }
        } catch (Exception e) {

        }
        return false;
    }

    public boolean ifStaffInWhiteList(String staffId) {
        StaffListEntity staffList = staffListRepository.findById(staffId).orElse(null);
        if (staffList != null) {
            return true;
        }
        return false;
    }

    public UserInfo register(MiniProgramRegisterRequest miniProgramRegisterRequest) {
        this.checkRegisterLogic(miniProgramRegisterRequest);
        UserInfo userInfo = null;
        List<UserInfo> userInfos = userInfoRepository.retrieveUserInfoByAppIdStaffId(miniProgramRegisterRequest.getAppId(), miniProgramRegisterRequest.getStaffId());
        if (!CollectionUtils.isEmpty(userInfos)) {
            System.out.println("The staff ID entered is already registered in Mini-program: appId= " + miniProgramRegisterRequest.getAppId());
            ErrorHandleHelper.getInstance().throwBadRequestRestException("-1", "The staff ID that you entered is already registered", miniProgramRegisterRequest.getStaffId());
        } else {
            userInfo = new UserInfo();
            userInfo.setAppId(miniProgramRegisterRequest.getAppId());
            userInfo.setOpenId(miniProgramRegisterRequest.getOpenId());
            userInfo.setStaffId(miniProgramRegisterRequest.getStaffId());
            userInfo = userInfoRepository.saveAndFlush(userInfo);
        }
        return userInfo;
    }

    private void checkRegisterLogic(MiniProgramRegisterRequest miniProgramRegisterRequest) {
        if (!ifStaffInWhiteList(miniProgramRegisterRequest.getStaffId())) {
            ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "Invalid input data", miniProgramRegisterRequest.getStaffId());
        }
    }
}
