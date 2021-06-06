const getters = {
    // student
    studentInfo: state => state.student.studentInfo,
    studentCourses: state => state.student.studentCourses,
    // course
    courseList: state => state.course.courseList,
    // admin
    adminInfo: state => state.admin.adminInfo,
    adminList: state => state.admin.adminList,
    studentList: state => state.admin.studentList,
    editStudentInfoVisible: state => state.admin.editStudentInfoVisible,
    addAdminVisible:state => state.admin.addAdminVisible,
    editCourseInfoVisible: state => state.admin.editCourseInfoVisible,
    editGradeVisible: state => state.admin.editGradeVisible,
    editAdminInfoVisible: state => state.admin.editAdminInfoVisible,
    courseSelectInfoVisible: state => state.admin.courseSelectInfoVisible,
    studentCourseVisible: state => state.admin.studentCourseVisible,
    studentCourseMode: state => state.admin.studentCourseMode,
    curStudent: state => state.admin.curStudent,
    curAdmin: state => state.admin.curAdmin,
    curCourse: state => state.admin.curCourse,
    curCourseSelectList: state => state.admin.curCourseSelectList,
    studentCourseList:state => state.admin.studentCourseList,
    otherCourseList:state => state.admin.otherCourseList,
}

export default getters