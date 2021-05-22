<template>
  <div>
    <h1 style="text-align: center;">登录</h1>
    <a-form :form="form" @submit="handleSubmit" class="form-container">
      <a-form-item label="用户名/学号" class="form-item">
        <a-input
            v-decorator="['username', { rules: [{ required: true, message: '请输入用户名' }],
             initialValue: '123456788'
             }]"
        >
          <!--        <a-icon slot="prefix" type="user" />-->
        </a-input>
      </a-form-item>
      <a-form-item label="密码" class="form-item">
        <a-input-password
            v-decorator="['password', { rules: [{ required: true, message: '请输入密码' }],
             initialValue: '123456'
            }]"

        />
      </a-form-item>
      <a-form-item class="form-item"
                   style="display: flex; align-items: center;justify-content: center;">
        <a-button type="primary" html-type="submit">
          Submit
        </a-button>
      </a-form-item>
    </a-form>
  </div>
</template>

<script>
import {mapGetters, mapActions} from 'vuex'

export default {
  name: "login",
  data() {
    return {
      formLayout: 'horizontal',
      form: this.$form.createForm(this, {name: 'coordinated'}),
    }
  },
  methods: {
    ...mapActions([
      'login'
    ]),
    handleSubmit(e) {
      e.preventDefault();
      this.form.validateFields((err, values) => {
        if (!err) {
          const data = {
            username: values.username,
            password: values.password,
          }
          this.login(data)
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