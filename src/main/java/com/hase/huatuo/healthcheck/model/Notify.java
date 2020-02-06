package com.hase.huatuo.healthcheck.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import java.io.Serializable;
import java.util.Date;

@Entity()
@ApiModel(description = "Notify staff")
public class Notify {
    @Id
    @Column(name = "staff_id", nullable = false)
    @ApiModelProperty(value = "45022339", notes = "staff id", dataType = "java.lang.String")
    private String staffId;

    @Column(name = "status", nullable = false)
    @ApiModelProperty(value = "0", notes = "status", dataType = "java.lang.String")
    private String status;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
