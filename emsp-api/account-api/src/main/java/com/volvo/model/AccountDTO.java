package com.volvo.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 *
 * @author lxr
 * @date 2025/2/26
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
    @ApiModelProperty(value = "邮箱", example = "lxr@volvo.com")
    private String email;
}
