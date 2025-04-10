package com.data_integration.b.pojo.student;

/**学生实体类*/
public class Student {

    /**
     * 学生Id
     */
    private String sid;

    /**
     * 学生姓名
     */
    private String sname;

    /**
     * 学生性别
     */
    private String gender;

    /**
     * 部门id
     */
    private String department;

    /**
     * 学生密码
     */
    private String password;

//    /**
//     * 学生权限
//     */
//    private int permission;

    public Student() {
    }

    public Student(String sid, String sname, String gender, String department, String password) {
        this.sid = sid;
        this.sname = sname;
        this.gender = gender;
        this.department = department;
        this.password = password;
    }


    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getSid() {
        return sid;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
//
//    public int getPermission() {
//        return permission;
//    }
//
//    public void setPermission(int permission) {
//        this.permission = permission;
//    }
}


