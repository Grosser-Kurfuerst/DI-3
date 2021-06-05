<template>
  <div>
    <a-table :columns="columns" :data-source="studentList" rowKey="id">
            <span slot="action" slot-scope="record">
              <a-button @click="editStudentInfo(record)">编辑信息</a-button>
            </span>
    </a-table>
    <edit-student-info-modal></edit-student-info-modal>
  </div>
</template>

<script>
import {mapGetters, mapActions, mapMutations} from "vuex";
import editStudentInfoModal from "@/components/admin/modals/editStudentInfoModal";
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
  components:{
    editStudentInfoModal
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
    ])
  },
  methods: {
    ...mapMutations([
      "setEditStudentInfoVisibility",
        "setCurStudent",
    ]),
    editStudentInfo: function (record) {
      console.log(record)
      this.setCurStudent(record)
      this.setEditStudentInfoVisibility(true)
    }
  }
}
</script>
<style scoped>

</style>