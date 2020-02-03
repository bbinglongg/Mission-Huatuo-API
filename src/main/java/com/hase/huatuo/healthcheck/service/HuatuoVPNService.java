package com.hase.huatuo.healthcheck.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hase.huatuo.healthcheck.dao.VPNInfoRepository;
import com.hase.huatuo.healthcheck.helper.ErrorHandleHelper;
import com.hase.huatuo.healthcheck.model.VpnInfo;
import com.hase.huatuo.healthcheck.model.VpnReport;
import com.hase.huatuo.healthcheck.model.request.VpnReportRequest;
import com.hase.huatuo.healthcheck.model.request.VpnRequest;

@Service
public class HuatuoVPNService {

	@Autowired
	private VPNInfoRepository vpnInfoRepository;

	public void submitVPNState(VpnRequest vpnRequest) {
		try {
			VpnInfo vpnInfo = vpnRequest.getVpnInfo();
			vpnInfo.setLastUpdateDatetime(new Date());
			vpnInfoRepository.saveAndFlush(vpnInfo);
		} catch (Exception e) {
			ErrorHandleHelper.getInstance().throwTechnicalRestException("server error", "save data to db error", null);
		}
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
