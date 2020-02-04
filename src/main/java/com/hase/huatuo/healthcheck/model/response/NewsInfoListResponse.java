package com.hase.huatuo.healthcheck.model.response;

import java.util.List;

import com.hase.huatuo.healthcheck.dao.entity.NewsInfoEntity;

public class NewsInfoListResponse {
	
	private List<NewsInfoEntity> newsInfoList;

	public List<NewsInfoEntity> getNewsInfoList() {
		return newsInfoList;
	}

	public void setNewsInfoList(List<NewsInfoEntity> newsInfoList) {
		this.newsInfoList = newsInfoList;
	}
	
	
}
