package com.hase.huatuo.healthcheck.dao;

import com.hase.huatuo.healthcheck.Application;
import com.hase.huatuo.healthcheck.model.PersonHealthInfo;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
public class RecordsRepositoryTest {
    @Autowired
    private RecordsRepository recordsRepository;

    @Test
    public void testAdd() {
        PersonHealthInfo personHealthInfo = new PersonHealthInfo();
        personHealthInfo.setStaffID("002");
        recordsRepository.save(personHealthInfo);
    }
    @Test
    public void testQuery() {
        Optional<PersonHealthInfo> per  = recordsRepository.findById("001");
        String staffId = per.get().getStaffID();
        Assert.assertEquals("001",staffId);
    }
}
