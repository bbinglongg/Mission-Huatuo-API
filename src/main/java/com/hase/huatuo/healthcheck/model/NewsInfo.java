package com.hase.huatuo.healthcheck.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Data
public class NewsInfo {
    @Id
    @GeneratedValue()
    @Column
    private Long id;
    @Column
    private String title;
    @Column(columnDefinition="blob")
    private String  content;
    @Column
    private Date date;
    @Column
    private int priority;
    @Column
    private String source;
    @Column
    private String enable;
}
