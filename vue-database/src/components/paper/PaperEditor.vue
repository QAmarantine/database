<template>
  <div>
    <el-row style="margin: 18px 0px 0px 18px ">
    </el-row>
    <el-link href="/paper" :underline="false" target="_blank" class="add-link">
      <el-button type="success">写论文</el-button>
    </el-link>
    <el-card style="margin: 18px 2%;width: 95%">
      <el-table
        :data="papers"
        stripe
        style="width: 100%"
        :max-height="tableHeight">
        <el-table-column
          type="selection"
          width="55">
        </el-table-column>
        <el-table-column type="expand">
          <template slot-scope="props">
            <el-form label-position="left" inline>
              <el-form-item>
                <span>{{ props.row.paperAbstract }}</span>
              </el-form-item>
            </el-form>
          </template>
        </el-table-column>
        <el-table-column
          prop="paperTitle"
          label="题目（展开查看摘要）"
          fit>
        </el-table-column>
        <el-table-column
          prop="paperDate"
          label="发布日期"
          width="200">
        </el-table-column>
        <el-table-column
          fixed="right"
          label="操作"
          width="180">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="viewPaper(scope.row.id)"
              type="text"
              size="small">
              查看
            </el-button>
            <el-button
              @click.native.prevent="editPaper(scope.row)"
              type="text"
              size="small">
              编辑
            </el-button>
            <el-button
              @click.native.prevent="deletePaper(scope.row.id)"
              type="text"
              size="small">
              移除
            </el-button>
          </template>
        </el-table-column>
      </el-table>
      <div style="margin: 20px 0 50px 0">
        <el-pagination
          background
          style="float:right;"
          layout="total, prev, pager, next, jumper"
          @current-change="handleCurrentChange"
          :page-size="pageSize"
          :total="total">
        </el-pagination>
      </div>
    </el-card>
  </div>
</template>

<script>
export default {
  name: 'PaperEditor',
  data () {
    return {
      papers: [],
      pageSize: 10,
      total: ''
    }
  },
  mounted () {
    this.loadPapers()
  },
  computed: {
    tableHeight () {
      return window.innerHeight - 320
    }
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
    },
    viewPaper (id) {
      let paperUrl = this.$router.resolve(
        {
          path: './paper',
          query: {
            id: id
          }
        }
      )
      window.open(paperUrl.href, '_blank')
    },
    editPaper (paper) {
      this.$router.push(
        {
          name: 'Editor',
          params: {
            paper: paper
          }
        }
      )
    },
    deletePaper (id) {
      this.$confirm('此操作将永久删除该论文, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      }).then(() => {
        this.$axios
          .delete('/paper/' + id).then(resp => {
            if (resp && resp.status === 200) {
              this.loadPapers()
            }
          })
      }
      ).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除'
        })
      })
    }
  }
}
</script>

<style scoped>
  .add-link {
    margin: 18px 0 15px 10px;
    float: left;
  }
</style>
