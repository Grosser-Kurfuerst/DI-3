import {
    getAllCoursesAPI,
    getOtherDepartmentCoursesAPI,
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
            const res2 = await getOtherDepartmentCoursesAPI()
            // console.log('所有课程', res)
            // console.log('其他院系课程',res2)
            res.data = res.data.concat(res2.data)
            console.log('所有课程res', res)

            if (res.data !== undefined) {
                let translatedRes = res.data.map((x) => {
                    return {
                        id: x.coursenum,
                        name: x.coursename,
                        // time: x.ctm,
                        point: x.credit,
                        teacher: x.teacher,
                        place: x.place,
                        share: x.share === 'N' ? '否':'是',
                        permission: x.permission,
                    }
                })
                commit('setCourseList', translatedRes)
                console.log('所有课程', state.courseList)
            }
        }
    },
}
export default course