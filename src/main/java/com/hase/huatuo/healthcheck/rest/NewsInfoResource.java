package com.hase.huatuo.healthcheck.rest;

import com.hase.huatuo.healthcheck.dao.NewsStatusRepository;
import com.hase.huatuo.healthcheck.dao.NewsInfoRepository;
import com.hase.huatuo.healthcheck.error.ErrorMessage;
import com.hase.huatuo.healthcheck.model.ItemStatus;
import com.hase.huatuo.healthcheck.model.NewsInfo;
import com.hase.huatuo.healthcheck.model.request.NewsDetailRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping({"/api","/api/v1"})
public class NewsInfoResource {
    @Autowired
    private NewsInfoRepository newsInfo;
    @Autowired
    private NewsStatusRepository newsStatusRep;

    @PostMapping(path = "/news/detail")
    public ResponseEntity<String> upDateNewsInfo(@RequestBody NewsDetailRequest detail)
    {
        final String newsId = detail.getNewsId();
        final String staffId = detail.getStaffId();
       ItemStatus itemStatus  = newsStatusRep.getItemStatusBy(newsId,staffId);
       if("N".equals(itemStatus.getItemType())){
           itemStatus.setItemType("Y");
           newsStatusRep.saveAndFlush(itemStatus);
       }

       Optional<NewsInfo> info = newsInfo.findById(newsId);
       ResponseEntity response;
        if("N".equals(info.get().getEnable())){
           response = new ResponseEntity(new ErrorMessage("error", "item new readable")
                   ,HttpStatus.BAD_REQUEST);
        }else {
            response = ResponseEntity.ok(info.get().getContent());
        }
       return response;
    }


}
