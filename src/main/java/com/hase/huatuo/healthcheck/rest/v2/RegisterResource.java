package com.hase.huatuo.healthcheck.rest.v2;

import com.hase.huatuo.healthcheck.model.UserInfo;
import com.hase.huatuo.healthcheck.model.request.MiniProgramRegisterRequest;
import com.hase.huatuo.healthcheck.service.HuatuoRegistrationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author David
 * @date
 */
@RestController
@RequestMapping({"/api/v2"})
@Api(tags="RegisterResource", value = "Register v2")
public class RegisterResource {

    @Autowired
    private HuatuoRegistrationService huatuoRegistrationService;

    @PostMapping("/register/mini-program")
    @ApiOperation(value = "mini program register", notes = "mini program register", httpMethod = "POST")
    @ResponseStatus(value = HttpStatus.OK)
    public UserInfo registration(@RequestBody @Valid final MiniProgramRegisterRequest miniProgramRegisterRequest) {
        return huatuoRegistrationService.register(miniProgramRegisterRequest);
    }
}
