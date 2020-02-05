package com.hase.huatuo.healthcheck.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.hase.huatuo.healthcheck.model.NewsInfoList;
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
			+ "' and i.item_type='news') i on i.item_id=n.id where n.enable='Y' order by priority DESC,date DESC";
			
//			Query query = entityManager.createNativeQuery(sql, NewsInfoEntity.class);  
//			List<NewsInfoEntity> rows = query.getResultList(); 
			
			Query query = entityManager.createNativeQuery(sql);  
			List rows = query.getResultList(); 
			List<NewsInfoList> newsInfoList = new ArrayList<NewsInfoList>();
			if(rows != null) {
				for(Object obj: rows) {
					NewsInfoList item = new NewsInfoList();
					Object[] cells = (Object[]) obj;
					
					item.setId(""+cells[0]);
					item.setTitle(""+cells[1]);
					item.setPriority(""+cells[2]);
					item.setDate(""+cells[3]);
					item.setSource(""+cells[4]);
					item.setA(""+cells[5]);
		            	
					newsInfoList.add(item);
				}
			}
	
			newsInfoListResponse.setNewsInfoList(newsInfoList);
			
    	} catch(Exception e) {
    		e.printStackTrace();
    	}
		return newsInfoListResponse;
	}
	
}
