package com.hase.huatuo.healthcheck.model.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class NewsDetailRequest {
    @NotBlank
    private String openId;
    @NotBlank
    private String newsId;
}
