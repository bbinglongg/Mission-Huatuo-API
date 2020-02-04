package com.hase.huatuo.healthcheck.model.request;

import javax.validation.constraints.NotBlank;

public class NewsDetailRequest {
    @NotBlank
    private String staffId;
    @NotBlank
    private String newsId;

    public String getStaffId() {
        return staffId;
    }

    public void setStaffId(String staffId) {
        this.staffId = staffId;
    }

    public String getNewsId() {
        return newsId;
    }

    public void setNewsId(String newsId) {
        this.newsId = newsId;
    }
}
