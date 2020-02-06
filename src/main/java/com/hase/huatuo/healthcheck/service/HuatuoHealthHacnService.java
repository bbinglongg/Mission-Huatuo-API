package com.hase.huatuo.healthcheck.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.jpa.HibernateEntityManager;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hase.huatuo.healthcheck.dao.HealthInfoHacnRepository;
import com.hase.huatuo.healthcheck.dao.entity.HealthInfoHacn;
import com.hase.huatuo.healthcheck.helper.ErrorHandleHelper;
import com.hase.huatuo.healthcheck.model.request.HealthInfoHacnEnquiryBody;
import com.hase.huatuo.healthcheck.model.request.HealthInfoHacnPostBody;
import com.hase.huatuo.healthcheck.model.response.HealthPostResponse;

@Service
public class HuatuoHealthHacnService {

	@Autowired
	private HealthInfoHacnRepository healthInfoHacnRepository;
	
	@PersistenceContext
    private EntityManager entityManager;
	
	public HealthPostResponse uploadHealthHacn(HealthInfoHacnPostBody body) {

		validation(body);
		
		HealthPostResponse response = new HealthPostResponse();
		
		if(checkRecordExsit(body)) {
			ErrorHandleHelper.getInstance().throwTechnicalRestException("server error", "you had reported today.", null);
		}
		
		updateHealthInfoStatistic(body);
		
		try {
			
			HealthInfoHacn healthInfoHacn = new HealthInfoHacn();
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(body); 
			
			healthInfoHacn = mapper.readValue(json, HealthInfoHacn.class);
			healthInfoHacn.setReportDate(getCurrentDate());
			healthInfoHacn.setSerailNumber(UUID.randomUUID().toString());
			healthInfoHacn.setIsStatistic("1");
			
			healthInfoHacnRepository.saveAndFlush(healthInfoHacn);
		} catch (Exception e) {
			ErrorHandleHelper.getInstance().throwTechnicalRestException("server error", "save data to db error", null);
		}
		
		response.setErrorCode("000");
		return response;
	}
	
	private String getCurrentDate() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String str = sdf.format(d);
		return str;
	}
	
	private boolean checkRecordExsit(HealthInfoHacnPostBody body) {
		try {
			HealthInfoHacn healthInfoHacn = new HealthInfoHacn();
			healthInfoHacn.setReportDate(getCurrentDate());
			healthInfoHacn.setOpenId(body.getOpenId());
			
			Example<HealthInfoHacn> example = Example.of(healthInfoHacn);
			
			List<HealthInfoHacn> list = healthInfoHacnRepository.findAll(example);
			if(list != null && list.size() > 0) {
				return true;
			}
		} catch (Exception e) {
			ErrorHandleHelper.getInstance().throwTechnicalRestException("server error", "get data error", null);
		}
		
		return false;
	}
	
	private void updateHealthInfoStatistic(HealthInfoHacnPostBody body) {
		Transaction tx = null;
		Session session = null;
		
		HibernateEntityManager hEntityManager = (HibernateEntityManager)entityManager;
        session = hEntityManager.getSession();

		try {
			tx = session.getTransaction();
			session.beginTransaction();
			Query query = session.createQuery("update HealthInfoHacn h set h.isStatistic=:sta where h.openId =:oid and h.staffId=:sid");
			query.setParameter("sta", "0");
			query.setParameter("oid", body.getOpenId());
			query.setParameter("sid", body.getStaffId());
			query.executeUpdate();
			tx.commit();
		} catch (HibernateException e) {
			tx.rollback();
			e.printStackTrace();
			ErrorHandleHelper.getInstance().throwTechnicalRestException("server error", "update data error", null);
		}
	}
	
	public HealthInfoHacn getHealthInfoHacn(HealthInfoHacnEnquiryBody enquiryBody) {
		if(enquiryBody.getOpenId() == null || enquiryBody.getOpenId().equals("")) {
			ErrorHandleHelper.getInstance().throwTechnicalRestException("Bad Request", "openid error", null);
		}
		
		HealthInfoHacn response = new HealthInfoHacn();
		
		try {
			HealthInfoHacn healthInfoHacn = new HealthInfoHacn();
			healthInfoHacn.setReportDate(enquiryBody.getReportDate());
			healthInfoHacn.setOpenId(enquiryBody.getOpenId());
			
			Example<HealthInfoHacn> example = Example.of(healthInfoHacn);
			
			List<HealthInfoHacn> list = healthInfoHacnRepository.findAll(example);
			if(list != null && list.size() > 0) {
				HealthInfoHacn item = list.get(0);
				response = item;
			}
		} catch (Exception e) {
			ErrorHandleHelper.getInstance().throwTechnicalRestException("server error", "get data error", null);
		}
		return response;
	}
	
	
	private void validation(HealthInfoHacnPostBody body) {
		if(body == null ) {
    		ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "request body is null", null);
    	}
    	
    	if(body.getStaffId() == null ||
    			body.getStaffId().length() < 5) {
    		ErrorHandleHelper.getInstance().throwBadRequestRestException("Bad Request", "staff ID is error", null);
    	}
	}
	
}
