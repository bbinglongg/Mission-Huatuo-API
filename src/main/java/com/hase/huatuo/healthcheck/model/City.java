package com.hase.huatuo.healthcheck.model;

import lombok.Data;

import java.util.List;

@Data
public class City {
    private String cityShortName;
    private String cityName;
    private String onlyHasSubBranch;
    private List<Branch> branches;
}
