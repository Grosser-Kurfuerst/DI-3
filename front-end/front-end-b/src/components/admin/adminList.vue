<template>
  <div>
    <a-button type="primary" @click="addAdminClicked" style="margin-top: 10px;margin-bottom: 20px">+添加管理员</a-button>
    <a-table :columns="columns" :data-source="adminList" rowKey="id">
            <span slot="action" slot-scope="record">
              <a-button @click="editAdminInfo(record)">编辑管理员信息</a-button>
            </span>
    </a-table>
    <edit-admin-info-modal></edit-admin-info-modal>
    <add-admin-modal></add-admin-modal>
  </div>
</template>

<script>
import {mapGetters, mapActions, mapMutations} from "vuex";
import editAdminInfoModal from "@/components/admin/modals/editAdminInfoModal";
import addAdminModal from "@/components/admin/modals/addAdminModal";
const columns = [
  {
    title: '用户名',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: '权限',
    key: 'permission',
    dataIndex: 'permission'
  },
  {
    title: '操作',
    key: 'action',
    scopedSlots: {customRender: 'action'},
  },
];
export default {
  name: "adminList",
  components:{
    editAdminInfoModal,
    addAdminModal,
  },
  data() {
    return {
      columns,
    }
  },
  computed: {
    ...mapGetters([
      'adminList',
    ])
  },
  methods: {
    ...mapMutations([
      "setEditAdminInfoVisibility",
      "setAddAdminVisibility",
      "setCurAdmin",
    ]),
    editAdminInfo: function (record) {
      this.setCurAdmin(record)
      this.setEditAdminInfoVisibility(true)
    },
    addAdminClicked:function () {
      this.setAddAdminVisibility(true)
    }
  }
}
</script>

<style scoped>

</style>