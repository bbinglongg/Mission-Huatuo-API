package com.hase.huatuo.healthcheck.service;

import com.hase.huatuo.healthcheck.dao.*;
import com.hase.huatuo.healthcheck.helper.ErrorHandleHelper;
import com.hase.huatuo.healthcheck.model.NewsInfo;
import com.hase.huatuo.healthcheck.model.NotifyStaffView;
import com.hase.huatuo.healthcheck.model.VpnInfo;
import com.hase.huatuo.healthcheck.model.VpnReport;
import com.hase.huatuo.healthcheck.model.request.VpnReportQueryRequest;
import com.hase.huatuo.healthcheck.model.request.VpnReportRequest;
import com.hase.huatuo.healthcheck.model.request.VpnRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class HuatuoAdminService {

	@Autowired
	private VPNInfoRepository vpnInfoRepository;

	@Autowired
	private NewsInfoRepository newsInfoRepository;

	@Autowired
	private NotifyStaffListRepository notifyStaffListRepository;

	@Autowired
	private NotifyRepository notifyRepository;

	@Autowired
	private StaffListRepository staffListRepository;

	/**
	 * Query Vpn Info
	 * @param map
	 * @return
	 */
	public List<VpnInfo> queryVPNReport(Map<String,Object> map) {
		Object staffId = map.get("staffId");
		Object location = map.get("location");
		Object internetISP = map.get("internetISP");
		Object lastUpatetime = map.get("lastUpatetime");
		VpnReportQueryRequest vpnReportQueryRequest = new VpnReportQueryRequest();
		if (staffId != null && !"".equals(staffId)) {
			vpnReportQueryRequest.setStaffId(Integer.parseInt(String.valueOf(staffId)));
		}
		if (location != null && !"".equals(location)) {
			vpnReportQueryRequest.setLocation("%"+String.valueOf(location)+"%");
		}
		if (internetISP != null && !"".equals(internetISP)) {
			vpnReportQueryRequest.setInternetISP(String.valueOf(internetISP));
		}
		if (lastUpatetime != null && !"".equals(lastUpatetime)) {
			vpnReportQueryRequest.setLastUpatetime(String.valueOf(lastUpatetime+"%"));
		}

		List<VpnInfo> vpnInfos = vpnInfoRepository.vpnReportView(vpnReportQueryRequest.getStaffId()
				, vpnReportQueryRequest.getLocation(),
				vpnReportQueryRequest.getInternetISP(),
				vpnReportQueryRequest.getLastUpatetime());
		return vpnInfos;
	}

	/**
	 * Query Notify Staff
	 * @return
	 */
	public List<NotifyStaffView> findNotifyStaff() {
		return notifyStaffListRepository.findAllStaff();
	}

	/**
	 * Save Notify Staff
	 * @param notifyStaffView
	 */
	@Transactional
	public void saveNotifyStaff(NotifyStaffView notifyStaffView) {
		notifyRepository.save(notifyStaffView.getNotify());
		staffListRepository.save(notifyStaffView.getStaffListEntity());
	}

	/**
	 * Delete Notify Staff
	 * @param staffId
	 */
	@Transactional
	public void deleteNotifyStaff(String staffId) {
		notifyRepository.deleteById(staffId);
		staffListRepository.deleteById(staffId);
	}

	/**
	 * query all news
	 * @param map
	 * @return
	 */
	public List<NewsInfo> findNewsByParms(Map<String,Object> map) {
		return newsInfoRepository.findAll();
	}

	/**
	 * Save news
	 * @param newsInfo
	 */
	public void saveNews(NewsInfo newsInfo) {
		newsInfoRepository.save(newsInfo);
	}

	/**
	 * Delete news
	 * @param id
	 */
	public void deleteNews(String id) {
		newsInfoRepository.deleteById(id);
	}
}
