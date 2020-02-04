package com.hase.huatuo.healthcheck.service;

import com.hase.huatuo.healthcheck.dao.NewsInfoRepository;
import com.hase.huatuo.healthcheck.model.NewsInfo;
import com.hase.huatuo.healthcheck.model.response.CommonResponse;
import com.hase.huatuo.healthcheck.model.response.ImportantNewsResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

@Service
public class HuatuoNewsService {

    @Autowired
    private NewsInfoRepository newsInfoRepository;

    public ResponseEntity<CommonResponse> getImportantNewsList(){
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
        commonResponse.setReturnObject(importantNewsResponseList);
        return ResponseEntity.ok(commonResponse);
    }
}
