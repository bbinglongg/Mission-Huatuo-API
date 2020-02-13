package com.hase.huatuo.healthcheck.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class DonationRequest {
    @NotBlank
    private String staffId;
    private String materialDonation;
    private String maskNum;
    private String adDonation;
    private String helpNumber;
    private String address;
}
