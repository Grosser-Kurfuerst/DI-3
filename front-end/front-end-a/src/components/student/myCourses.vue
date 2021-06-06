<template>
  <a-table :columns="columns" :data-source="studentCourses" rowKey="id">
  </a-table>
</template>

<script>
const columns = [
  {
    title:'课程名',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: '学时',
    dataIndex: 'time',
    key: 'time',
  },
  {
    title: '教师',
    dataIndex: 'teacher',
    key: 'teacher',
  },
  {
    title: '地点',
    key: 'place',
    dataIndex: 'place',
  },
  {
    title: '是否共享',
    key: 'share',
    dataIndex: 'share'
  },
  {
    title: '成绩',
    key: 'grade',
    dataIndex: 'grade'
  },
];
import {mapActions,mapGetters} from 'vuex'
export default {
  name: "myCourses",
  data(){
    return {
      courseInfo:[],
      columns,
    }
  },
  computed:{
    ...mapGetters([
        'studentCourses'
    ])
  },
  methods:{
  },
  created() {
    this.courseInfo = this.studentCourses
  }
}
</script>

<style scoped>

</style>