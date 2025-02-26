package com.volvo.model.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 描述:
 *
 * @author lxr
 * @date 2025/2/23
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardVO {

    @ApiModelProperty(value = "ID", example = "1")
    private Long id;

    @ApiModelProperty(value = "用户名", example = "lxr")
    private String email;

    @ApiModelProperty(value = "状态", example = "Active")
    private String status;
}
