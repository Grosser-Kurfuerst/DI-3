import Vue from 'vue'
import axios from 'axios'
import {VueAxios} from './axios'
import {notification, message} from 'ant-design-vue'
import store from '@/store'

// 创建 axios 实例
const service = axios.create({
    baseURL: process.env.NODE_ENV === 'production' ? 'http://127.0.0.1:9361' : 'http://127.0.0.1:9361', // TODO 修改
    withCredentials: true
})
// service.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
// axios.defaults.headers.post['Content-Type'] = 'application/json;charset=UTF-8';
console.log(process.env.NODE_ENV)
const err = (error) => {
    if (error.response) {
        const data = error.response.data
        const token = Vue.ls.get('ACCESS_TOKEN')
        if (error.response.status === 403) {
            notification.error({
                message: 'Forbidden',
                description: data.message
            })
        }
        if (error.response.status === 401 && !(data.result && data.result.isLogin)) {
            notification.error({
                message: 'Unauthorized',
                description: 'Authorization verification failed'
            })
            if (token) {
                store.dispatch('Logout').then(() => {
                    setTimeout(() => {
                        window.location.reload()
                    }, 1500)
                })
            }
        }
    }
    return Promise.reject(error)
}

//request incerceptor
service.interceptors.request.use((config) => {
    const requestConfig = {
        ...config,
        url: `${config.url}`,
    }
    return requestConfig
}, err)

service.interceptors.response.use((response) => {
    // console.log(response)
    switch (response.status) {
        case 200: // 包装一下res，data可能为空，统一为undefined
            // console.log('response',response)
            let res = {}
            res.result = 'success'
            let responseData = response.data
            if (responseData === undefined || responseData == null
                || JSON.stringify(responseData) === '{}'
                || JSON.stringify(responseData) === '[]'
                || JSON.stringify(responseData) === '\"\"'
                || JSON.stringify(responseData) === '\'\''
                ||responseData.length === 0
            ) {
                res.data = undefined
            }
            else{
                res.data = response.data
            }
            // console.log("res", res)
            return res
        case 404:
            message.error("404~")
            return false
        default:
            message.error(response.status + '错误')
            return false
    }
})

const installer = {
    vm: {},
    install(Vue) {
        Vue.use(VueAxios, service)
    }
}

export {
    installer as VueAxios,
    service as axios
}
