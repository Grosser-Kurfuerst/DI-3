import {axios} from '@/utils/request'

const api={
    studentPre:'/a/student', // TODO 修改
}

export function loginAPI(data) {
    return axios({
        url:`${api.studentPre}/login`,
        method: 'POST',
        data
    })
}

export function updateStudentInfoAPI(data){
    return axios({
        url:`${api.studentPre}/updateStudentInfo`,
        method:'POST',
        data,
    })
}

export function getAllStudentsAPI() {
    return axios({
        url: `${api.studentPre}/getAllStudents`,
        method: 'GET',
    })
}
