package com.hase.huatuo.healthcheck.rest;

import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hase.huatuo.healthcheck.dao.NewsInfoRepository;
import com.hase.huatuo.healthcheck.dao.NewsStatusRepository;
import com.hase.huatuo.healthcheck.error.ErrorMessage;
import com.hase.huatuo.healthcheck.model.ItemStatus;
import com.hase.huatuo.healthcheck.model.NewsInfo;
import com.hase.huatuo.healthcheck.model.request.NewsDetailRequest;

@RestController
@RequestMapping({"/huatuo/api","/huatuo/api/v1","/api","/api/v1"})
public class NewsInfoResource {
	@Autowired
	private NewsInfoRepository newsInfo;

	public static final String HEALTH_STATISTIC_SQL = "select count(1) from news_info_read_record where staff_id=? and news_id=? and app_id=?";

	public static final String HEALTH_UPDATE_SQL = "insert into news_info_read_record values (?, ?, ?)";

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
    private NewsStatusRepository newsStatusRep;
	
	@PostMapping(path = "/news/detail")
	public ResponseEntity<String> upDateNewsInfo(@RequestBody @Valid NewsDetailRequest detail) {
		if(StringUtils.isEmpty(detail.getAppId())){
			detail.setAppId("wx9812117be87d24d2");
		}
		final String newsId = detail.getNewsId();
		final String openId = detail.getOpenId();
		final String appId = detail.getAppId();

//        
//        
//       ItemStatus itemStatus  = newsStatusRep.getItemStatusByOpenId(newsId,openId);
//       if(itemStatus==null){
//    	   ItemStatus newItemStatus = new ItemStatus();
//    	   newItemStatus.setItemType("news");
//    	   newItemStatus.setItemId(newsId);
//    	   newItemStatus.setStaffId(Long.valueOf(detail.getStaffId()));
//           newsStatusRep.saveAndFlush(newItemStatus);
//       }

		int num = jdbcTemplate.queryForObject(HEALTH_STATISTIC_SQL,
				new Object[] { detail.getStaffId(), detail.getNewsId(),appId }, Integer.class);

		if (num < 1) {
			int result = jdbcTemplate.update(HEALTH_UPDATE_SQL,
					new Object[] { appId,detail.getStaffId(), detail.getNewsId() });
			
		}

		NewsInfo info = newsInfo.getNewsInfoByIdEquals(Long.valueOf(newsId));
		ResponseEntity response;
//		if ("N".equals(info.getEnable())) {
//			response = new ResponseEntity(new ErrorMessage("error", "item new readable"), HttpStatus.BAD_REQUEST);
//		} else {
			response = ResponseEntity.ok(info);
//		}
		return response;
	}

}
