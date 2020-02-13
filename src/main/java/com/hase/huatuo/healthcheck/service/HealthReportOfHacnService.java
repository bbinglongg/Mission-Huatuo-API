package com.hase.huatuo.healthcheck.service;

import com.hase.huatuo.healthcheck.model.HacnHealInfoView;
import com.hase.huatuo.healthcheck.model.HacnIsolationInfoView;
import com.hase.huatuo.healthcheck.model.HealthInfoView;
import com.hase.huatuo.healthcheck.model.response.AreaReport;
import com.hase.huatuo.healthcheck.model.response.AreaReportForHacn;
import com.hase.huatuo.healthcheck.model.response.BranchOfHacn;
import com.hase.huatuo.healthcheck.model.response.BuildingReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class HealthReportOfHacnService {

    public static final String IS_DUMMY = "true";

    public static final String DEATH = "3";

    public static final String SUSPECT = "2";

    public static final String CONFIRMED = "1";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final Map<String, Integer> cityOfBranchPriority = new HashMap<String, Integer>(){{
        put("SH", 1);put("BJ", 2);put("CD", 3);put("DG", 4);
        put("FZ", 5);put("GZ", 6);put("HaZ", 7);put("JN", 8);
        put("KM", 9);put("NJ", 10);put("NB", 11);put("SZ", 12);
        put("XM", 13);put("TJ", 14);
    }};
    private static final Map<String, Integer> cityOfSubBranchPriority = new HashMap<String, Integer>(){{
        put("FS", 1);put("HuZ", 2);put("JM", 3);put("ZS", 4);
    }};

    private static final String HEALTH_SUMMARY_BY_CITY = "SELECT c.city_short_name as cityShortName,h.health_status as healthStatus,AVG(c.only_has_sub_branch) AS isSubBranch, SUM(IF(h.staff_id IS NULL, 0, 1)) AS total FROM cities_hacn c LEFT JOIN health_info_hacn h on c.city_short_name = h.city_short_name WHERE h.is_statistic= '1' OR h.is_statistic IS NULL GROUP BY c.city_short_name, h.health_status ORDER BY null";

    private static final String HEALTH_SUMMARY_BY_ISOLATION_TYPE = "select count(h.health_status) from health_info_hacn h where (h.is_statistic='1' or h.is_statistic IS NULL) and h.health_status=?";

    public ResponseEntity<AreaReportForHacn> enquiry(){
        AreaReportForHacn areaReportForHacn = new AreaReportForHacn();
        List<BranchOfHacn> branchOfHacns = new ArrayList<>();
        List<BranchOfHacn> subBranchOfHacns = new ArrayList<>();
        RowMapper<HacnHealInfoView> healthInfoRowMapper = BeanPropertyRowMapper.newInstance(HacnHealInfoView.class);
        List<HacnHealInfoView> healthInfos = jdbcTemplate.query(HEALTH_SUMMARY_BY_CITY, healthInfoRowMapper, null);
        Iterator<Map.Entry<String, List<HacnHealInfoView>>> entryIterator = healthInfos.stream().collect(Collectors.groupingBy(h -> h.getCityShortName())).entrySet().iterator();

        while (entryIterator.hasNext()){
            Map.Entry<String, List<HacnHealInfoView>> entry = entryIterator.next();
            BranchOfHacn b = new BranchOfHacn();
            b.setSuspect("0");
            b.setDeath("0");
            b.setConfirmed("0");
            b.setArea(entry.getKey());
            entry.getValue().forEach(h -> {
                if(DEATH.equalsIgnoreCase(h.getHealthStatus())){
                    b.setDeath(h.getTotal());
                }
                if(SUSPECT.equalsIgnoreCase(h.getHealthStatus())){
                    b.setSuspect(h.getTotal());
                }
                if(CONFIRMED.equalsIgnoreCase(h.getHealthStatus())){
                    b.setConfirmed(h.getTotal());
                }
            });
            if("1".equalsIgnoreCase(entry.getValue().stream().findFirst().get().getIsSubBranch())){
                subBranchOfHacns.add(b);
            } else {
                branchOfHacns.add(b);
            }
        }
        branchOfHacns.sort((o1, o2) -> comparePriority(o1, o2, cityOfBranchPriority));
        subBranchOfHacns.sort((o1, o2) -> comparePriority(o1, o2, cityOfSubBranchPriority));
        areaReportForHacn.setSubBranches(subBranchOfHacns);
        areaReportForHacn.setBranches(branchOfHacns);
//        RowMapper<HacnIsolationInfoView> isolationInfoViewRowMapper = BeanPropertyRowMapper.newInstance(HacnIsolationInfoView.class);
//        List<HacnIsolationInfoView> isolationInfoViews = jdbcTemplate.query(HEALTH_SUMMARY_BY_ISOLATION_TYPE, isolationInfoViewRowMapper, null);
//        isolationInfoViews.stream().forEach(item -> {
//            if("1".equalsIgnoreCase(item.getIsolationType())){
//                areaReportForHacn.setActiveIsolation(item.getTotal());
//            } else {
//                areaReportForHacn.setPassiveIsolation(item.getTotal());
//            }
//        });
        int passiveIsolation = jdbcTemplate.queryForObject(HEALTH_SUMMARY_BY_ISOLATION_TYPE, new Object[]{"4"}, Integer.class);
        int activeIsolation = jdbcTemplate.queryForObject(HEALTH_SUMMARY_BY_ISOLATION_TYPE, new Object[]{"5"}, Integer.class);
        areaReportForHacn.setActiveIsolation(activeIsolation+"");
        areaReportForHacn.setPassiveIsolation(passiveIsolation+"");
        return ResponseEntity.ok(areaReportForHacn);
    }

    public int comparePriority(final BranchOfHacn o1 , final BranchOfHacn o2, final Map<String, Integer> cityPriority){
        if(cityPriority.get(o1.getArea()).intValue() > cityPriority.get(o2.getArea()).intValue()){
            return 1;
        } else if(cityPriority.get(o1.getArea()).intValue() < cityPriority.get(o2.getArea()).intValue()){
            return -1;
        } else {
            return 0;
        }
    }
}
