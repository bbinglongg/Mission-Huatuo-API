package com.hase.huatuo.healthcheck.model.request;

import com.hase.huatuo.healthcheck.model.VpnInfo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "RequestBody of VPN state submit")
public class VpnRequest {
	@ApiModelProperty(notes = "VPN state info", dataType = "com.hase.huatuo.healthcheck.model.VpnInfo")
	private VpnInfo vpnInfo;

	public VpnInfo getVpnInfo() {
		return vpnInfo;
	}

	public void setVpnInfo(VpnInfo vpnInfo) {
		this.vpnInfo = vpnInfo;
	}
}
