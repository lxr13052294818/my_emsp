package com.volvo.constant;

/**
 * 描述:
 *
 * @author lxr
 * @date 2025/2/23
 */
public enum AccountStatus {
    ACTIVE("Active"),
    INACTIVE("Inactive"),
    SUSPENDED("Suspended");

    private String status;

    AccountStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
