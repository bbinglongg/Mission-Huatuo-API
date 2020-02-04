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
    private Long newsId;
    private String newsTitle;
}
