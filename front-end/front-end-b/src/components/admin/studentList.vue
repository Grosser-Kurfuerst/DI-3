<template>
  <div>
    <a-table :columns="columns" :data-source="studentList" rowKey="id">
            <span slot="action" slot-scope="record">
              <a-button type="primary" @click="editStudentInfo(record)">编辑信息</a-button>
              <a-divider type="vertical"></a-divider>
              <a-button @click="showSelectedCourses(record)">已选课程</a-button>
              <a-divider type="vertical"></a-divider>
              <a-button @click="showOtherCourses(record)">未选课程</a-button>
            </span>
    </a-table>
    <edit-student-info-modal></edit-student-info-modal>
    <student-course-modal></student-course-modal>
  </div>
</template>

<script>
import {mapGetters, mapActions, mapMutations} from "vuex";
import editStudentInfoModal from "@/components/admin/modals/editStudentInfoModal";
import studentCourseModal from "@/components/admin/modals/studentCourseModal";

const columns = [
  {
    title: '学号',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: '姓名',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: '性别',
    dataIndex: 'gender',
    key: 'gender',
  },
  {
    title: '院系',
    key: 'department',
    dataIndex: 'department',
  },
  {
    title: '权限/年级',
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
  name: "studentList",
  components: {
    editStudentInfoModal,
    studentCourseModal
  },
  data() {
    return {
      columns,
    }
  },
  computed: {
    ...mapGetters([
      'studentList',
      'editStudentInfoVisible',
      "courseList"
    ])
  },
  methods: {
    ...mapMutations([
      "setEditStudentInfoVisibility",
      "setCurStudent",
      "setStudentCourseVisibility",
      "setStudentCourseMode",
      "setStudentCourseList", // 已选
      "setOtherCourseList", // 未选
    ]),
    ...mapActions([
      "getAdminStudentCourse",
    ]),
    editStudentInfo: function (record) {
      console.log(record)
      this.setCurStudent(record)
      this.setEditStudentInfoVisibility(true)
    },
    showSelectedCourses: function (record) {
      this.setCurStudent(record)
      this.setStudentCourseMode(0)
      this.getAdminStudentCourse(record.id)

    },
    showOtherCourses: function (record) {
      this.setCurStudent(record)
      this.setStudentCourseMode(1)
      this.getAdminStudentCourse(record.id)
    }
  }
}
</script>
<style scoped>

</style>