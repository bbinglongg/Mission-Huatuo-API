package com.hase.huatuo.healthcheck.service;

import java.util.ArrayList;
import java.util.List;

import com.hase.huatuo.healthcheck.model.request.NewsDetailRequest;
import com.hase.huatuo.healthcheck.utils.AppConfigUtils;
import org.apache.commons.lang3.StringUtils;
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

    public static final String NEWS_INFO_READ_RECORD_SQL = "select count(1) from news_info_read_record where staff_id=? and news_id=? and app_id=?";

    public static final String HEALTH_UPDATE_SQL = "insert into news_info_read_record values (?, ?, ?)";

    @Autowired
    private NewsInfoRepository newsInfoRepository;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public static final String HEALTH_STATISTIC_SQL = "select count(1) from news_info where enable='Y' and app_id =? and id not in (select i.news_id from news_info_read_record i, user_info u where i.staff_id=u.staff_Id and u.open_id=? and i.app_id=?)";

    public ResponseEntity<CommonResponse> getImportantNewsList(final NewsNotReadRequest newsNotReadRequest){
        if(StringUtils.isEmpty(newsNotReadRequest.getAppId())){
            newsNotReadRequest.setAppId(AppConfigUtils.getAppIdFromProperties("haseIT"));
        }
        CommonResponse commonResponse = new CommonResponse();
        List<ImportantNewsResponse>  importantNewsResponseList = new ArrayList<>();
        List<NewsInfo> importantNewsList = newsInfoRepository.getImportantNewsList(newsNotReadRequest.getAppId());

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
        
        int num = jdbcTemplate.queryForObject(HEALTH_STATISTIC_SQL, new Object[]{newsNotReadRequest.getAppId(),newsNotReadRequest.getOpenId(),newsNotReadRequest.getAppId()}, Integer.class);

        return num;
    }

    public NewsInfo getNewsDetail(NewsDetailRequest newsDetailRequest) {
        if(StringUtils.isEmpty(newsDetailRequest.getAppId())){
            newsDetailRequest.setAppId(AppConfigUtils.getAppIdFromProperties("haseIT"));
        }
        final String newsId = newsDetailRequest.getNewsId();
        final String appId = newsDetailRequest.getAppId();

        int num = jdbcTemplate.queryForObject(NEWS_INFO_READ_RECORD_SQL,
                new Object[] { newsDetailRequest.getStaffId(), newsDetailRequest.getNewsId(),appId }, Integer.class);

        if (num < 1) {
            jdbcTemplate.update(HEALTH_UPDATE_SQL,
                    new Object[] { appId,newsDetailRequest.getStaffId(), newsDetailRequest.getNewsId() });
        }

        NewsInfo info = newsInfoRepository.getNewsInfoByIdEquals(Long.valueOf(newsId));
        return info;
    }
}
