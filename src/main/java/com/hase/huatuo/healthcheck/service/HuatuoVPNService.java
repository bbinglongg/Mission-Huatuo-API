package com.hase.huatuo.healthcheck.service;

import com.hase.huatuo.healthcheck.dao.VPNInfoRepository;
import com.hase.huatuo.healthcheck.model.VPNReport;
import com.hase.huatuo.healthcheck.model.request.VPNReportRequest;
import com.hase.huatuo.healthcheck.model.response.VPNReportResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hase.huatuo.healthcheck.model.VPNStateInfo;
import com.hase.huatuo.healthcheck.model.request.VPNStatePostBody;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class HuatuoVPNService {

	@Autowired
	private VPNInfoRepository vpnInfoRepository;

	public void submitVPNState(VPNStatePostBody vpnStatePostBody) {
		VPNStateInfo vpnStateInfo = vpnStatePostBody.getVpnStateInfo();
		vpnInfoRepository.saveAndFlush(vpnStateInfo);
	}

	public List<VPNReport> loadVPNStateDashboard(VPNReportRequest vpnReportRequest) throws ParseException {
		Integer day = vpnReportRequest.getDay();
		Date from;
		if (day != null && day > 0) {
			Calendar now = Calendar.getInstance();
			now.set(Calendar.DATE,now.get(Calendar.DATE)-day);
			from = now.getTime();
		} else {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			from = simpleDateFormat.parse("2019-12-01");
		}
		return vpnInfoRepository.vpnReport(from);
	}
}
