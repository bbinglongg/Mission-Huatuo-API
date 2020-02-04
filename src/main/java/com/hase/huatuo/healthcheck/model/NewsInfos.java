package com.hase.huatuo.healthcheck.model;

import java.io.Serializable;

public class NewsInfos implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
//	private String staff_id;
	private String id;
	private String title;
	private String priority;
	private String date;
	private String source;
	private String a;
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
