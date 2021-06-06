import {axios} from '@/utils/request'

const api = {
    studentPre: '/a/student', // TODO 修改
    adminPre: '/a/account',
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
        url: `${api.adminPre}/getAllAccounts`,
        method: 'GET',
    })
}


export function deleteAdminAPI(data) {
    return axios({
        url: `${api.adminPre}/${data.operatorId}/deleteAccount/${data.operandId}`,
        method: 'POST',
    })
}

export function addAdminAPI(data) {
    const tmp = {
        account: data.account,
        password: data.password,
        permission: data.permission
    }
    return axios({
        url: `${api.adminPre}/${data.operatorId}/addAccount`,
        method: 'POST',
        data: tmp,
    })
}

export function updateAdminAPI(data) {
    const tmp = {
        account: data.account,
        password: data.password,
        permission: data.permission
    }
    return axios({
        url: `${api.adminPre}/updateAccount`,
        method: 'POST',
        data: tmp,
    })
}
