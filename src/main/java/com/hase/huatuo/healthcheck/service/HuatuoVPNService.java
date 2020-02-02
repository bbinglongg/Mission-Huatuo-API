package com.hase.huatuo.healthcheck.service;

import com.hase.huatuo.healthcheck.dao.VPNInfoRepository;
import com.hase.huatuo.healthcheck.model.VpnReport;
import com.hase.huatuo.healthcheck.model.request.VpnReportRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hase.huatuo.healthcheck.model.VpnInfo;
import com.hase.huatuo.healthcheck.model.request.VpnRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
public class HuatuoVPNService {

	@Autowired
	private VPNInfoRepository vpnInfoRepository;

	public void submitVPNState(VpnRequest vpnRequest) {
		VpnInfo vpnInfo = vpnRequest.getVpnInfo();
		vpnInfoRepository.saveAndFlush(vpnInfo);
	}

	public List<VpnReport> loadVPNStateDashboard(VpnReportRequest vpnReportRequest) throws ParseException {
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
