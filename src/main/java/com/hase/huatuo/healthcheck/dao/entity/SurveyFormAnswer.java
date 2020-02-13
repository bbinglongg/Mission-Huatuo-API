package com.hase.huatuo.healthcheck.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
public class SurveyFormAnswer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long answerId;
    @Column
    private Long formId;
    @Column
    private String staffId;
    @Column
    private Date lastUpdateDatetime;
    @Column
    private String answer1;
    @Column
    private String answer2;
    @Column
    private String answer3;
    @Column
    private String answer4;
    @Column
    private String answer5;
    @Column
    private String answer6;
    @Column
    private String answer7;
    @Column
    private String answer8;
    @Column
    private String answer9;
    @Column
    private String answer10;
    @Column
    private String answer11;
    @Column
    private String answer12;
    @Column
    private String answer13;
    @Column
    private String answer14;
    @Column
    private String answer15;
    @Column
    private String answer16;
    @Column
    private String answer17;
    @Column
    private String answer18;
    @Column
    private String answer19;
    @Column
    private String answer20;
    @Column
    private String answer21;
    @Column
    private String answer22;
    @Column
    private String answer23;
    @Column
    private String answer24;
    @Column
    private String answer25;
    @Column
    private String answer26;
    @Column
    private String answer27;
    @Column
    private String answer28;
    @Column
    private String answer29;
    @Column
    private String answer30;
    @Column
    private String answer31;
    @Column
    private String answer32;
    @Column
    private String answer33;
    @Column
    private String answer34;
    @Column
    private String answer35;
    @Column
    private String answer36;
    @Column
    private String answer37;
    @Column
    private String answer38;
    @Column
    private String answer39;
    @Column
    private String answer40;
    @Column
    private String answer41;
    @Column
    private String answer42;
    @Column
    private String answer43;
    @Column
    private String answer44;
    @Column
    private String answer45;
    @Column
    private String answer46;
    @Column
    private String answer47;
    @Column
    private String answer48;
    @Column
    private String answer49;
    @Column
    private String answer50;
    @Column
    private String answer51;
    @Column
    private String answer52;
    @Column
    private String answer53;
    @Column
    private String answer54;
    @Column
    private String answer55;
    @Column
    private String answer56;
    @Column
    private String answer57;
    @Column
    private String answer58;
    @Column
    private String answer59;
    @Column
    private String answer60;
    @Column
    private String answer61;
    @Column
    private String answer62;
    @Column
    private String answer63;
    @Column
    private String answer64;
    @Column
    private String answer65;
    @Column
    private String answer66;
    @Column
    private String answer67;
    @Column
    private String answer68;
    @Column
    private String answer69;
    @Column
    private String answer70;
    @Column
    private String answer71;
    @Column
    private String answer72;
    @Column
    private String answer73;
    @Column
    private String answer74;
    @Column
    private String answer75;
    @Column
    private String answer76;
    @Column
    private String answer77;
    @Column
    private String answer78;
    @Column
    private String answer79;
    @Column
    private String answer80;
    @Column
    private String answer81;
    @Column
    private String answer82;
    @Column
    private String answer83;
    @Column
    private String answer84;
    @Column
    private String answer85;
    @Column
    private String answer86;
    @Column
    private String answer87;
    @Column
    private String answer88;
    @Column
    private String answer89;
    @Column
    private String answer90;
    @Column
    private String answer91;
    @Column
    private String answer92;
    @Column
    private String answer93;
    @Column
    private String answer94;
    @Column
    private String answer95;
    @Column
    private String answer96;
    @Column
    private String answer97;
    @Column
    private String answer98;
    @Column
    private String answer99;
    @Column
    private String answer100;
}
