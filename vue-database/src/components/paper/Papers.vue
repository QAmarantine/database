<template>
  <div style="margin-top: 40px">
    <search-bar @onSearch="searchResult" ref="searchBar"></search-bar>
    <!--<el-button @click="addPaper()">添加论文</el-button>-->
    <div class="papers-area">
      <el-card style="text-align: left">
        <div v-for="paper in papers" :key="paper.id">
          <div style="float:left;width:85%;height: 150px;">
            <router-link class="paper-link" :to="{path:'jotter/paper',query:{id: paper.id}}"><span style="font-size: 20px"><strong>{{paper.paperTitle}}</strong></span></router-link>
            <el-divider content-position="left">{{paper.paperDate}}</el-divider>
            <router-link class="paper-link" :to="{path:'jotter/paper',query:{id: paper.id}}"><p>{{paper.paperAbstract}}</p></router-link>
          </div>
          <el-image
            style="margin:18px 0 0 30px;width:100px;height: 100px"
            :src="paper.paperCover"
            fit="cover"></el-image>
          <el-divider></el-divider>
        </div>
      </el-card>
    </div>
    <el-pagination
      background
      layout="total, prev, pager, next, jumper"
      @current-change="handleCurrentChange"
      :page-size="pageSize"
      :total="total">
    </el-pagination>
  </div>
</template>

<script>
import EditForm from './EditForm'
import SearchBar from './SearchBar'
export default {
  name: 'Papers',
  components: {EditForm, SearchBar},
  data () {
    return {
      papers: [],
      pageSize: 4,
      total: ''
    }
  },
  mounted () {
    this.loadPapers()
  },
  methods: {
    loadPapers () {
      var _this = this
      this.$axios.get('/paper/' + this.pageSize + '/1').then(resp => {
        if (resp && resp.status === 200) {
          _this.papers = resp.data.content
          _this.total = resp.data.totalElements
        }
      })
    },
    handleCurrentChange (page) {
      var _this = this
      this.$axios.get('/paper/' + this.pageSize + '/' + page).then(resp => {
        if (resp && resp.status === 200) {
          _this.papers = resp.data.content
          _this.total = resp.data.totalElements
        }
      })
    }
  }
}
</script>

<style scoped>
  .papers-area {
    width: 990px;
    height: 750px;
    margin-left: auto;
    margin-right: auto;
  }

  .paper-link {
    text-decoration: none;
    color: #606266;
  }

  .paper-link:hover {
    color: #409EFF;
  }
</style>
