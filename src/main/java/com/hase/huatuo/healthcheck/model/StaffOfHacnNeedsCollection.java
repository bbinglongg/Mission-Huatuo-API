package com.hase.huatuo.healthcheck.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity()
@Table(name = "staff_needs_collections")
public class StaffOfHacnNeedsCollection {
    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

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

    @Id
    @GenericGenerator(name="idGenerator", strategy="uuid")
    @GeneratedValue(generator="idGenerator")
    @Column(name = "serial_number")
    private String serialNumber;
    @Column(name = "staff_id")
    private String staffId;
    @Column(name = "mobile_number")
    private String mobileNumber;
    @Column(name = "material_type")
    private String materialType;
    @Column(name = "mask_no")
    private String maskNo;
    @Column(name = "workplace")
    private String workplace;
    @Column(name = "extra_help")
    private String extraHelp;

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    @Column(name = "create_date")
    private String createDate;
}
