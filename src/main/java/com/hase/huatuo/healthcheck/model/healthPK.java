package com.hase.huatuo.healthcheck.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class healthPK implements Serializable {

	@Column(name="staff_Id")
	private String staffID;
	@Column(name="workplace")
	private String workplace;
	public String getStaffID() {
		return staffID;
	}
	public void setStaffID(String staffID) {
		this.staffID = staffID;
	}
	public String getWorkplace() {
		return workplace;
	}
	public void setWorkplace(String workplace) {
		this.workplace = workplace;
	}
	

}
