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

    public String getDeath() {
        return death;
    }

    public void setDeath(String death) {
        this.death = death;
    }

    private String area;
    private String confirmed;
    private String suspect;
    private String death;

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }
}
