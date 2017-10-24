package com.baiying.hu.enums;

/**
 * Created by jmx
 * 2017/10/18.
 */
public enum UserStatusEnum {
    AUDITING("auditing"), EFFECT("effect"),AUDIT_PASSED("auditPassed"),AUDIT_NOT_PASSED("auditNotPassed");
    private String statusType;

    UserStatusEnum(String statusType) {
        this.statusType = statusType;
    }

    public String getStatusType() {
        return statusType;
    }

    public void setStatusType(String statusType) {
        this.statusType = statusType;
    }
}
