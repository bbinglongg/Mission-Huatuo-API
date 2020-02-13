package com.hase.huatuo.healthcheck.model.response;

import com.hase.huatuo.healthcheck.model.SurveyFormData;
import com.hase.huatuo.healthcheck.model.SurveyFormStructure;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SurveyFormViewResponse {
    private SurveyFormStructure formStructure;
    private SurveyFormData formData;
}
