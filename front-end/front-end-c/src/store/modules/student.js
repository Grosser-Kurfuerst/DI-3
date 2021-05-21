import {
    loginAPI,
    updateCourseShareAPI,
} from "@/api/student";

import {message} from 'ant-design-vue'
import router from "@/router";


const student = {
    state: {
        studentId: "", // 学号
        studentInfo: {
            id:"", //也是学号
            name:"",
            gender:"",
            department:"",
            permission:"", //也是权限
        }, // 当前学生信息
        studentPermission:"", // 目前C，课程权限有01234，学生权限>=课程权限时可以选课 暂时不用
    },
    mutations: {
        setStudentId: function (state, studentId) {
            state.studentId = studentId
        },
        setStudentInfo: function (state, studentInfo) {
            state.studentInfo = studentInfo
        },
        setStudentPermission:function (state, studentPermission) {
            state.studentPermission = studentPermission
        },
    },
    actions: {
        login: async ({commit}, loginData) => {
            const translatedData = {
                sno:loginData.username,
                pwd:loginData.password,
            }
            const res = await loginAPI(translatedData)
            if(res){
                message.success('登录成功')
                commit("setStudentId", res.sno)
                const translatedRes = {
                    id: res.sno,
                    name: res.snm,
                    gender:res.sex === 'M' ? '男':'女',
                    department: res.sde,
                    permission: Number(res.permission),
                }
                commit("setStudentInfo", translatedRes)
                console.log("学生信息:", translatedRes)
                router.push('/student/' + translatedRes.id)
            }
            else{
                message.error('登录失败，请重试')
            }
        },
        // TODO 注意要加{commit}，不然参数数量不匹配
        tmp: async ({commit},data) => {
            // const p1 =data.p1
            // const p2 = data.p2
            const res = await updateCourseShareAPI(data)
        }

    },

}
export default student