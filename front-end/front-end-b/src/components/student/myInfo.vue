<template>
  <div class="my-info-container">
    <a-descriptions title="个人信息" bordered>
      <a-descriptions-item label="姓名">
        {{ this.studentInfo.name }}
      </a-descriptions-item>
      <a-descriptions-item label="性别">
        {{ this.studentInfo.gender }}
      </a-descriptions-item>
      <a-descriptions-item label="学号">
        {{ this.studentInfo.id }}
      </a-descriptions-item>
      <a-descriptions-item label="院系">
        {{ this.studentInfo.department }}
      </a-descriptions-item>
<!--      <a-descriptions-item label="年级">-->
<!--        {{ this.studentInfo.permission }}-->
<!--      </a-descriptions-item>-->
    </a-descriptions>
    <div class="my-info-btn-group"
         style="margin-top: 50px;display: flex; flex-direction: row;justify-content: center;align-items: center">
      <a-button type="primary" @click="showEditStudentInfoModal" style="margin-right: 50px">编辑信息</a-button>
      <a-button type="primary" @click="showChangePasswordModal">修改密码</a-button>
    </div>
    <a-modal v-model="editStudentInfoVisible" title="编辑基本信息">
      <template slot="footer">
        <a-button key="back" @click="handleEditStudentInfoCancel">
          返回
        </a-button>
        <a-button key="submit" type="primary" @click="handleEditStudentInfoSubmit">
          提交
        </a-button>
      </template>
      <a-form :form="form" class="form-container">
        <a-form-item label="姓名" class="form-item">
          <a-input
              v-decorator="['name', { rules: [{ required: true, message: '请输入姓名' }],
             initialValue: this.studentInfo.name
             }]"
          >
          </a-input>
        </a-form-item>
        <a-form-item label="性别" class="form-item">
          <a-input
              v-decorator="['gender', { rules: [{ required: true, message: '请输入性别' }],
             initialValue: this.studentInfo.gender
             }]"
          >
          </a-input>
        </a-form-item>
        <a-form-item label="院系" class="form-item">
          <a-input
              v-decorator="['department', { rules: [{ required: true, message: '请输入院系' }],
             initialValue: this.studentInfo.department
             }]"
          >
          </a-input>
        </a-form-item>

<!--        <a-form-item label="年级" class="form-item">-->
<!--          <a-input-->
<!--              v-decorator="['permission', { rules: [{ required: true, message: '请输入年级' }],-->
<!--             initialValue: this.studentInfo.permission-->
<!--             }]"-->
<!--          >-->
<!--          </a-input>-->
<!--        </a-form-item>-->

      </a-form>
    </a-modal>


    <a-modal v-model="changePasswordVisible" title="修改密码">
      <template slot="footer">
        <a-button key="back" @click="handleChangePasswordCancel">
          返回
        </a-button>
        <a-button key="submit" type="primary" @click="handleChangePasswordSubmit">
          提交
        </a-button>
      </template>
      <a-form :form="form2" class="form-container">
        <a-form-item label="原密码" class="form-item">
          <a-input-password
              v-decorator="['oldPassword', { rules: [{ required: true, message: '请输入原密码' }],
                    }]"
          />
        </a-form-item>
        <a-form-item label="新密码" class="form-item">
          <a-input-password
              v-decorator="['newPassword', { rules: [{ required: true, message: '请输入新密码' }],
                    }]"
          />
        </a-form-item>
        <a-form-item label="确认新密码" class="form-item">
          <a-input-password
              v-decorator="['confirmNewPassword', { rules: [{ required: true, message: '请确认新密码' }],
                    }]"
          />
        </a-form-item>
      </a-form>
    </a-modal>
  </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'
import {message} from "ant-design-vue";

export default {
  name: "myInfo",
  data() {
    return {
      editStudentInfoVisible: false,
      changePasswordVisible: false,
      form: this.$form.createForm(this, {name: 'editStudentInfoForm'}),
      form2: this.$form.createForm(this, {name: 'changePasswordForm'}),

    }
  },
  computed: {
    ...mapGetters([
      'studentInfo',
    ])
  },
  created() {
  },
  methods: {
    ...mapActions([
        'updateStudentInfo',
    ]),
    showChangePasswordModal: function () {
      this.changePasswordVisible = true
    },
    showEditStudentInfoModal: function () {
      this.editStudentInfoVisible = true
    },
    handleEditStudentInfoSubmit(e) {
      e.preventDefault();
      this.form.validateFields((err, values) => {
        if (!err) {
          console.log(values)
          // department: "123"
          // gender: "123"
          // name: "123"
          // permission: "123"
          let newStudentInfo = this.studentInfo
          newStudentInfo.department = values.department
          newStudentInfo.gender = values.gender
          newStudentInfo.name = values.name
          newStudentInfo.permission = values.permission
          this.updateStudentInfo(newStudentInfo)
          this.editStudentInfoVisible = false
        }
      });
      this.editStudentInfoVisible = false
      this.form.resetFields()
    },
    handleEditStudentInfoCancel() {
      this.editStudentInfoVisible = false
      this.form.resetFields()
    },
    handleChangePasswordSubmit(e) {
      e.preventDefault();
      this.form2.validateFields((err, values) => {
        if (!err) {
          console.log(values)
          // confirmNewPassword: "1231"
          // newPassword: "123"
          // oldPassword: "123123"
          if (values.newPassword !== values.confirmNewPassword) {
            message.error('两次密码不一致')
            return
          }
          else if (values.oldPassword !== this.studentInfo.password) {
            message.error('旧密码错误')
            return;
          }
          let newStudentInfo = this.studentInfo
          newStudentInfo.password = values.newPassword
          this.updateStudentInfo(newStudentInfo)
          this.changePasswordVisible = false
        }
      });
      this.form2.resetFields()
    },
    handleChangePasswordCancel() {
      this.changePasswordVisible = false
      this.form2.resetFields()
    }
  }
}
</script>

<style scoped>

</style>