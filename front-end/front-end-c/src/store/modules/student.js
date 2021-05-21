import {
    loginAPI,
    updateCourseShareAPI,
} from "@/api/student";

import {message} from 'ant-design-vue'


const student = {
    state: {
        studentId: "",
        studentInfo: {}, // 当前学生信息
        // name: "",
        // gender: "",
        // department: "",
        // permission:"", //权限
    },
    mutations: {
        setStudentId: function (state, studentId) {
            state.studentId = studentId
        },
        setStudentInfo: function (state, studentInfo) {
            state.studentInfo = studentInfo
        },
    },
    actions: {
        login: async ({commit}, loginData) => {
            const res = await loginAPI(loginData)
            if(res){
                console.log("登录成功")
                console.log(res)
                commit("setStudentId", res.sno)
                commit("setStudentInfo", res)
            }
            else{
                console.log("登录失败")
            }
        },
        // TODO 注意要加{commit}，不然参数数量不匹配
        tmp: async ({commit},data) => {
            // const p1 =data.p1
            // const p2 = data.p2
            const res = await updateCourseShareAPI(data)
            console.log(11111)
        }

    },

}
export default student