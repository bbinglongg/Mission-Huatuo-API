package com.hase.huatuo.healthcheck.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.hase.huatuo.healthcheck.dao.LocationRepository;
import com.hase.huatuo.healthcheck.dao.NotifyRepository;
import com.hase.huatuo.healthcheck.model.Location;
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

import java.util.List;
import java.util.Optional;

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
		try {
//			deleteHealthInfo(healthPostBody);
			deleteHealthInfoHql(healthPostBody);
		} catch (Exception e) {
			e.printStackTrace();
		}
		addHealthInfo(healthPostBody);
		response.setErrorCode("000");
		return response;
	}

	public void deleteHealthInfoHql(HealthPostBody healthPostBody) {
		Transaction tx = null;
		Session session = null;
//		SessionFactory sessionFactory = null;
		
		
		HibernateEntityManager hEntityManager = (HibernateEntityManager)entityManager;
        session = hEntityManager.getSession();


		try {
			tx = session.getTransaction();
			session.beginTransaction();
			Query query = session.createQuery("delete HealthInfo s where s.id.staffID=?0");
			query.setParameter(0, healthPostBody.getStaffId());
//			query.
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
//		pi.setWorkplace(workplace);
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
	}

	public HealthRequestResponse getPersonHealth(HealthRequest healthRequest) {
		HealthRequestResponse response = new HealthRequestResponse();

		return response;
	}
}
