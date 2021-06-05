<template>
  <div>
    <a-modal :visible="courseSelectInfoVisible" @cancel="handleCourseSelectInfoCancel">
      <template slot="footer">
        <a-button key="back" @click="handleCourseSelectInfoCancel">
          返回
        </a-button>
      </template>
      <a-table :columns="columns" :data-source="curCourseSelectList" rowKey="studentId" style="margin-top: 20px">
        <span slot="action" slot-scope="record">
          <a-button type="primary" @click="editGrade(record)" style="margin-right: 10px">修改成绩</a-button>
        </span>
      </a-table>
      <a-modal
          title="选课列表"
          :visible="editGradeVisible"
          @ok="handleEditGradeSubmit"
          @cancel="handleEditGradeCancel"
      >
        <a-form :form="form" class="form-container">
          <a-form-item label="成绩" class="form-item">
            <a-input-number
                v-decorator="['grade', { rules: [{ required: true, message: '请输入课程成绩' }],
             initialValue: (this.curSelectInfo.grade)
             }]"
            >
            </a-input-number>
          </a-form-item>
        </a-form>
      </a-modal>
    </a-modal>
  </div>
</template>

<script>
import {mapActions, mapGetters, mapMutations} from "vuex";
import {message} from "ant-design-vue";

const columns = [
  {
    title: '学号',
    dataIndex: 'studentId',
    key: 'studentId',
  },
  {
    title: '成绩',
    dataIndex: 'grade',
    key: 'grade',
  },
  {
    title: '操作',
    key: 'action',
    scopedSlots: {customRender: 'action'},
  },
];
export default {
  name: "courseSelectInfoModal",
  components: {},
  data() {
    return {
      columns,
      form: this.$form.createForm(this, {name: 'editGradeForm'}),
      curSelectInfo: {}, // courseId, studentId, grade
    }
  },
  computed: {
    ...mapGetters([
      "editGradeVisible",
      "courseSelectInfoVisible",
      "curCourseSelectList",
    ]),
  },
  methods: {
    ...mapMutations([
      "setEditGradeVisibility",
      "setCourseSelectInfoVisibility",
    ]),
    ...mapActions([
      "updateCourseGrade"
    ]),
    editGrade: function (record) {
      this.curSelectInfo = record
      console.log(record)
      this.setEditGradeVisibility(true)
    },
    handleEditGradeSubmit(e) {
      e.preventDefault();
      this.form.validateFields((err, values) => {
        if (!err) {
          // if(values.grade === '暂无'){
          //   message.error('请输入成绩')
          //   return
          // }
          values.courseId = this.curSelectInfo.courseId
          values.studentId = this.curSelectInfo.studentId
          this.updateCourseGrade(values)
          this.setEditGradeVisibility(false)
          this.form.resetFields()
        }
      })
    },
    handleEditGradeCancel() {
      this.setEditGradeVisibility(false)
      this.form.resetFields()
    },
    handleCourseSelectInfoCancel() {
      this.setCourseSelectInfoVisibility(false)
    },
    handleCourseSelectInfoSubmit() {
      this.setCourseSelectInfoVisibility(false)
    },
  }
}
</script>

<style scoped>

</style>