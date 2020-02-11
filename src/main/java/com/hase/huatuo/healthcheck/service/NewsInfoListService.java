package com.hase.huatuo.healthcheck.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import com.hase.huatuo.healthcheck.utils.AppConfigUtils;
import org.apache.commons.lang3.StringUtils;
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
		if(StringUtils.isEmpty(newsInfoListRequestBody.getAppId())){
			newsInfoListRequestBody.setAppId(AppConfigUtils.getAppIdFromProperties("haseIT"));
		}
		NewsInfoListResponse newsInfoListResponse = new NewsInfoListResponse();
		
		try {
			String sql ="select n.id, n.title, n.priority, DATE_FORMAT(n.date,'%Y-%m-%d %H:%i') as date, n.source, i.a from news_info n left join (select i.news_id, 'Y' a from news_info_read_record i, user_info u where i.staff_id=u.staff_id and u.open_id='"
			+ newsInfoListRequestBody.getOpenId() 
			+ "' and i.app_id='"+newsInfoListRequestBody.getAppId()+"') i on i.news_id=n.id where n.enable='Y' and n.app_id='"+newsInfoListRequestBody.getAppId()+"' order by priority DESC,date DESC";
			
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
