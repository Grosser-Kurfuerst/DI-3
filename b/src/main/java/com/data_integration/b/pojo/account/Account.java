package com.data_integration.b.pojo.account;

/**账户实体类*/
public class Account {

    /**账户名*/
    private String aname;

    /**密码*/
    private String password;

    /**权限*/
    private int power_grade;

    /**客体（如学生）的id*/
    private String guest_id;


    public int getPower_grade() {
        return power_grade;
    }

    public void setPower_grade(int power_grade) {
        this.power_grade = power_grade;
    }

    public String getGuest_id() {
        return guest_id;
    }

    public void setGuest_id(String guest_id) {
        this.guest_id = guest_id;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public Account() {
    }

    public Account(String aname, String password, int power_grade, String guest_id) {
        this.aname = aname;
        this.password = password;
        this.power_grade = power_grade;
        this.guest_id = guest_id;
    }
}
