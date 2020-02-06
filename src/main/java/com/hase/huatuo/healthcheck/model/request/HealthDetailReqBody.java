package com.hase.huatuo.healthcheck.model.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class HealthDetailReqBody {

    @NonNull
    private String serailNumber;
}
