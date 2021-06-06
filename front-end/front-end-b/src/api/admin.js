import {axios} from '@/utils/request'

const api = {
    studentPre: '/b/student', // TODO 修改
    adminPre: '/b/admin',
}

export function adminLoginAPI(data) {
    return axios({
        url: `${api.adminPre}/login`,
        method: 'POST',
        data,
    })
}

export function getAllAdminsAPI() {
    return axios({
        url: `${api.adminPre}/getAllAdmins`,
        method: 'GET',
    })
}


export function deleteAdminAPI(data) {
    return axios({
        url: `${api.adminPre}/deleteAccount/${data.operatorId}/${data.operandId}`,
        method: 'GET',
    })
}

export function addAdminAPI(data) {
    const tmp = {
        aname: data.aname,
        password: data.password,
        power_grade: data.power_grade
    }
    console.log(tmp)
    return axios({
        url: `${api.adminPre}/${data.operatorId}/addAccount`,
        method: 'POST',
        data: tmp,
    })
}

export function updateAdminAPI(data) {
    const tmp = {
        aname: data.aname,
        password: data.password,
        power_grade: data.power_grade
    }
    return axios({
        url: `${api.adminPre}/updateAccount`,
        method: 'POST',
        data: tmp,
    })
}
