package com.hase.huatuo.healthcheck.model.response;

import lombok.Data;

/**
 * <p> </p>
 * Created by shenpeng on  2020/2/6 15:59
 */
@Data
public class AdminUserResponse extends AdminResponse{

    private String[] roles;

    private String introduction;

    private String avatar;

    private String name;
}
