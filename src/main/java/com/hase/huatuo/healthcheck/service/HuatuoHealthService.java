package com.hase.huatuo.healthcheck.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hase.huatuo.healthcheck.dao.LocationRepository;
import com.hase.huatuo.healthcheck.dao.NotifyRepository;
import com.hase.huatuo.healthcheck.model.Location;
import com.hase.huatuo.healthcheck.utils.MailUtils;
import com.hase.huatuo.healthcheck.utils.SMSUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jpa.HibernateEntityManager;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hase.huatuo.healthcheck.dao.PersonHealthInfoRepository;
import com.hase.huatuo.healthcheck.model.HealthInfo;
import com.hase.huatuo.healthcheck.model.healthPK;
import com.hase.huatuo.healthcheck.model.request.HealthPostBody;
import com.hase.huatuo.healthcheck.model.request.HealthRequest;
import com.hase.huatuo.healthcheck.model.response.HealthPostResponse;
import com.hase.huatuo.healthcheck.model.response.HealthRequestResponse;
import org.springframework.util.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class HuatuoHealthService {

	@Autowired
	private PersonHealthInfoRepository recordsRepository;

	@Autowired
	private NotifyRepository notifyRepository;

	@Autowired
	private LocationRepository locationRepository;

	@PersistenceContext
    private EntityManager entityManager;

	public HealthPostResponse setPersonHealth(HealthPostBody healthPostBody) {
		HealthPostResponse response = new HealthPostResponse();
        List<HealthInfo> healthInfos = recordsRepository.searchSavedRecordByStaffId(healthPostBody.getStaffId());
		//若帮别人填，保留高严重性的
		if(healthPostBody.getStaffId() != healthPostBody.getReporter()){
            if(healthInfos.size() > 0 ){
                int savedRecordStatus = Integer.valueOf(healthInfos.get(0).getHealthStatus());
                int newReportStatus = Integer.valueOf(healthPostBody.getHealthStatus());
                if(savedRecordStatus < newReportStatus){ //之前保存的更严重=>不用保存当前记录
                    response.setErrorCode("000");
                    return response;
                }
            }
		}
		//自己报告的或帮别人上报更严重的记录 一律以新提交的为准，删除旧记录保存新记录
        updateRecord(healthInfos,healthPostBody);
//		try {
//			deleteHealthInfoHql(healthPostBody);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		addHealthInfo(healthPostBody);
		response.setErrorCode("000");
		return response;
	}
    public void updateRecord(List<HealthInfo> savedRecords,HealthPostBody healthPostBody){
        Set<String> savedWorkplaces = new HashSet<>();
        if(savedRecords.size()>0){
            savedRecords.stream().forEach(one->{
                savedWorkplaces.add(one.getId().getWorkplace());
            });
        }
        String workplace = healthPostBody.getWorkplace();
        String[] submitedWorkplaceArray = workplace.split(",");
        HealthInfo pi = new HealthInfo();
        pi.setId(new healthPK());
        pi.setCity(healthPostBody.getCity());
        pi.setDepartment(healthPostBody.getDepartment());
        pi.setHealthStatus(healthPostBody.getHealthStatus());
        pi.setMobileNum(healthPostBody.getMobileNum());
        pi.setOpenId(healthPostBody.getOpenId());
        pi.setReporter(healthPostBody.getReporter());
        pi.getId().setStaffID(healthPostBody.getStaffId());
        pi.setOther(healthPostBody.getOther());
        for (int i = 0; i < submitedWorkplaceArray.length; i++) {
            pi.getId().setWorkplace(submitedWorkplaceArray[i]);
            recordsRepository.save(pi);
            if(savedWorkplaces.contains(submitedWorkplaceArray[i])){
                savedWorkplaces.remove(submitedWorkplaceArray[i]);
            }
            String healthStatus = healthPostBody.getHealthStatus();
            if ("1".equalsIgnoreCase(healthStatus) || "2".equalsIgnoreCase(healthStatus)) {
                caseNotifyManger(submitedWorkplaceArray[i], healthPostBody.getHealthStatus());
            }
        }
        if(savedWorkplaces.size() > 0){
            for(String place : savedWorkplaces){
                pi.getId().setWorkplace(place);
                recordsRepository.delete(pi);
            }
        }
        recordsRepository.flush();
	}


	public void deleteHealthInfoHql(HealthPostBody healthPostBody) {
//	    recordsRepository.deleteHealthInfoHql(healthPostBody.getStaffId());
//	    recordsRepository.flush();
		Transaction tx = null;
		Session session = null;
		HibernateEntityManager hEntityManager = (HibernateEntityManager)entityManager;
        session = hEntityManager.getSession();
		try {
			tx = session.getTransaction();
			session.beginTransaction();
			Query query = session.createQuery("delete HealthInfo s where s.id.staffID=?0");
			query.setParameter(0, healthPostBody.getStaffId());
			query.executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
		}
	}

	public void deleteHealthInfo(HealthPostBody healthPostBody) {
		HealthInfo pi = new HealthInfo();
		pi.setId(new healthPK());
		pi.getId().setStaffID(healthPostBody.getStaffId());
		recordsRepository.delete(pi);
	}

	public void addHealthInfo(HealthPostBody healthPostBody) {
		String workplace = healthPostBody.getWorkplace();
		String[] list = workplace.split(",");
		HealthInfo pi = new HealthInfo();
		pi.setId(new healthPK());
		pi.setCity(healthPostBody.getCity());
		pi.setDepartment(healthPostBody.getDepartment());
		pi.setHealthStatus(healthPostBody.getHealthStatus());
		pi.setMobileNum(healthPostBody.getMobileNum());
		pi.setOpenId(healthPostBody.getOpenId());
		pi.setReporter(healthPostBody.getReporter());
		pi.getId().setStaffID(healthPostBody.getStaffId());
		pi.setOther(healthPostBody.getOther());
		for (int i = 0; i < list.length; i++) {
			pi.getId().setWorkplace(list[i]);
			recordsRepository.save(pi);
			String healthStatus = healthPostBody.getHealthStatus();
			if ("1".equalsIgnoreCase(healthStatus) || "2".equalsIgnoreCase(healthStatus)) {
				caseNotifyManger(list[i], healthPostBody.getHealthStatus());
			}
		}
		recordsRepository.flush();
	}

	private void caseNotifyManger(String workplace, String healthStatus) {
		List<String> managerStaffMobileNumbers = notifyRepository.notifyStaffMobileNumbers();
		if (!CollectionUtils.isEmpty(managerStaffMobileNumbers)) {
			String managerStaffMobileNumbersString = StringUtils.join(managerStaffMobileNumbers, ",");
			String statusText = "1".equalsIgnoreCase(healthStatus)? "CONFIRM":"SUSPECT";
			Optional<Location> locationOptional = locationRepository.findById(workplace);
			if(locationOptional.isPresent()){
				Location location = locationOptional.get();
				SMSUtils.sendSMSHuaTuoReport(managerStaffMobileNumbersString, statusText, location.getCity(), location.getWorkplaceEnName());
			}
		}
		List<String> managerStaffEmailAddress = notifyRepository.notifyStaffEmailAddress();
		if (!CollectionUtils.isEmpty(managerStaffEmailAddress)) {
			String statusText = "1".equalsIgnoreCase(healthStatus)? "CONFIRM":"SUSPECT";
			Optional<Location> locationOptional = locationRepository.findById(workplace);
			if(locationOptional.isPresent()){
				Location location = locationOptional.get();
				String position = location.getCity()+" - "+location.getWorkplaceEnName();
				MailUtils.sendNotifyEmail(managerStaffEmailAddress,statusText,position);
			}
		}
	}

	public HealthRequestResponse getPersonHealth(HealthRequest healthRequest) {
		HealthRequestResponse response = new HealthRequestResponse();

		return response;
	}
}
