package com.hase.huatuo.healthcheck.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.hase.huatuo.healthcheck.dao.entity.NewsInfoEntity;
import com.hase.huatuo.healthcheck.model.request.NewsInfoListRequestBody;
import com.hase.huatuo.healthcheck.model.response.NewsInfoListResponse;

@Service
public class NewsInfoListService {

//	@Autowired
//	private NewsInfoRepository newsInfoRepository;
	@PersistenceContext
    private EntityManager entityManager;
	
	public NewsInfoListResponse getNewsInfoList(NewsInfoListRequestBody newsInfoListRequestBody) {
		NewsInfoListResponse newsInfoListResponse = new NewsInfoListResponse();
		
		try {
			String sql ="select n.id, n.title, n.priority, n.date, n.source, i.a from news_info n left join (select i.item_id, 'Y' a from item_status i, user_info u where i.staff_id=u.staff_id and u.open_id='" 
			+ newsInfoListRequestBody.getOpenId() 
			+ "' and i.item_type='news') i on i.item_id=n.id where n.enable='Y' order by date DESC";
			
			Query query = entityManager.createNativeQuery(sql, NewsInfoEntity.class);  
			List<NewsInfoEntity> rows = query.getResultList(); 
	
			newsInfoListResponse.setNewsInfoList(rows);
			
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
		return newsInfoListResponse;
	}
	
}
