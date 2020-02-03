package com.hase.huatuo.healthcheck.model.response;

import java.util.List;

public class AreaReport {
    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public List<BuildingReport> getBuildingReports() {
        return buildingReports;
    }

    public void setBuildingReports(List<BuildingReport> buildingReports) {
        this.buildingReports = buildingReports;
    }

    private String area;
    private List<BuildingReport> buildingReports;
}
