package com.data_integration.b.pojo.election;

/**选课实体类*/
public class Election {

    /**课程编号*/
    private String courseId;

    /**学生编号*/
    private String studentId;

    /**得分*/
    private String score;

    public Election() {
    }

    public Election(String courseId, String studentId, String score) {
        this.courseId = courseId;
        this.studentId = studentId;
        this.score = score;
    }

    public String getCourseId() {
        return courseId;
    }

    public void setCourseId(String courseId) {
        this.courseId = courseId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }
}
