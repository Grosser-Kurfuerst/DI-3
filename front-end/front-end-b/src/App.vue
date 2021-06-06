<template>
  <div id="app">
    <a-page-header
        style="border: 1px solid rgb(235, 237, 240);margin-bottom: 30px"
        title="教务系统"
        sub-title="竭诚为您服务"
    />
    <transition name="fade-transform" mode="out-in" id="content">
      <router-view/>
    </transition>
  </div>
</template>

<script>
export default {
  name: 'App',
  components: {
  }
}
</script>

<style>
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  /*text-align: center;*/
  /*color: #2c3e50;*/
  /*margin-top: 60px;*/
  color: #2c3e50;
  width: 100%;

}
#content{
  padding: 20px 70px 144px;
  min-height: 800px
}
</style>
