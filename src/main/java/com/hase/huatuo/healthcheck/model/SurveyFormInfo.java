package com.hase.huatuo.healthcheck.model;

import lombok.Data;

@Data
public class SurveyFormInfo {

    private String formId;
    private String formNameCn;
    private String formNameEn;
    private String lastUpdateDateTime;
    private String status;
}
