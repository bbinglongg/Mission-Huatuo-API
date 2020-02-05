package com.hase.huatuo.healthcheck.dao;

import com.hase.huatuo.healthcheck.model.Notify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface NotifyRepository extends JpaRepository<Notify,String> {
    @Query(value = "select s.mobileNum from Notify n left join StaffListEntity s on n.staffId = s.staffId where s.mobileNum is not null and s.mobileNum <> '' and n.status = 1")
    List<String> notifyStaffMobileNumbers();
}
