<template>
  <div class="papers-area">
    <el-card style="text-align: left;width: 990px;margin: 35px auto 0 auto">
      <div>
        <span style="font-size: 20px"><strong>{{paper.paperTitle}}</strong></span>
        <el-divider content-position="left">{{paper.paperDate}}</el-divider>
        <div class="markdown-body">
          <div v-html="paper.paperContentHtml"></div>
        </div>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'Paper',
  data () {
    return {
      paper: []
    }
  },
  mounted () {
    this.loadPaper()
  },
  methods: {
    loadPaper () {
      var _this = this
      this.$axios.get('/paper/' + this.$route.query.id).then(resp => {
        if (resp && resp.status === 200) {
          _this.paper = resp.data
        }
      })
    }
  }
}
</script>

<style scoped>
  @import "../../styles/markdown.css";
</style>
