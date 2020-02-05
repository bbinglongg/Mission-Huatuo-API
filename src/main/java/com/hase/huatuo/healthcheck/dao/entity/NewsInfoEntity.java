package com.hase.huatuo.healthcheck.dao.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity()
public class NewsInfoEntity implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
//	@Column(name = "staff_id")
//    private String staffId;
	@Id
    @Column(name="id")
    private String id;
    @Column(name="title")
    private String title;
    @Column(name="priority")
    private String priority;
    @Column(name="date")
    private String date;
    @Column(name="source")
    private String source;
    
    private String a;
    
//    @Transient
//	private boolean read;
    
    
//	public String getStaffId() {
//		return staffId;
//	}
//	public void setStaffId(String staffId) {
//		this.staffId = staffId;
//	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPriority() {
		return priority;
	}
	public void setPriority(String priority) {
		this.priority = priority;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getA() {
		return a;
	}
	public void setA(String a) {
		this.a = a;
	}
	
}
