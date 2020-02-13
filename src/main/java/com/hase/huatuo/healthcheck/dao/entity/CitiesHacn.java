package com.hase.huatuo.healthcheck.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class CitiesHacn implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long cityId;
    @Column
    private String cityShortName;
    @Column
    private String cityName;
    @Column
    private String onlyHasSubBranch;

    @OneToMany()
    @JoinColumn(name = "cityShortName", referencedColumnName = "cityShortName")
    private List<BranchesHacn> branches;
}
