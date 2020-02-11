package com.hase.huatuo.healthcheck.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author David
 * @date
 */
@Data
@ApiModel(description = "RequestBody for mini program register")
public class MiniProgramRegisterRequest {
    @ApiModelProperty(value = "appId", notes = "App ID", dataType = "java.lang.String")
    @NotBlank
    private String appId;

    @ApiModelProperty(value = "openId", notes = "open ID get after authorization", dataType = "java.lang.String")
    @NotBlank
    private String openId;

    @ApiModelProperty(value = "45022339", notes = "Staff ID", dataType = "java.lang.String")
    @NotBlank
    private String staffId;
}
