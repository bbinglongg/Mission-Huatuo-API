package com.hase.huatuo.healthcheck.model.response;

import com.hase.huatuo.healthcheck.model.SurveyFormInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class SurveyFormListResponse {
    private List<SurveyFormInfo> unComplete;
    private List<SurveyFormInfo> complete;

    public SurveyFormListResponse (List<SurveyFormInfo> unComplete, List<SurveyFormInfo> complete) {
        this.unComplete = unComplete;
        this.complete = complete;
    }
}
