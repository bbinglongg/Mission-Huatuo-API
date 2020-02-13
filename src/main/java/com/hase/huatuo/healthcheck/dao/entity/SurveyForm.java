package com.hase.huatuo.healthcheck.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Entity
@Data
public class SurveyForm implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long formId;
    @Column
    private String formNameCn;
    @Column
    private String formNameEn;
    @Column
    private String formDescriptionCn;
    @Column
    private String formDescriptionEn;
    @Column
    private String pushed;
    @Column
    private Date pushedDatetime;
    @Column
    private Date lastUpdateDatetime;
    @Column
    private String appId;
    @Column
    private String lastUpdateOperator;
    @Column
    private String pushOperator;

    @OneToMany()
    @JoinColumn(name="formId", referencedColumnName = "formId")
    private List<SurveyFormQuestion> questions;
}
