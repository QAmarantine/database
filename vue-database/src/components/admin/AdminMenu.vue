<template>
  <div>
    <el-menu
      class="el-menu-admin"
      router
      mode="vertical"
      text-color="#fff"
      active-text-color="#ffd04b">
      <div style="height: 100px;"></div>
    </el-menu>
    <template>
        <el-row class="tac">
    <el-col :span="12">
      <el-menu
        default-active="2"
        class="el-menu-vertical-demo"
        @open="handleOpen"
        @close="handleClose"
      >
        <el-menu-item v-for="(item,i) in navList" :key="i" :index="item.name">
        {{ item.navItem }}
      </el-menu-item>
      </el-menu>
    </el-col>
  </el-row>
</template>
  </div>
</template>
<script>

export default {
  name: 'AdminMenu',
  data () {
    return {
      navList: [
        {name: '/information', navItem: '用户信息'},
        {name: '/Person_paper', navItem: '个人论文'}
      ]
    }
  }
}
</script>
