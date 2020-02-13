package com.hase.huatuo.healthcheck.service;

import java.util.*;
import java.util.stream.Collectors;

import com.hase.huatuo.healthcheck.dao.SurveyFormAnswerRepository;
import com.hase.huatuo.healthcheck.dao.SurveyFormRepository;
import com.hase.huatuo.healthcheck.dao.entity.SurveyForm;
import com.hase.huatuo.healthcheck.dao.entity.SurveyFormAnswer;
import com.hase.huatuo.healthcheck.error.exception.BadRequestRestException;
import com.hase.huatuo.healthcheck.model.*;
import com.hase.huatuo.healthcheck.model.request.SurveyFormListRequest;
import com.hase.huatuo.healthcheck.model.response.SurveyFormListResponse;
import org.dozer.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hase.huatuo.healthcheck.helper.ErrorHandleHelper;
import com.hase.huatuo.healthcheck.model.request.SurveyFormAnswerRequest;
import com.hase.huatuo.healthcheck.model.response.CommonResponse;
import org.springframework.util.CollectionUtils;

@Service
public class SurveyFormService {

	@Autowired
	private SurveyFormRepository surveyFormRepository;

	@Autowired
	private SurveyFormAnswerRepository surveyFormAnswerRepository;

	@Autowired
	private Mapper mapper;

	public SurveyFormStructure getFormStructure(Long formId) throws BadRequestRestException {
		Optional<SurveyForm> surveyFormOptional = surveyFormRepository.findById(formId);
		if (!surveyFormOptional.isPresent()) {
			ErrorHandleHelper.getInstance().throwBadRequestRestException("-1", "No this form", formId.toString());
		}
		SurveyForm surveyForm = surveyFormOptional.get();
		SurveyFormStructure formStructure = mapper.map(surveyForm, SurveyFormStructure.class);
		return formStructure;
	}

	public SurveyFormListResponse getStaffFormList(SurveyFormListRequest surveyFormListRequest) {
		List<SurveyFormInfo> unComplete = null;
		List<SurveyFormInfo> complete = null;
		List<SurveyForm> allPushedSurveyForms = surveyFormRepository.retrievalAllPushedFormByAppId(surveyFormListRequest.getAppId());
		List<SurveyForm> completeSurveyForms = surveyFormRepository.retrievalCompleteSurveyFormByAppIdStaffId(surveyFormListRequest.getAppId(), surveyFormListRequest.getStaffId());
		if (!CollectionUtils.isEmpty(completeSurveyForms) && !CollectionUtils.isEmpty(allPushedSurveyForms)) {
			complete = completeSurveyForms.stream().sorted(Comparator.comparing(SurveyForm::getLastUpdateDatetime).reversed()).map(completeSurveyForm -> mapper.map(completeSurveyForm, SurveyFormInfo.class)).collect(Collectors.toList());
			unComplete = allPushedSurveyForms.stream().filter(pushedSurveyForm -> {
				Long formId = pushedSurveyForm.getFormId();
				return !completeSurveyForms.stream().allMatch(completeSurveyForm -> formId.equals(completeSurveyForm.getFormId()));
			}).map(pushedSurveyForm -> mapper.map(pushedSurveyForm, SurveyFormInfo.class)).collect(Collectors.toList());
		} else {
			if (!CollectionUtils.isEmpty(allPushedSurveyForms)) {
				unComplete = allPushedSurveyForms.stream().map(pushedSurveyForm -> mapper.map(pushedSurveyForm, SurveyFormInfo.class)).collect(Collectors.toList());
			}
		}
		return new SurveyFormListResponse(unComplete, complete);
	}

	public SurveyFormData getFormData(Long formId, String staffId) {
		SurveyFormData formData = null;
		SurveyFormAnswer surveyFormAnswer = surveyFormAnswerRepository.getSurveyFormAnswerByFormIdStaffId(formId, staffId);
		if (surveyFormAnswer != null ) {
			formData = mapper.map(surveyFormAnswer, SurveyFormData.class);
		}
		return formData;
	}

	public CommonResponse submitFormAnswer(Long formId, String staffId, final SurveyFormAnswerRequest surveyFormAnswerRequest) {
		CommonResponse commonResponse = new CommonResponse();
		SurveyFormAnswer surveyFormAnswer = surveyFormAnswerRepository.getSurveyFormAnswerByFormIdStaffId(formId, staffId);
		if (surveyFormAnswer != null) {
			ErrorHandleHelper.getInstance().throwBadRequestRestException("-1", "This form has been submitted", null);
		}
		surveyFormAnswer = mapper.map(surveyFormAnswerRequest.getAnswers(), SurveyFormAnswer.class);
		surveyFormAnswer.setFormId(formId);
		surveyFormAnswer.setStaffId(staffId);
		surveyFormAnswer.setLastUpdateDatetime(new Date());
		surveyFormAnswerRepository.saveAndFlush(surveyFormAnswer);
		commonResponse.setCode("200");
		commonResponse.setMsg("success");
		return commonResponse;
	}
}
