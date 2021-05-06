package com.data_integration.b.pojo.course;

/**课程实体类*/
public class Course {

    /**课程id*/
    private String courseId;

    /**教师姓名*/
    private String teacherName;

    /**学分*/
    private String score;

    /**课程学时*/
    private String courseTime;

    /**课程名称*/
    private String courseName;

    /**教学地点*/
    private String teachingPlace;

    /**是否共享*/
    private String shareFlag;

    public Course() {
    }

    public Course(String courseId, String teacherName, String score, String courseTime, String courseName, String teachingPlace, String shareFlag) {
        this.courseId = courseId;
        this.teacherName = teacherName;
        this.score = score;
        this.courseTime = courseTime;
        this.courseName = courseName;
        this.teachingPlace = teachingPlace;
        this.shareFlag = shareFlag;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getCourseTime() {
        return courseTime;
    }

    public void setCourseTime(String courseTime) {
        this.courseTime = courseTime;
    }

    public String getShareFlag() {
        return shareFlag;
    }

    public void setShareFlag(String shareFlag) {
        this.shareFlag = shareFlag;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeachingPlace() {
        return teachingPlace;
    }

    public void setTeachingPlace(String teachingPlace) {
        this.teachingPlace = teachingPlace;
    }
}
