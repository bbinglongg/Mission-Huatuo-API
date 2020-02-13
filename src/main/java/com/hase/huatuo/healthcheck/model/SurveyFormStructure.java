package com.hase.huatuo.healthcheck.model;

import lombok.Data;

import java.util.List;

@Data
public class SurveyFormStructure {
    private String formNameCn;
    private String formNameEn;
    private String formDescriptionCn;
    private String formDescriptionEn;
    private List<SurveyFormStructureQuestion> questions;
}
