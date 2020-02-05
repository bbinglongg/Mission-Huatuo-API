package com.hase.huatuo.healthcheck.model.response;

import java.util.List;

public class AreaReportForHacn {

    public List<BranchOfHacn> getBranches() {
        return branches;
    }

    public void setBranches(List<BranchOfHacn> branches) {
        this.branches = branches;
    }

    public List<BranchOfHacn> branches;

    public List<BranchOfHacn> getSubBranches() {
        return subBranches;
    }

    public void setSubBranches(List<BranchOfHacn> subBranches) {
        this.subBranches = subBranches;
    }

    public List<BranchOfHacn> subBranches;

    public String getActiveIsolation() {
        return activeIsolation;
    }

    public void setActiveIsolation(String activeIsolation) {
        this.activeIsolation = activeIsolation;
    }

    public String getPassiveIsolation() {
        return passiveIsolation;
    }

    public void setPassiveIsolation(String passiveIsolation) {
        this.passiveIsolation = passiveIsolation;
    }

    public String activeIsolation;
    public String passiveIsolation;
}
