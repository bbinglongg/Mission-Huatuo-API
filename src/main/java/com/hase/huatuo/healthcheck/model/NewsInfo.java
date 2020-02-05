package com.hase.huatuo.healthcheck.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class NewsInfo {
    @Id
    @GeneratedValue()
    @Column
    private Long id;
    @Column
    private String title;
    @Column(columnDefinition="BLOB")
    private String  content;
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm")
    @JsonFormat(pattern="yyyy-MM-dd HH:mm",timezone="GMT+8")
    @Column
    private Date date;
    @Column
    private Integer priority;
    @Column
    private String source;
    @Column
    private String enable;
}
