package com.hase.huatuo.healthcheck.dao;

import com.hase.huatuo.healthcheck.model.ItemStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface NewsStatusRepository
        extends JpaRepository<ItemStatus, String> {
    @Query(value = "select u from ItemStatus u where u.itemId = ?1  and u.staffId = ?2")
    ItemStatus getItemStatusBy(String itemId,Long staffId);
    ItemStatus getItemStatusByItemId(String ItemId);
}
