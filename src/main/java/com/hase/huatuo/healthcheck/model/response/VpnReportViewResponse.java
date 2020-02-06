package com.hase.huatuo.healthcheck.model.response;

import com.hase.huatuo.healthcheck.model.VpnInfo;

import java.util.List;

public class VpnReportViewResponse {
	private List<VpnInfo> items;
	private Integer total;
	private Integer code;

	public List<VpnInfo> getItems() {
		return items;
	}

	public void setItems(List<VpnInfo> items) {
		this.items = items;
	}

	public Integer getTotal() {
		return total;
	}

	public void setTotal(Integer total) {
		this.total = total;
	}

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}
}
