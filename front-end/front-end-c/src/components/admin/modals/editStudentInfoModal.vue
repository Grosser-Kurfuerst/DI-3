<template>
  <a-modal :visible="editStudentInfoVisible" title="编辑学生信息" @cancel="handleEditStudentInfoCancel">
    <template slot="footer">
      <a-button key="back" @click="handleEditStudentInfoCancel">
        返回
      </a-button>
      <a-button key="submit" type="primary" @click="handleEditStudentInfoSubmit">
        提交
      </a-button>
    </template>
    <a-form :form="form" class="form-container">
      <a-form-item label="学号" class="form-item">
        <a-input
            v-decorator="['id', { rules: [{ required: true, message: '请输入学号' }],
             initialValue: this.curStudent.id
             }]"
        >
        </a-input>
      </a-form-item>
      <a-form-item label="姓名" class="form-item">
        <a-input
            v-decorator="['name', { rules: [{ required: true, message: '请输入姓名' }],
             initialValue: this.curStudent.name
             }]"
        >
        </a-input>
      </a-form-item>
      <a-form-item label="性别" class="form-item">
        <a-input
            v-decorator="['gender', { rules: [{ required: true, message: '请输入性别' }],
             initialValue: this.curStudent.gender
             }]"
        >
        </a-input>
      </a-form-item>
      <a-form-item label="院系" class="form-item">
        <a-input
            v-decorator="['department', { rules: [{ required: true, message: '请输入院系' }],
             initialValue: this.curStudent.department
             }]"
        >
        </a-input>
      </a-form-item>

      <a-form-item label="年级" class="form-item">
        <a-input
            v-decorator="['permission', { rules: [{ required: true, message: '请输入年级' }],
             initialValue: this.curStudent.permission
             }]"
        >
        </a-input>
      </a-form-item>

<!--      <a-form-item label="密码" class="form-item">-->
<!--        <a-input-password disabled-->
<!--            v-decorator="['password', { rules: [{ required: true, message: '请输入密码' }],-->
<!--             initialValue: '123456'-->
<!--             }]"-->
<!--        >-->
<!--        </a-input-password>-->
<!--      </a-form-item>-->

    </a-form>
  </a-modal>
</template>

<script>
import {mapGetters, mapMutations, mapActions} from 'vuex'

export default {
  name: "editStudentInfoModal",
  props: [],
  data() {
    return {
      form: this.$form.createForm(this, {name: 'editStudentInfoForm'}),
      // modalVisible: false,
    }
  },
  computed: {
    ...mapGetters([
      'editStudentInfoVisible',
      "curStudent",
    ]),
  },
  watch: {},
  methods: {
    ...mapMutations([
      'setEditStudentInfoVisibility'
    ]),
    handleEditStudentInfoSubmit(e) {
      e.preventDefault();
      this.form.validateFields((err, values) => {
        if (!err) {
          values.password = '123456' // TODO 修改

        }
        this.setEditStudentInfoVisibility(false)
        this.form.resetFields()
      })
    },
    handleEditStudentInfoCancel(e) {
      this.setEditStudentInfoVisibility(false)
      this.form.resetFields()
    },
  }

}
</script>

<style scoped>

</style>