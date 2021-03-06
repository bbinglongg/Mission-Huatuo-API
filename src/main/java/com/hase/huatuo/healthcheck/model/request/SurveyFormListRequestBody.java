package com.hase.huatuo.healthcheck.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class SurveyFormListRequestBody {

    @NotBlank
    private String staffId;
    @NotBlank
    private String appId;
}
