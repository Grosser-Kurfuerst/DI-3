<template>
  <div>
    <a-modal :visible="studentCourseVisible" @cancel="handleCancel" @ok="handleCancel">
      <a-table :columns="columns" :data-source="studentCourseMode === 0? studentCourseList:otherCourseList" rowKey="id"
               style="margin-top: 20px">
        <span slot="action" slot-scope="record">
            <a-popconfirm
                title="确认退选？"
                ok-text="是"
                cancel-text="否"
                @confirm="adminRemoveCourse(record.id)"
                v-if="studentCourseMode === 0"
            >
          <a-button type="danger">退选</a-button>
            </a-popconfirm>
            <a-popconfirm
                title="确认选课？"
                ok-text="是"
                cancel-text="否"
                @confirm="adminSelectCourse(record.id)"
                v-if="studentCourseMode === 1"
            >
          <a-button type="primary">选择</a-button>
            </a-popconfirm>
        </span>
      </a-table>
    </a-modal>
  </div>
</template>

<script>
import {mapActions, mapGetters, mapMutations} from "vuex";

const columns = [
  {
    title: '课程id',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: '课程名',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: '学时',
    dataIndex: 'time',
    key: 'time',
  },
  // {
  //   title: '教师',
  //   dataIndex: 'teacher',
  //   key: 'teacher',
  // },
  // {
  //   title: '地点',
  //   key: 'place',
  //   dataIndex: 'place',
  // },
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
    scopedSlots: {customRender: 'action'},
  },
];
export default {
  name: "studentCourseModal",
  data() {
    return {
      columns,
    }
  },
  computed: {
    ...mapGetters([
      "studentCourseVisible",
      "studentCourseMode",
      "studentCourseList",
      "otherCourseList",
    ])
  },
  methods: {
    ...mapMutations([
      "setStudentCourseVisibility"
    ]),
    ...mapActions([
      // "selectCourse",
      // "removeSelectCourse",
        "adminSelectCourse",
        "adminRemoveCourse",
    ]),
    handleCancel() {
      this.setStudentCourseVisibility(false)
    }
  },

}
</script>

<style scoped>

</style>