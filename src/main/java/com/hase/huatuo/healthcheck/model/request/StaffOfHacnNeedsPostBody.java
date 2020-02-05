package com.hase.huatuo.healthcheck.model.request;

import javax.validation.constraints.NotBlank;

public class StaffOfHacnNeedsPostBody {

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public String getMaterialType() {
        return materialType;
    }

    public void setMaterialType(String materialType) {
        this.materialType = materialType;
    }

    public String getMaskNo() {
        return maskNo;
    }

    public void setMaskNo(String maskNo) {
        this.maskNo = maskNo;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getExtraHelp() {
        return extraHelp;
    }

    public void setExtraHelp(String extraHelp) {
        this.extraHelp = extraHelp;
    }


    @NotBlank
    private String staffId;
    @NotBlank
    private String mobileNumber;
    @NotBlank
    private String materialType;

    private String maskNo;
    @NotBlank
    private String workplace;
    private String extraHelp;
}
