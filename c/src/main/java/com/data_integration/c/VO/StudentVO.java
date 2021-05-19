package com.data_integration.c.VO;

public class StudentVO {
    public String sno;
    public String snm;
    public String sex;
    public String sde;
    public Integer permission;

    public Integer getPermission() {
        return permission;
    }

    public void setPermission(Integer permission) {
        this.permission = permission;
    }

    public String getSno() {
        return sno;
    }

    public void setSno(String sno) {
        this.sno = sno;
    }

    public String getSnm() {
        return snm;
    }

    public void setSnm(String snm) {
        this.snm = snm;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getSde() {
        return sde;
    }

    public void setSde(String sde) {
        this.sde = sde;
    }
}
