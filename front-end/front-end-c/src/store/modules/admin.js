import {
    adminLoginAPI,
    getAllAdminsAPI,
    addAdminAPI,
    deleteAdminAPI,
    updateAdminAPI,
} from "@/api/admin";

import {
    getAllStudentsAPI
} from "@/api/student";

import {
    getAllCoursesAPI,
    getAllCourseSelectInfoAPI,
    getCourseSelectInfoAPI,
    updateCourseInfoAPI,
    updateCourseShareAPI,
    updateCourseGradeAPI,
    getStudentCoursesAPI,
} from "@/api/course";

import {message} from 'ant-design-vue'
import router from "@/router";
import callMoment from "ant-design-vue/lib/_util/callMoment";

const admin = {
    state: {
        adminInfo: {
            id: '', //账户名
            password: '',
            permission: '',
        },
        adminList: [],
        studentList: [],
        // modals
        editStudentInfoVisible: false,
        editCourseInfoVisible: false,
        editAdminInfoVisible: false,
        courseSelectInfoVisible: false, // 选课(成绩信息)
        editGradeVisible:false, // 编辑成绩(二级modal)
        curStudent:{}, // 当前学生基本信息
        curCourse:{}, // 当前课程基本信息
        curCourseSelectList:[], // 当前选课信息
    },
    mutations: {
        setAdminInfo: function (state, adminInfo) {
            state.adminInfo = adminInfo
        },
        setStudentList: function (state, studentList) {
            state.studentList = studentList
        },
        setAdminList: function (state, adminList) {
            state.adminList = adminList
        },
        setEditStudentInfoVisibility: function (state, visible) {
            state.editStudentInfoVisible = visible
        },
        setEditCourseInfoVisibility:function (state, visible) {
          state.editCourseInfoVisible = visible
        },
        setEditAdminInfoVisibility:function (state, visible) {
            state.editAdminInfoVisible = visible
        },
        setCourseSelectInfoVisibility:function (state, visible) {
            state.courseSelectInfoVisible = visible
        },
        setEditGradeVisibility:function (state, visible) {
            state.editGradeVisible = visible
        },
        setCurStudent: function (state, studentInfo){
            state.curStudent = studentInfo
        },
        setCurCourse: function (state, courseInfo) {
            state.curCourse = courseInfo
        },
        setCurCourseSelectList: function (state, selectList) {
            state.curCourseSelectList = selectList
        },


    },
    actions: {
        adminLogin: async function ({commit, dispatch}, loginData) {
            const translatedData = {
                acc: loginData.username,
                passwd: loginData.password,
            }
            const res = await adminLoginAPI(translatedData)
            if (res) {
                if (res.data === undefined) {
                    message.error('登录失败')
                    return
                }
                message.success('登录成功')
                const translatedRes = {
                    id: res.data.acc,
                    password: loginData.password,
                    permission: Number(res.data.permission)
                }
                commit('setAdminInfo', translatedRes)
                console.log('管理员信息', translatedRes)
                dispatch('getAllCourses')  // 获取所有课程
                dispatch('getAllStudents') // 获取所有学生
                if (translatedRes.permission > 1) {
                    // 获取管理员列表
                    dispatch('getAllAdmins')
                }
                await router.push('/admin/' + translatedRes.id)
            }
        },
        getAllStudents: async function ({commit}) {
            const res = await getAllStudentsAPI()
            if (res) {
                if (res.data) {
                    let translatedRes = res.data.map((x) => {
                        return {
                            id: x.sno,
                            name: x.snm,
                            gender: x.sex === 'M' ? '男' : '女',
                            department: x.sde,
                            permission: Number(x.permission),
                            password: '123456', // TODO 修改
                        }
                    })
                    commit('setStudentList', translatedRes)
                    console.log('学生列表', translatedRes)
                }
            }
        },
        getAllAdmins: async function ({commit}) {
            const res = await getAllAdminsAPI()
            if (res) {
                if (res.data) {
                    let translatedRes = res.data.map((x) => {
                        return {
                            id: x.acc,
                            permission: x.permission,
                        }
                    })
                    commit('setAdminList', translatedRes)
                    console.log('管理员列表', translatedRes)
                }
            }
        },
        addAdmin: async function ({dispatch, state}, data) {
            const translatedData = {
                operatorId: state.adminInfo.id,
                acc: data.id,
                passwd: data.password,
                permission: data.permission
            }
            const res = await addAdminAPI(translatedData)
            if (res && res.data !== undefined) {
                if (res.data === true) {
                    dispatch('getAllAdmins')
                    message.success('添加成功')
                }
                else if (res.data === false) {
                    message.error('没有权限或异常')
                }
            }
            else {
                message.error('网络错误')
            }
        },
        updateAdmin: async function ({state, dispatch}, data) {
            const translatedData = {
                operatorId: state.adminInfo.id,
                acc: data.id,
                passwd: data.password,
                permission: data.permission
            }
            const res = await updateAdminAPI(translatedData)
            if (res) {
                dispatch('getAllAdmins')
                message.success('更新成功')
            }
            else {
                message.error('更新失败')
            }
        },
        removeAdmin: async function ({state, dispatch}, operandId) {
            const translatedData = {
                operatorId: state.adminInfo.id,
                operandId: operandId,
            }
            const res = await deleteAdminAPI(translatedData)
            if (res && res.data !== undefined) {
                if (res.data === true) {
                    dispatch('getAllAdmins')
                    message.success('删除成功')
                }
                else if (res.data === false) {
                    message.error('没有权限或异常')
                }
            }
            else {
                message.error('网络错误')
            }
        },
        updateCourseInfo: async function({state, dispatch}, data){
          const translatedData =  {
              cno:data.id,
              cnm:data.name,
              ctm:data.time,
              cpt:data.point,
              tec:data.teacher,
              pla:data.place,
              share:data.share === '是' ? 'Y':'N',
              permission:Number(data.permission)
          }
          const res = await updateCourseInfoAPI(translatedData)
            if(res){
                dispatch('getAllCourses')
                message.success('更新成功')
            }
            else {
                message.error('网络错误')
            }
        },
        getCourseSelectInfo: async function({state, commit}, courseId){
            const res = await getCourseSelectInfoAPI(courseId)
            if(res && res.data !== undefined){
                const translatedRes = res.data.map((x) => {
                    return {
                        courseId: courseId,
                        studentId:x.sno,
                        grade:x.grd === null ? '暂无': Number(x.grd)
                    }
                })
                console.log('该课程成绩信息',translatedRes)
                commit('setCurCourseSelectList',translatedRes)
            }
        },
        updateCourseGrade: async function({dispatch}, data){
            const translatedData = {
                cno: data.courseId,
                sno:data.studentId,
                grd:Number(data.grade)
            }
            const res = await updateCourseGradeAPI(translatedData)
            if(res){
                dispatch('getCourseSelectInfo',data.courseId)
                message.success('登记成绩成功')
            }
            else {
                message.error('网络错误')
            }
        }
    },
}

export default admin