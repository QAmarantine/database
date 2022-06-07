<template>
  <el-container>
    <el-aside style="width: 200px;margin-top: 20px">
      <switch></switch>
      <SideMenu @indexSelect="listByCategory" ref="sideMenu"></SideMenu>
    </el-aside>
    <el-main>
      <papers class="papers-area" ref="papersArea"></papers>
    </el-main>
  </el-container>
</template>

<script>
import SideMenu from './SideMenu'
import Papers from './Papers'
export default {
  name: 'PaperIndex',
  components: {Papers, SideMenu},
  methods: {
    listByCategory () {
      var _this = this
      var cid = this.$refs.sideMenu.cid
      var url = 'categories/' + cid + '/papers'
      this.$axios.get(url).then(resp => {
        if (resp && resp.status === 200) {
          _this.$refs.papersArea.papers = resp.data
        }
      })
    }
  }
}
</script>

<style scoped>
  .papers-area {
    width: 990px;
    margin-left: auto;
    margin-right: auto;
  }
</style>
