<template>
  <div>
    <a-table :columns="columns" :data-source="courseList" rowKey="id" style="margin-top: 20px">
        <span slot="action" slot-scope="record">
          <a-button type="primary" @click="editCourseInfo(record)" style="margin-right: 10px">编辑</a-button>
          <a-divider type="vertical"></a-divider>
          <a-button @click="showCourseSelectInfo(record)" style="margin-left: 10px">选课列表</a-button>
        </span>
    </a-table>
    <edit-course-info-modal></edit-course-info-modal>
    <course-select-info-modal></course-select-info-modal>
  </div>
</template>

<script>
import {mapActions, mapGetters, mapMutations} from "vuex";
import editCourseInfoModal from "@/components/admin/modals/editCourseInfoModal";
import courseSelectInfoModal from "@/components/admin/modals/courseSelectInfoModal";
const columns = [
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
    scopedSlots: {customRender: 'action'},
  },
];
export default {
  name: "courseList",
  components: {
    editCourseInfoModal,
    courseSelectInfoModal,
  },
  data() {
    return {
      columns,
    }
  },
  computed: {
    ...mapGetters([
      "courseList",
    ]),
  },
  methods: {
    ...mapMutations([
      'setEditCourseInfoVisibility',
      "setCourseSelectInfoVisibility",
      "setCurCourse",
        "setCurCourseSelectList",
    ]),
    ...mapActions([
       "getCourseSelectInfo",
    ]),
    editCourseInfo: function (record) {
      this.setCurCourse(record)
      this.setEditCourseInfoVisibility(true)
    },
    showCourseSelectInfo: function (record) {
      this.getCourseSelectInfo(record.id) // 设置当前selectList
      this.setCourseSelectInfoVisibility(true)
    }
  }
}
</script>

<style scoped>

</style>