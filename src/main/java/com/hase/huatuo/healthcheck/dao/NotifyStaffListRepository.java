package com.hase.huatuo.healthcheck.dao;

import com.hase.huatuo.healthcheck.model.Notify;
import com.hase.huatuo.healthcheck.model.NotifyStaffView;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotifyStaffListRepository extends JpaRepository<Notify,String> {

    @Query("SELECT new com.hase.huatuo.healthcheck.model.NotifyStaffView(n,s) from" +
            " com.hase.huatuo.healthcheck.model.Notify n , com.hase.huatuo.healthcheck.dao.entity.StaffListEntity s " +
            " where n.staffId = s.staffId ")
    List<NotifyStaffView> findAllStaff();

}
