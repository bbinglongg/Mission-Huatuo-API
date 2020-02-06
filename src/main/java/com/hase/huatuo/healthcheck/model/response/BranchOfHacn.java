package com.hase.huatuo.healthcheck.model.response;

public class BranchOfHacn {
    public String getConfirmed() {
        return confirmed;
    }

    public void setConfirmed(String confirmed) {
        this.confirmed = confirmed;
    }

    public String getSuspect() {
        return suspect;
    }

    public void setSuspect(String suspect) {
        this.suspect = suspect;
    }

    public String getFever() {
        return fever;
    }

    public void setFever(String fever) {
        this.fever = fever;
    }
    private String area;
    private String confirmed;
    private String suspect;
    private String fever;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
