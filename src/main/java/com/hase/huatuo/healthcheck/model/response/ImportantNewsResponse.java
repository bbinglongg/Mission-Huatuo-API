package com.hase.huatuo.healthcheck.model.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ImportantNewsResponse {
    private String newsId;
    private String newsTitle;
}
