package com.hase.huatuo.healthcheck.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "RequestBody for datadict request")
public class DatadictGetRequest {
    @ApiModelProperty(value = "sdfsdfsdfsdf", notes = "get from wechat", dataType = "java.lang.String")
    private String openId;

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}

}
