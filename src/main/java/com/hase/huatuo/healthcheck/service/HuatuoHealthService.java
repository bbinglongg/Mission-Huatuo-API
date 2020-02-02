package com.hase.huatuo.healthcheck.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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

@Service
public class HuatuoHealthService {

	@Autowired
	private PersonHealthInfoRepository recordsRepository;
	
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
		}
		recordsRepository.flush();
	}

	public HealthRequestResponse getPersonHealth(HealthRequest healthRequest) {
		HealthRequestResponse response = new HealthRequestResponse();

		return response;
	}
}
