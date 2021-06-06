<template>
  <a-modal :visible="editAdminInfoVisible" title="编辑管理员信息" @cancel="handleEditAdminInfoCancel">
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
             initialValue: this.curAdmin.id
             }]"

        >
        </a-input>
      </a-form-item>
      <a-form-item label="权限" class="form-item">
        <a-input-number
            v-decorator="['permission', { rules: [{ required: true, message: '请输入权限' }],
             initialValue: this.curAdmin.permission
             }]"
        >
        </a-input-number>
      </a-form-item>

      <a-form-item label="密码" class="form-item">
        <a-input-password
                          v-decorator="['password', { rules: [{ required: true, message: '请输入密码' }],
             initialValue: this.curAdmin.password
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
  name: "editAdminInfoModal",
  data() {
    return {
      form: this.$form.createForm(this, {name: 'editStudentInfoForm'}),
    }
  },
  computed: {
    ...mapGetters([
      'editAdminInfoVisible',
      "curAdmin",
    ]),
  },
  methods: {
    ...mapMutations([
      'setEditAdminInfoVisibility'
    ]),
    ...mapActions([
      "updateAdminInfo",
      "getAllAdmins",
    ]),
    handleEditAdminInfoSubmit(e) {
      e.preventDefault();
      this.form.validateFields((err, values) => {
        this.updateAdminInfo(values)
        this.setEditAdminInfoVisibility(false)
        this.form.resetFields()
      })
    },
    handleEditAdminInfoCancel(e) {
      this.setEditAdminInfoVisibility(false)
      this.form.resetFields()
    },
  }
}
</script>

<style scoped>

</style>