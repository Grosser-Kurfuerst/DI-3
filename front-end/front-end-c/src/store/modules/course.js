import {
    getAllCoursesAPI
} from "@/api/course";

const course = {
    state: {
        courseList: [], // 全部课程
        /* 列表项
      course: {
          id: '',
          name: '',
          time: '', //学时
          point:'', //学分
          teacher: '', //教师名
          place: '', //地点
          share: '', // 是否共享
          permission: '' // 权限 [0,4]
      },
       */
    },
    getters: {
        getCourseById: (state) => (id) => {
            return state.courseList.find(x => x.id === id)
        },
    },
    mutations: {
        setCourseList: function (state, courseList) {
            state.courseList = courseList
        },
    },
    actions: {
        async getAllCourses({commit, state}) {
            const res = await getAllCoursesAPI()
            if (res.data !== undefined) {
                let translatedRes = res.data.map((x) => {
                    return {
                        id: x.cno,
                        name: x.cnm,
                        time: x.ctm,
                        point: x.cpt,
                        teacher: x.tec,
                        place: x.pla,
                        share: x.share === 'Y' ? '是':'否',
                        permission: x.permission,
                    }
                })
                commit('setCourseList', translatedRes)
                // console.log('所有课程', state.courseList)
            }
        }
    },
}
export default course