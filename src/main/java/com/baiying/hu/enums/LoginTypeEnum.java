package com.baiying.hu.enums;

/**
 * Created by jmx
 * 2017/10/2.
 */
public enum LoginTypeEnum {
    ACCOUNT("account"), MOBILE("mobile");
    private String type;

    LoginTypeEnum(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
