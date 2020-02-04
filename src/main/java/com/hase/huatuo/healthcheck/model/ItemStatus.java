package com.hase.huatuo.healthcheck.model;


import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class ItemStatus {
    @Id
    @Column
    private String staffId;
    @Column
    private String itemId;
    @Column
    private String itemType;
}
