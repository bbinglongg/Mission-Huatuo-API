package com.hase.huatuo.healthcheck.dao.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Data
public class BranchesHacn implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long branchId;
    @Column
    private String branchCode;
    @Column
    private String branchName;
    @Column
    private String branchType;
    @Column
    private String cityShortName;
}
