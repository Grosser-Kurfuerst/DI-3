<template>
  <div class="hello">
      <a-button @click="testBtnClicked">test</a-button>
  </div>
</template>

<script>
import { mapActions} from "vuex"
export default {
  name: 'HelloWorld',
  props: {
    msg: String
  },
  methods: {
    ...mapActions([
      'login',
        'tmp',
    ]),
    testBtnClicked:function () {
      // const userInfo = {
      //   sno: "123456788",
      //   pwd:"123456",
      // }
      // console.log("尝试登录")
      // this.login(userInfo)

      // const  data = {
      //   cno:'1234',
      //   share:'N',
      // }
      // this.tmp(data)
      console.log(111)
      this.$router.push('/login')
    }
  }

}
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
h3 {
  margin: 40px 0 0;
}
ul {
  list-style-type: none;
  padding: 0;
}
li {
  display: inline-block;
  margin: 0 10px;
}
a {
  color: #42b983;
}
</style>
