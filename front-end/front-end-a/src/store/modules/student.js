import {
    loginAPI,
    updateStudentInfoAPI,
} from "@/api/student";

import {
    getStudentCoursesAPI,
    updateCourseShareAPI,
    selectCourseAPI,
    removeSelectCourseAPI,
} from "@/api/course";

import {message} from 'ant-design-vue'
import router from "@/router";


const student = {
    state: {
        studentId: "", // 学号
        studentInfo: {
            id: "", //也是学号
            name: "",
            gender: "",
            department: "",
            permission: "", //也是权限
            password: "",
        }, // 当前学生信息
        studentPermission: "", // 目前C，课程权限有01234，学生权限>=课程权限时可以选课 暂时不用
        studentCourses: [], // 数据结构为课程的数据结构 + grade: ""(String)

    },
    mutations: {
        setStudentId: function (state, studentId) {
            state.studentId = studentId
        },
        setStudentInfo: function (state, studentInfo) {
            state.studentInfo = studentInfo
        },
        setStudentPermission: function (state, studentPermission) {
            state.studentPermission = studentPermission
        },
        setStudentCourses: function (state, studentCourses) {
            state.studentCourses = studentCourses
        }
    },
    actions: {
        logout: async ({commit}) => {
            commit.setStudentCourses([])
            commit.setStudentInfo({})
            commit.setStudentId('')
        },

        login: async ({commit, dispatch, state, rootState}, loginData) => {
            const translatedData = {
                stunum: loginData.username,
                pwd: loginData.password,
            }
            const res = await loginAPI(translatedData)
            if (res) {
                if (res.data === undefined) {
                    message.error('登录失败，密码或用户名错误')
                    return
                }
                message.success('登录成功')
                const translatedRes = {
                    id: res.data.stunum,
                    name: res.data.stuname,
                    gender: res.data.sex === 'M' ? '男' : '女',
                    department: res.data.department,
                    permission: Number(res.data.permission),
                    password: loginData.password,
                }
                commit("setStudentInfo", translatedRes)
                commit("setStudentId", translatedRes.id)
                console.log("学生信息:", translatedRes)
                await router.push('/student/' + translatedRes.id)
                dispatch('getAllCourses')  // 获取所有课程
                dispatch('getStudentCourses', state.studentInfo.id)
            }
            else {
                message.error('登录失败，请重试')
            }
        },

        updateStudentInfo: async ({commit,rootState,dispatch}, studentInfo) => {
            const translatedData = {
                stunum: studentInfo.id,
                stuname: studentInfo.name,
                sex: studentInfo.gender === '男' ? 'M' : 'F',
                department: studentInfo.department,
                pwd: studentInfo.password,
                permission: studentInfo.permission,
                // account:'neverUsed',
            }
            const res = await updateStudentInfoAPI(translatedData)
            if (res) {
                commit('setStudentInfo', studentInfo)
                dispatch('getAllStudents')
                message.success('更新成功')
            }
            else {
                message.error('更新失败')
            }
        },

        getStudentCourses: async ({commit, state,dispatch,rootGetters}, studentId) => {
            const res = await getStudentCoursesAPI(studentId)
            console.log("已选课程res：", res)
            if (res) {
                const resData = res.data // 可能为空
                // 修改属性名称
                // console.log(resData)   //{cno: "1233", sno: "123456788", grd: 80} // grd可能为null
                let translatedRes = resData.map((x) => {
                    let targetCourse = rootGetters.getCourseById(x.coursenum) // 只有这里(还有下面)需要修改
                   // console.log("targetCourse",targetCourse)
                    return Object.assign({grade: String(x.record)==='null'? '暂无':String(x.record) },targetCourse) // 课程信息加上成绩
                })
                commit('setStudentCourses', translatedRes)
                console.log("已选课程：", translatedRes)
            }
            else {
                message.error('获取课程信息失败')
            }
        },

        selectCourse: async ({state,dispatch,rootGetters}, courseId) => {
            // console.log('选课，课程id-',courseId)
            const res = await selectCourseAPI(state.studentInfo.id, courseId)
            if(res){
                const resData = res.data
                if(resData === undefined){
                    message.error('网络错误')
                }
                else if(Boolean(resData) === false){
                    message.error('无权限或后台异常')
                }
                else if(Boolean(resData) === true){
                    message.success('选课成功')
                    dispatch('getStudentCourses',state.studentInfo.id)
                }
            }
            else{
                message.error('网络错误')
            }
        },

        removeSelectCourse:async ({state,dispatch}, courseId) => {
            const res = await removeSelectCourseAPI(state.studentInfo.id,courseId)
            if(res){
                if(res.result){
                    message.success('退选成功')
                    dispatch('getStudentCourses',state.studentInfo.id)
                }
                else{
                    message.error('网络错误')
                }
            }
            else{
                message.error('网络错误')
            }
        },


        tmp: async ({commit}, data) => {
            // const p1 =data.p1
            // const p2 = data.p2
            const res = await updateCourseShareAPI(data)
        }

    },

}
export default student