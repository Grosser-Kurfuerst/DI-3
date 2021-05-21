import Vue from 'vue'
import Vuex from 'vuex'
import student from './modules/student'
import admin from './modules/admin'
import getters from './getters'

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        student,
        admin,
    },
    state: {},
    mutations: {},
    actions: {},
    getters
})
