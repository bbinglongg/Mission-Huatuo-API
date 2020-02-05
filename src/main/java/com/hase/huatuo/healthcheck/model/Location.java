package com.hase.huatuo.healthcheck.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity()
@ApiModel(description = "location")
public class Location {
    @Column(name = "city", nullable = false)
    @ApiModelProperty(value = "45022339", notes = "staff id of VPN state", dataType = "java.lang.String")
    private String city;
    @Id
    @Column(name = "workplace", nullable = false)
    @ApiModelProperty(value = "0", notes = "VPN type", dataType = "java.lang.String")
    private String workplace;
    @Column(name = "workplace_en_name")
    @ApiModelProperty(value = "0", notes = "Open id", dataType = "java.lang.String")
    private String workplaceEnName;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getWorkplace() {
        return workplace;
    }

    public void setWorkplace(String workplace) {
        this.workplace = workplace;
    }

    public String getWorkplaceEnName() {
        return workplaceEnName;
    }

    public void setWorkplaceEnName(String workplaceEnName) {
        this.workplaceEnName = workplaceEnName;
    }
}
