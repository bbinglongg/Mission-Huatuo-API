package com.hase.huatuo.healthcheck.rest;

import com.hase.huatuo.healthcheck.service.HuatuoAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping({"/huatuoAdmin/api","/api"})
public class HuatuoAdminResource {

    @Autowired
    private HuatuoAdminService huatuoAdminService;

    

}
