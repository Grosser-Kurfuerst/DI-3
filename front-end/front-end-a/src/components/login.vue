<template>
  <div>
    <div style="display: flex;flex-direction: column;align-items: center;justify-content: center">
      <h1 style="text-align: center; display: inline;">{{ this.userType === 'student' ? '学生登录' : '管理员登录' }}</h1>
      <a style="display: inline;" @click="switchRole">切换身份</a>
    </div>
    <a-form :form="form" @submit="handleSubmit" class="form-container">
      <a-form-item label="用户名/学号" class="form-item">
        <a-input
            v-decorator="['username', { rules: [{ required: true, message: '请输入用户名' }],
             //initialValue: '00000001c'
             initialValue: '0001a'
             }]"
        >
          <!--        <a-icon slot="prefix" type="user" />-->
        </a-input>
      </a-form-item>
      <a-form-item label="密码" class="form-item">
        <a-input-password
            v-decorator="['password', { rules: [{ required: true, message: '请输入密码' }],
             initialValue: '123'
             //initialValue: '12345678'
            }]"
        />
      </a-form-item>
      <a-form-item class="form-item"
                   style="display: flex; align-items: center;justify-content: center;">
        <a-button type="primary" html-type="submit">
          登录
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'
import {message} from "ant-design-vue";

export default {
  name: "login",
  data() {
    return {
      form: this.$form.createForm(this, {name: 'coordinated'}),
      userType: 'student',
    }
  },
  created() {
    this.getAllCourses()
  },
  methods: {
    ...mapActions([
      'login',
      "adminLogin",
      'getAllCourses',
    ]),
    switchRole() {
      if (this.userType === 'student') {
        this.userType = 'admin'
      }
      else {
        this.userType = 'student'
      }
      message.success('切换成功')
    },
    handleSubmit(e) {
      e.preventDefault();
      this.form.validateFields((err, values) => {
        if (!err) {
          const data = {
            username: values.username,
            password: values.password,
          }
          if(this.userType === 'student'){
            this.login(data)
          }
          else if(this.userType === 'admin'){
            this.adminLogin(data)
          }
        }
      });
    },
  },
}
</script>

<style scoped>
.form-container {
  width: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
}

.form-item {
  width: 25%;
}

</style>