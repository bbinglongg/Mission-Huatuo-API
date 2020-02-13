package com.hase.huatuo.healthcheck.rest.v2;

import com.hase.huatuo.healthcheck.model.response.CityWithBranchResponse;
import com.hase.huatuo.healthcheck.service.HacnService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

/**
 * @author David
 * @date
 */
@RestController
@RequestMapping({"/api/v2/hacn"})
@Api(tags="HacnResource", value = "Hacn v2")
public class HacnResource {

    @Autowired
    private HacnService hacnService;

    @GetMapping("/city/with-branch")
    @ApiOperation(value = "cities with branches", notes = "get cities with branches", httpMethod = "GET")
    @ResponseStatus(value = HttpStatus.OK)
    public CityWithBranchResponse getHacnBranches() {
        return new CityWithBranchResponse(hacnService.getAllCitiesBranches());
    }
}
