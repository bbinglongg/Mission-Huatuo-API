package com.hase.huatuo.healthcheck.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class SurveyFormInfo {
    private String formId;
    private String formNameCn;
    private String formNameEn;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date lastUpdateDatetime;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    private Date pushedDatetime;
}
