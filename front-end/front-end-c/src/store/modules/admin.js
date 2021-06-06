import {
    adminLoginAPI,
    getAllAdminsAPI,
    addAdminAPI,
    deleteAdminAPI,
    updateAdminAPI,
} from "@/api/admin";

import {
    getAllStudentsAPI,

} from "@/api/student";

import {
    getAllCoursesAPI,  // 登录的时候已经做好了
    getAllCourseSelectInfoAPI,  //暂时没用，被分开了
    getCourseSelectInfoAPI,
    updateCourseInfoAPI,
    updateCourseShareAPI, // 暂时没用
    updateCourseGradeAPI,
    getStudentCoursesAPI,  // 以后可能用于教务员强制学生选课/退课

    selectCourseAPI,
    removeSelectCourseAPI,
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
        addAdminVisible:false,
        courseSelectInfoVisible: false, // 选课(成绩信息)
        editGradeVisible: false, // 编辑成绩(二级modal)(废弃)

        studentCourseVisible: false, // 学生选课信息
        studentCourseMode: 0, // 展示的是已选还是未选 0:已选 1:未选

        curStudent: {}, // 当前学生基本信息
        curCourse: {}, // 当前课程基本信息
        curAdmin: {}, // 当前管理员
        curCourseSelectList: [], // 当前学院选课信息
        studentCourseList:[], // 当前学生选课信息
        otherCourseList: [], // 当前学生未选课信息
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
        setEditCourseInfoVisibility: function (state, visible) {
            state.editCourseInfoVisible = visible
        },
        setEditAdminInfoVisibility: function (state, visible) {
            state.editAdminInfoVisible = visible
        },
        setAddAdminVisibility:function (state, visible) {
            state.addAdminVisible = visible
        },
        setCourseSelectInfoVisibility: function (state, visible) {
            state.courseSelectInfoVisible = visible
        },
        setEditGradeVisibility: function (state, visible) {
            state.editGradeVisible = visible
        },
        setStudentCourseVisibility:function (state, visible) {
          state.studentCourseVisible = visible
        },

        setStudentCourseMode: function (state, mode) {
          state.studentCourseMode = Number(mode)
        },
        setStudentCourseList:function (state, studentCourseList) {
          state.studentCourseList = studentCourseList
        },
        setOtherCourseList: function (state, otherCourseList) {
          state.otherCourseList = otherCourseList
        },

        setCurStudent: function (state, studentInfo) {
            state.curStudent = studentInfo
        },
        setCurAdmin: function (state, adminInfo) {
            state.curAdmin = adminInfo
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
                            password: x.pwd,
                        }
                    })
                    commit('setStudentList', translatedRes)
                    console.log('学生列表', translatedRes)
                }
            }
        },
        getAllAdmins: async function ({state,commit}) {
            const res = await getAllAdminsAPI()
            if (res) {
                if (res.data) {
                    let translatedRes = res.data.map((x) => {
                        return {
                            id: x.acc,
                            permission: x.permission,
                            password: x.passwd,
                        }
                    })
                    commit('setAdminList', translatedRes)
                    console.log('管理员列表', translatedRes)
                    // 假设更新了自身，找到自己，更新adminInfo
                    let tmp = translatedRes.find((x) => {return  x.id === state.adminInfo.id})
                    console.log('我自己',tmp)
                    if(tmp !== undefined){
                        commit('setAdminInfo',tmp)
                    }
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
        updateAdminInfo: async function ({state, dispatch}, data) {
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
        updateCourseInfo: async function ({state, dispatch}, data) {
            const translatedData = {
                cno: data.id,
                cnm: data.name,
                ctm: data.time,
                cpt: data.point,
                tec: data.teacher,
                pla: data.place,
                share: data.share === '是' ? 'Y' : 'N',
                permission: Number(data.permission)
            }
            const res = await updateCourseInfoAPI(translatedData)
            if (res) {
                dispatch('getAllCourses')
                message.success('更新成功')
            }
            else {
                message.error('网络错误')
            }
        },
        getCourseSelectInfo: async function ({state, commit}, courseId) {
            commit('setCurCourseSelectList', [])
            const res = await getCourseSelectInfoAPI(courseId)
            if (res && res.data !== undefined) {
                const translatedRes = res.data.map((x) => {
                    return {
                        courseId: courseId,
                        studentId: x.sno,
                        grade: x.grd === null ? '暂无' : Number(x.grd)
                    }
                })
                console.log('该课程成绩信息', translatedRes)
                commit('setCurCourseSelectList', translatedRes)
            }
        },
        updateCourseGrade: async function ({dispatch}, data) {
            const translatedData = {
                cno: data.courseId,
                sno: data.studentId,
                grd: Number(data.grade)
            }
            const res = await updateCourseGradeAPI(translatedData)
            if (res) {
                dispatch('getCourseSelectInfo', data.courseId)
                message.success('登记成绩成功')
            }
            else {
                message.error('网络错误')
            }
        },
        // 获取某个学生的选课情况
        getAdminStudentCourse: async function({commit, state,rootGetters}, studentId){
            const res = await getStudentCoursesAPI(studentId)
            if (res) {
                const resData = res.data // 可能为空
                // 修改属性名称
                // console.log(resData)   //{cno: "1233", sno: "123456788", grd: 80} // grd可能为null
                let translatedRes1 = resData.map((x) => {
                    let targetCourse = rootGetters.getCourseById(x.cno) // 只有这里(还有下面)需要修改
                    // console.log("targetCourse",targetCourse)
                    // return Object.assign({grade: String(x.grd)==='null'? '暂无':String(x.grd) },targetCourse) // 课程信息加上成绩
                    return Object.assign({},targetCourse)
                })
                commit('setStudentCourseList', translatedRes1)
                console.log("已选课程：", translatedRes1)
                let allCourses = rootGetters.courseList
                let translatedRes2 = []
                for(let i=0;i<allCourses.length;i++){
                    let tmp1 = rootGetters.getCourseById(allCourses[i].id)
                    let tmp2 = translatedRes1.find((x) => {return x.id === tmp1.id})
                    if(tmp2 === undefined){
                        translatedRes2.push(tmp1)
                    }
                }
                console.log("未选课程",translatedRes2)
                commit('setOtherCourseList', translatedRes2)
                // 设置modal的visible
                commit('setStudentCourseVisibility', true)
            }
        },
        adminSelectCourse: async ({state, dispatch}, courseId) => {
            const res = await selectCourseAPI(state.curStudent.id, courseId)
            if(res){
                const resData = res.data
                if(resData === undefined){
                    message.error('网络错误')
                }
                else if(Boolean(resData) === false){
                    message.error('学生无权限或后台异常')
                }
                else if(Boolean(resData) === true){
                    message.success('选课成功')
                    dispatch('getAdminStudentCourse',state.curStudent.id)
                }
            }
            else{
                message.error('网络错误')
            }
        },
        adminRemoveCourse: async ({state, dispatch}, courseId) => {
            const res = await removeSelectCourseAPI(state.curStudent.id, courseId)
            if(res){
                if(res.result){
                    message.success('退选成功')
                    dispatch('getAdminStudentCourse',state.curStudent.id)
                }
                else{
                    message.error('网络错误')
                }
            }
            else{
                message.error('网络错误')
            }
        },
    },
}

export default admin