package com.volvo.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

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

    private String email;
    private String status;
}
