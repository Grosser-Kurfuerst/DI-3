<template>
  <div class="container">
    <h2>我(管理员)</h2>
    <div class="tab-pane">
      <a-tabs default-active-key="0" @change="onTabChange">
        <a-tab-pane key="0" tab="学生管理">
          <student-list></student-list>
        </a-tab-pane>
        <a-tab-pane key="1" tab="课程管理" force-render>
          <course-list></course-list>
        </a-tab-pane>
<!--        v-if="Number(this.adminInfo.permission) > 1"-->
        <a-tab-pane  key="2" tab="管理员列表" force-render>
          <admin-list></admin-list>
        </a-tab-pane>
        <a-tab-pane key="3" tab="我的信息" force-render>
          <my-admin-info></my-admin-info>
        </a-tab-pane>
      </a-tabs>
    </div>
  </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'
import studentList from "@/components/admin/studentList";
import courseList from "@/components/admin/courseList";
import adminList from "@/components/admin/adminList";
import myAdminInfo from "@/components/admin/myAdminInfo";
export default {
  name: "studentIndex",
  components: {
    studentList,
    courseList,
    adminList,
    myAdminInfo,
  },
  data() {
    return {
      editMode: false
    }
  },
  computed: {
    ...mapGetters([
      'adminInfo',
      'adminList',
      'studentList',
      "courseList",
    ])
  },
  methods: {
    onTabChange: function (key) {
      console.log(this.adminList)
    }
  },
}
</script>

<style scoped>
.container {
  padding: 20px 70px 144px;
}
</style>