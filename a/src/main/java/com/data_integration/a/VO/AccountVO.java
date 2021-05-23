package com.data_integration.a.VO;

import java.sql.Timestamp;

public class AccountVO {
    public String account;
    public Integer permission;

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }
}
