package com.hase.huatuo.healthcheck.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class SurveyFormQuestion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long questionId;
    @Column
    private Long formId;
    @Column
    private String questionTitleCn;
    @Column
    private String questionTitleEn;
    @Column
    private String questionNo;
    @Column
    private String questionType;
    @Column
    private String rule;

    @OneToMany()
    @JoinColumn(name="questionId", referencedColumnName = "questionId")
    private List<SurveyFormQuestionItem> questionItems;
}
