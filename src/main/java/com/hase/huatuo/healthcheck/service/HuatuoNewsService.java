package com.hase.huatuo.healthcheck.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.hase.huatuo.healthcheck.dao.NewsInfoRepository;
import com.hase.huatuo.healthcheck.model.NewsInfo;
import com.hase.huatuo.healthcheck.model.request.NewsNotReadRequest;
import com.hase.huatuo.healthcheck.model.response.CommonResponse;
import com.hase.huatuo.healthcheck.model.response.ImportantNewsResponse;
import com.hase.huatuo.healthcheck.model.response.NewsImportantResponse;

@Service
public class HuatuoNewsService {

    @Autowired
    private NewsInfoRepository newsInfoRepository;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static final String HEALTH_STATISTIC_SQL = "select count(1) from news_info where enable='Y' and id not in (select i.item_id from item_status i, user_info u where i.staff_id=u.staff_Id and u.open_id=? and i.item_type='news')";

    public ResponseEntity<CommonResponse> getImportantNewsList(final NewsNotReadRequest newsNotReadRequest){
        CommonResponse commonResponse = new CommonResponse();
        List<ImportantNewsResponse>  importantNewsResponseList = new ArrayList<>();
        List<NewsInfo> importantNewsList = newsInfoRepository.getImportantNewsList();

        if(!CollectionUtils.isEmpty(importantNewsList)){
            importantNewsList.forEach(newsInfo -> {
                ImportantNewsResponse importantNewsResponse = new ImportantNewsResponse();
                importantNewsResponse.setNewsId(newsInfo.getId());
                importantNewsResponse.setNewsTitle(newsInfo.getTitle());
                importantNewsResponseList.add(importantNewsResponse);
            });
        }
        
        commonResponse.setCode("200");
        commonResponse.setMsg("success");
        NewsImportantResponse importantNewsResult = new NewsImportantResponse();
        importantNewsResult.setImportantNewsResponseList(importantNewsResponseList);
        importantNewsResult.setUnReadCount(enquiry(newsNotReadRequest));
        commonResponse.setReturnObject(importantNewsResult);
        return ResponseEntity.ok(commonResponse);
    }
    
    public int enquiry(final NewsNotReadRequest newsNotReadRequest){
    	
    	System.out.println("openId:"+newsNotReadRequest.getOpenId());
        
        int num = jdbcTemplate.queryForObject(HEALTH_STATISTIC_SQL, new Object[]{newsNotReadRequest.getOpenId()}, Integer.class);

        return num;
    }
}
