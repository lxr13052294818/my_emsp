package com.volvo.model;

import io.swagger.annotations.ApiModelProperty;

/**
 * 描述:
 *
 * @author lxr
 * @date 2025/2/26
 */
public class AccountDTO {
    @ApiModelProperty(value = "邮箱", example = "lxr@volvo.com")
    private String email;
}
