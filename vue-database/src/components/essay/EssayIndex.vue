<template>
  <el-container>
    <el-aside style="width: 200px;margin-top: 20px">
      <switch></switch>
      <SideMenu @indexSelect="listByCategory" ref="sideMenu"></SideMenu>
    </el-aside>
    <el-main>
      <essays class="essays-area" ref="essaysArea"></essays>
    </el-main>
  </el-container>
</template>

<script>
import SideMenu from './SideMenu'
import Essays from './Essays'

export default {
  name: 'AppEssay',
  components: {Essays, SideMenu},
  methods: {
    listByCategory () {
      var _this = this
      var cid = this.$refs.sideMenu.cid
      var url = 'categories/' + cid + '/essays'
      this.$axios.get(url).then(resp => {
        if (resp && resp.status === 200) {
          _this.$refs.essaysArea.essays = resp.data
        }
      })
    }
  }
}
</script>

<style scoped>
  .essays-area {
    width: 990px;
    margin-left: auto;
    margin-right: auto;
  }
</style>
