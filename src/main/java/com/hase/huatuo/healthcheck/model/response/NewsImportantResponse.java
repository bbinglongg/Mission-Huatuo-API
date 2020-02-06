package com.hase.huatuo.healthcheck.model.response;

import java.util.List;

import com.hase.huatuo.healthcheck.model.HealthInfo;
import com.hase.huatuo.healthcheck.model.NewsInfo;

public class NewsImportantResponse {
	
	private List<ImportantNewsResponse> importantNewsResponseList;
	
	private int unReadCount;

	public List<ImportantNewsResponse> getImportantNewsResponseList() {
		return importantNewsResponseList;
	}

	public void setImportantNewsResponseList(List<ImportantNewsResponse> importantNewsResponseList) {
		this.importantNewsResponseList = importantNewsResponseList;
	}

	public int getUnReadCount() {
		return unReadCount;
	}

	public void setUnReadCount(int unReadCount) {
		this.unReadCount = unReadCount;
	}
	
}
