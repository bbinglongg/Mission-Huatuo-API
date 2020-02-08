package com.hase.huatuo.healthcheck.model.response;

import com.hase.huatuo.healthcheck.model.SurveyFormInfo;
import lombok.Data;

import java.util.List;

@Data
public class SurveyFormListResponse {

    private List<SurveyFormInfo> unComplete;
    private List<SurveyFormInfo> complete;

}
