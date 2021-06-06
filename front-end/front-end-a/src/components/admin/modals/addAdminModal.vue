<template>
  <a-modal :visible="addAdminVisible" title="添加管理员" @cancel="handleEditAdminInfoCancel">
    <template slot="footer">
      <a-button key="back" @click="handleEditAdminInfoCancel">
        返回
      </a-button>
      <a-button key="submit" type="primary" @click="handleEditAdminInfoSubmit">
        提交
      </a-button>
    </template>
    <a-form :form="form" class="form-container">
      <a-form-item label="用户名" class="form-item">
        <a-input
            v-decorator="['id', { rules: [{ required: true, message: '请输入用户名' }],
             }]"

        >
        </a-input>
      </a-form-item>
      <a-form-item label="权限" class="form-item">
        <a-input-number
            v-decorator="['permission', { rules: [{ required: true, message: '请输入权限' }],
             }]"
        >
        </a-input-number>
      </a-form-item>

      <a-form-item label="密码" class="form-item">
        <a-input-password
            v-decorator="['password', { rules: [{ required: true, message: '请输入密码' }],
             }]"
        >
        </a-input-password>
      </a-form-item>

    </a-form>
  </a-modal>
</template>

<script>
import {mapGetters, mapMutations, mapActions} from 'vuex'
export default {
  name: "addAdminModal",
  data() {
    return {
      form: this.$form.createForm(this, {name: 'addAdminForm'}),
    }
  },
  computed: {
    ...mapGetters([
      'addAdminVisible',
      "curAdmin",
    ]),
  },
  methods: {
    ...mapMutations([
      'setAddAdminVisibility'
    ]),
    ...mapActions([
      "addAdmin",
      "getAllAdmins",
    ]),
    handleEditAdminInfoSubmit(e) {
      e.preventDefault();
      this.form.validateFields((err, values) => {
        this.addAdmin(values)
        this.setAddAdminVisibility(false)
        this.form.resetFields()
      })
    },
    handleEditAdminInfoCancel(e) {
      this.setAddAdminVisibility(false)
      this.form.resetFields()
    },
  }
}
</script>

<style scoped>

</style>