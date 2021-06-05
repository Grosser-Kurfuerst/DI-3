import Vue from 'vue'
import Vuex from 'vuex'
import student from './modules/student'
import course from './modules/course'
import admin from './modules/admin'
import getters from './getters'

Vue.use(Vuex)

const store = new Vuex.Store({
    modules: {
        student,
        course,
        admin,
    },
    state: {},
    mutations: {},
    actions: {},
    getters
})
export default store