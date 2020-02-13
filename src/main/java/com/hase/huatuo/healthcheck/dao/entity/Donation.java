package com.hase.huatuo.healthcheck.dao.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Donation {
    @Id
    @Column
    private String staffId;
    @Column
    private String materialDonation;
    @Column
    private String maskNum;
    @Column(columnDefinition = "text")
    private String adDonation;
    @Column
    private String helpNumber;
    @Column
    private String address;
}
