<template>
  <div class="container">
    <h2>我</h2>
    <div class="tab-pane">
      <a-tabs default-active-key="0" @change="onTabChange">
        <a-tab-pane key="0" tab="我的课程">
          <my-courses></my-courses>

        </a-tab-pane>
        <a-tab-pane key="1" tab="学期选课" force-render>
          <class-select></class-select>
        </a-tab-pane>
        <a-tab-pane key="2" tab="我的信息" force-render>
<!--          {{this.studentInfo}}}-->
         <my-info></my-info>
        </a-tab-pane>
      </a-tabs>
    </div>
  </div>
</template>

<script>
import {mapGetters,mapActions} from 'vuex'
import myInfo from "@/components/student/myInfo";
import myCourses from "@/components/student/myCourses";
import classSelect from "@/components/student/classSelect";
export default {
  name: "studentIndex",
  components:{
    myInfo,
    myCourses,
    classSelect,
  },
  data() {
    return {
      editMode: false
    }
  },
  computed:{
    ...mapGetters([
        'studentInfo',
        'studentCourses'
    ])
  },
  methods: {
    onTabChange: function (key) {
      console.log(key)
    }
  },
}
</script>

<style scoped>
.container{
  padding: 20px 70px 144px;
}
</style>