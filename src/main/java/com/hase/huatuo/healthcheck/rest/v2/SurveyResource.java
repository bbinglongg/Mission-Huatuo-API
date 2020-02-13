package com.hase.huatuo.healthcheck.rest.v2;

import com.hase.huatuo.healthcheck.model.request.SurveyFormListRequest;
import com.hase.huatuo.healthcheck.model.request.SurveyFormAnswerRequest;
import com.hase.huatuo.healthcheck.model.response.CommonResponse;
import com.hase.huatuo.healthcheck.model.response.SurveyFormListResponse;
import com.hase.huatuo.healthcheck.model.response.SurveyFormNewResponse;
import com.hase.huatuo.healthcheck.model.response.SurveyFormViewResponse;
import com.hase.huatuo.healthcheck.service.SurveyFormService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author David
 * @date
 */
@RestController
@RequestMapping({"/api/v2/survey"})
@Api(tags="SurveyResource", value = "Survey v2")
public class SurveyResource {

    @Autowired
    private SurveyFormService surveyFormService;

    @GetMapping("/form/{formId}/new")
    @ApiOperation(value = "survey form structure", notes = "get survey form structure", httpMethod = "GET")
    @ResponseStatus(value = HttpStatus.OK)
    public SurveyFormNewResponse getFormStructure(@PathVariable("formId") Long formId) {
        return new SurveyFormNewResponse(surveyFormService.getFormStructure(formId));
    }

    @PostMapping("/form/{formId}/new/{staffId}")
    @ApiOperation(value = "submit survey form", notes = "submit survey form", httpMethod = "POST")
    @ResponseStatus(value = HttpStatus.OK)
    public CommonResponse submitSurveyForm(@PathVariable("formId") Long formId, @PathVariable("staffId") String staffId, @RequestBody final SurveyFormAnswerRequest surveyFormAnswerRequest) {
        return surveyFormService.submitFormAnswer(formId, staffId, surveyFormAnswerRequest);
    }

    @PostMapping("/form")
    @ApiOperation(value = "staff survey form list", notes = "get staff survey form list", httpMethod = "POST")
    @ResponseStatus(value = HttpStatus.OK)
    public SurveyFormListResponse getFormList(@RequestBody SurveyFormListRequest surveyFormListRequest) {
        return surveyFormService.getStaffFormList(surveyFormListRequest);
    }

    @GetMapping("/form/{formId}/view/{staffId}")
    @ApiOperation(value = "survey form structure and data", notes = "get survey form structure and data", httpMethod = "GET")
    @ResponseStatus(value = HttpStatus.OK)
    public SurveyFormViewResponse getFormStructure(@PathVariable("formId") Long formId, @PathVariable("staffId") String staffId) {
        return new SurveyFormViewResponse(surveyFormService.getFormStructure(formId), surveyFormService.getFormData(formId, staffId));
    }
}
