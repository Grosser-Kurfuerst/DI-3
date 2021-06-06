import {axios} from "@/utils/request";

const api = {
    coursePre: '/a/course',
    courseSelectingPre: '/a/courseSelecting'
}


// 查看课程列表
export function getAllCoursesAPI() {
    return axios({
        url: `${api.coursePre}/getAllCourses`,
        method: 'GET',
    })
}

export function getOtherDepartmentCoursesAPI() {
    return axios({
        url: `${api.coursePre}/getOtherDepartmentCourses`,
        method: 'GET',
    })
}

// 更新课程共享信息
export function updateCourseShareAPI(data) {
    return axios({
        url: `${api.coursePre}/updateCourseShare`,
        method: 'POST',

        // TODO 这样搞，params是加在url，能成功
        params: {
            cno: data.cno,
            share: data.share
        }
    })
}

// 修改课程信息
export function updateCourseInfoAPI(data) {
    // RequestBody
    // cno: char(4) 课程编号
    // cnm: varchar(10) 课程名
    // ctm: integer 课时
    // cpt: integer 学分
    // tec: varchar(20) 教师名
    // pla: varchar(18) 上课地点
    // share: char(1) 是否共享
    // permission: integer 权限
    return axios({
        url: `${api.coursePre}/updateCourseInfo`,
        method: 'POST',
        data,
    })
}

// 获取所有的选课数据
export function getAllCourseSelectInfoAPI() {
    return axios({
        url: `${api.courseSelectingPre}/getAllCourseSelecting`,
        method: 'GET',
    })
    // 返回数组
    // cno: char(4) 课程编号
    // sno: char(9) 学号
    // grd: integer 成绩，可能为null
}

// 选课
export function selectCourseAPI(studentId, courseId) {
    return axios({
        url: `${api.courseSelectingPre}/addCourseSelecting`,
        method: 'POST',
        data: {
            coursenum: courseId,
            studentnum: studentId,
        }
    })
}

// 退选
export function removeSelectCourseAPI(studentId, courseId) {
    return axios({
        url: `${api.courseSelectingPre}/deleteCourseSelecting`,
        method: 'POST',
        data: {
            coursenum: courseId,
            studentnum: studentId,
        }
    })
}

// 查看某个学生的选课
export function getStudentCoursesAPI(studentId) {
    return axios({
        url: `${api.courseSelectingPre}/getCourseSelectingBySno/${studentId}`,
        method: 'GET',
    })
}

// 查看某门课程的选课情况
export function getCourseSelectInfoAPI(courseId) {
    return axios({
        url: `${api.courseSelectingPre}/getCourseSelectingByCno/${courseId}`,
        method: 'GET',
    })
    // 返回数组
    // cno: char(4) 课程编号
    // sno: char(9) 学号
    // grd: integer 成绩，可能为null
}

// 修改成绩
export function updateCourseGradeAPI(data) {
    // RequestBody
    // cno: char(4) 课程编号
    // sno: char(9) 学号
    // grd: integer 成绩
    return axios({
        url: `${api.courseSelectingPre}/updateGrade`,
        method: 'POST',
        data,
    })
}

