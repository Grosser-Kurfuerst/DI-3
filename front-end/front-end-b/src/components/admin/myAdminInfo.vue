<template>
  <div class="my-info-container">
    <a-descriptions title="个人信息" bordered>
      <a-descriptions-item label="账户">
        {{ this.adminInfo.id }}
      </a-descriptions-item>
      <a-descriptions-item label="权限">
        {{ this.adminInfo.permission }}
      </a-descriptions-item>
<!--      <a-descriptions-item label="密码">-->
<!--        {{ this.adminInfo.permission }}-->
<!--      </a-descriptions-item>-->
    </a-descriptions>
    <div class="my-info-btn-group"
         style="margin-top: 50px;display: flex; flex-direction: row;justify-content: center;align-items: center">
      <a-button type="primary" @click="showEditAdminInfoModal" style="margin-right: 0">编辑信息</a-button>
    </div>
    <edit-admin-info-modal></edit-admin-info-modal>
  </div>
</template>

<script>
import {mapGetters, mapActions, mapMutations} from 'vuex'
import {message} from "ant-design-vue";
import editAdminInfoModal from "@/components/admin/modals/editAdminInfoModal";
export default {
  name: "myAdminInfo",
  data(){
    return{

    }
  },
  components:{
    editAdminInfoModal,
  },
  computed:{
    ...mapGetters([
        'adminInfo',
    ])
  },
  methods:{
    ...mapMutations([
        "setEditAdminInfoVisibility",
        "setCurAdmin",
        "setAdminInfo"
    ]),
    showEditAdminInfoModal(){
      this.setCurAdmin(this.adminInfo)
      this.setEditAdminInfoVisibility(true)
    }
  }
}
</script>

<style scoped>

</style>