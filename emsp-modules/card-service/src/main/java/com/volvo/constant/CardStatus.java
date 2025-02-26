package com.volvo.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 描述:
 *
 * @author lxr
 * @date 2025/2/23
 */
@Getter
@AllArgsConstructor
public enum CardStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    SUSPENDED("Suspended");

    private String status;
}
