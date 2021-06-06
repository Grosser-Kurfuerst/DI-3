<template>
  <a-modal :visible="editCourseInfoVisible" title="编辑课程信息" @cancel="handleCancel">
    <template slot="footer">
      <a-button key="back" @click="handleCancel">
        返回
      </a-button>
      <a-button key="submit" type="primary" @click="handleSubmit">
        提交
      </a-button>
    </template>
    <a-form :form="form" class="form-container">
      <a-form-item label="课程id" class="form-item">
        <a-input
            v-decorator="['id', { rules: [{ required: true, message: '请输入课程id' }],
             initialValue: this.curCourse.id
             }]"
        >
        </a-input>
      </a-form-item>
      <a-form-item label="课程名" class="form-item">
        <a-input
            v-decorator="['name', { rules: [{ required: true, message: '请输入课程名' }],
             initialValue: this.curCourse.name
             }]"
        >
        </a-input>
      </a-form-item>
      <a-form-item label="学时" class="form-item">
        <a-input-number
            v-decorator="['time', { rules: [{ required: true, message: '请输入学时' }],
             initialValue: Number(this.curCourse.time)
             }]"
        >
        </a-input-number>
      </a-form-item>
      <a-form-item label="学分" class="form-item">
        <a-input-number
            v-decorator="['point', { rules: [{ required: true, message: '请输入学分' }],
             initialValue: Number(this.curCourse.point)
             }]"
        >
        </a-input-number>
      </a-form-item>

      <a-form-item label="教师名" class="form-item">
        <a-input
            v-decorator="['teacher', { rules: [{ required: true, message: '请输入教师名' }],
             initialValue: this.curCourse.teacher
             }]"
        >
        </a-input>
      </a-form-item>
      <a-form-item label="地点" class="form-item">
        <a-input
            v-decorator="['place', { rules: [{ required: true, message: '请输入地点' }],
             initialValue: this.curCourse.place
             }]"
        >
        </a-input>
      </a-form-item>
      <a-form-item label="是否共享" class="form-item">
        <a-select
            v-decorator="['share', { rules: [{ required: true, message: '请输入是否共享' }],
             initialValue: this.curCourse.share
             }]">
          <a-select-option value="是">
            是
          </a-select-option>
          <a-select-option value="否">
            否
          </a-select-option>
        </a-select>
      </a-form-item>

      <a-form-item label="年级/权限" class="form-item">
        <a-input-number
            v-decorator="['permission', { rules: [{ required: true, message: '请输入课程权限' }],
             initialValue: Number(this.curCourse.permission)
             }]"
        >
        </a-input-number>
      </a-form-item>


    </a-form>
  </a-modal>
</template>

<script>
import {mapGetters, mapMutations, mapActions} from 'vuex'

export default {
  name: "editCourseInfoModal",
  data() {
    return {
      form: this.$form.createForm(this, {name: 'editStudentInfoForm'}),
      // modalVisible: false,
    }
  },
  computed: {
    ...mapGetters([
      "editCourseInfoVisible",
      "curCourse",
    ]),
  },
  watch: {},
  methods: {
    ...mapMutations([
      'setEditCourseInfoVisibility'
    ]),
    ...mapActions([
        'updateCourseInfo',
    ]),
    handleSubmit(e) {
      e.preventDefault();
      this.form.validateFields((err, values) => {
        if (!err) {
          console.log(values)
          this.updateCourseInfo(values)
          this.setEditCourseInfoVisibility(false)
          this.form.resetFields()
        }
      })
    },
    handleCancel(e) {
      this.setEditCourseInfoVisibility(false)
      this.form.resetFields()
    },
  }

}
</script>

<style scoped>

</style>