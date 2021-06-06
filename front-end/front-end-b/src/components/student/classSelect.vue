<template>
  <div>
  <h3 style="display: inline;margin: 30px 40px 30px 0">当前权限：{{this.studentInfo.permission}}</h3>
    <a-tooltip style="display: inline">
      <template slot="title">
        自身权限大于等于课程权限的时候，才可以选课
      </template>
      <a>权限说明</a>
    </a-tooltip>


  <a-table :columns="columns" :data-source="courseList" rowKey="id" style="margin-top: 20px">
        <span slot="action" slot-scope="record">
            <a-popconfirm
                title="确认退选？"
                ok-text="是"
                cancel-text="否"
                @confirm="removeSelectCourse(record.id)"
                v-if="studentCourses.find((x) => {return  x.id === record.id}) !== undefined"
            >
          <a-button type="danger" >退选</a-button>
            </a-popconfirm>
            <a-popconfirm
                title="确认选课？"
                ok-text="是"
                cancel-text="否"
                @confirm="selectCourse(record.id)"
                v-else
            >
          <a-button type="primary" >选择</a-button>
            </a-popconfirm>
        </span>
  </a-table>
  </div>
</template>

<script>
import {mapActions, mapGetters} from "vuex";

const columns = [
  {
    title: '课程id',
    dataIndex: 'id',
    key: 'id',
  },
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
    title: '权限',
    key: 'permission',
    dataIndex: 'permission'
  },
  {
    title: '操作',
    key: 'action',
    scopedSlots: { customRender: 'action' },
  },
];
export default {
  name: "classSelect",
  data(){
    return{
      columns,
    }
  },
  computed:{
    ...mapGetters([
        'studentCourses',
        "courseList",
        "studentInfo",
    ]),
  },
  methods:{
    ...mapActions([
        "selectCourse",
        "removeSelectCourse",
    ])
  }
}
</script>

<style scoped>

</style>