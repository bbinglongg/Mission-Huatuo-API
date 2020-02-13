package com.hase.huatuo.healthcheck.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
public class SurveyFormQuestionItem implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long itemId;
    @Column
    private Long questionId;
    @Column
    private String itemNo;
    @Column
    private String itemTextCn;
    @Column
    private String itemTextEn;
    @Column
    private String needSubItem;
    @Column
    private String subItemRule;
}
