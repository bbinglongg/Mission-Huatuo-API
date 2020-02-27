package com.hase.huatuo.healthcheck.dao;

import com.hase.huatuo.healthcheck.model.Notify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotifyRepository extends JpaRepository<Notify,String> {
    @Query(value = "select s.mobileNum from Notify n left join com.hase.huatuo.healthcheck.dao.entity.Staff s on n.staffId = s.staffId where s.mobileNum is not null and s.mobileNum <> '' and n.status = 1")
    List<String> notifyStaffMobileNumbers();
    @Query(value = "select s.emailAddress from Notify n left join com.hase.huatuo.healthcheck.dao.entity.Staff s on n.staffId = s.staffId where s.emailAddress is not null and s.emailAddress <> '' and n.status = 1")
    List<String> notifyStaffEmailAddress();
}
