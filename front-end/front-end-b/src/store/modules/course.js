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
        // courseId: "0001b"
        // courseName: "sjjc"
        // courseTime: "4"
        // powerGrade: 3
        // score: "2"
        // shareFlag: "0"
        // teacherName: "6f"
        // teachingPlace: "202"
        async getAllCourses({commit, state}) {
            const res = await getAllCoursesAPI()
            const res2 = await getOtherDepartmentCoursesAPI()
            // console.log('所有课程', res)
            // console.log('其他院系课程',res2)
            res.data = res.data.concat(res2.data)
            if (res.data !== undefined) {
                let translatedRes = res.data.map((x) => {
                    return {
                        id: x.courseId,
                        name: x.courseName,
                        time: x.courseTime,
                        point: x.score,
                        teacher: x.teacherName,
                        place: x.teachingPlace,
                        share: x.shareFlag === 'N' ? '否':'是',
                        permission: x.powerGrade,
                    }
                })
                commit('setCourseList', translatedRes)
                // console.log('所有课程', state.courseList)
            }
        }
    },
}
export default course