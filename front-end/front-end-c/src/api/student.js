import {axios} from '@/utils/request'

const api={
    studentPre:'/c/student', // TODO 修改
}

export function loginAPI(data) {
    return axios({
        url:`${api.studentPre}/login`,
        method: 'POST',
        data
    })
}

export function updateCourseShareAPI(data) {
    return axios({
        url:'/c/course/updateCourseShare',
        method:'POST',

        // TODO 这样搞，params是加在url，能成功
        params:{
            cno:data.cno,
            share:data.share
        }
    })
}