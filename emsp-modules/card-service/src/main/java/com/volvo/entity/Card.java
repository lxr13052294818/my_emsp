package com.volvo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * 描述:
 *
 * @author lxr
 * @date 2025/2/23
 */
@Data
@Builder
@TableName("card")
public class Card {

    @TableId
    private Long id;

    @ApiModelProperty(value = "合同ID", example = "1")
    private String contractId;

    @ApiModelProperty(value = "状态", example = "CREATED")
    private String status;

    @ApiModelProperty(value = "账户ID", example = "1")
    private Long accountId;

    @ApiModelProperty(value = "更新时间", example = "2025-02-23 00:00:00")
    private LocalDateTime lastUpdated;
}
