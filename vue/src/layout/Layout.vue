<template>
  <div>
    <!--    头部-->
<!--    <Header/>-->
    <div style="color:#FFFFFF;height:100px;line-height: 80px;border-bottom: 1px solid;display: flex">
      <div style="width: 200px;padding-left: 30px;font-size:32px;font-width: bold;background-color: #e0cdcf">后台管理</div>
      <div style="flex: 1;background-color: #e0cdcf"></div>
      <div style="width: 100px;background-color: #e0cdcf">
        <img src="@/assets/ppp.png" style="width: 23px;height: 23px;margin-top: 30px"/>
        <el-dropdown>
        <span class="el-dropdown-link">{{user.username}}
         <el-icon class="el-icon--right">
         </el-icon>
        </span>
          <template  style="text-decoration-color:#FFFFFF;" #dropdown>
            <el-dropdown-menu>
              <el-dropdown-item
                  @click="$router.push('/selfinformation')">个人信息</el-dropdown-item>
              <el-dropdown-item @click="$router.push('/login')">退出系统</el-dropdown-item>
            </el-dropdown-menu>
          </template>
        </el-dropdown>
      </div>
    </div>
    <!--    主体-->
    <div style="display: flex;">
      <!--      侧边栏-->
      <el-menu
          class="el-menu-vertical-demo"
          default-active="1"
          router
          style="width:200px;min-height: calc(100vh - 10px);background-color:#c1cbd7">
        <el-sub-menu index="1">
<!--          v-if="user.jurisdiction === '1'"-->
          <template #title>系统管理</template>
          <el-menu-item index="/roomstandard">客房标准信息管理</el-menu-item>
          <el-menu-item index="/room">订房信息管理</el-menu-item>
<!--          <el-menu-item index="/user">顾客信息管理</el-menu-item>-->
        </el-sub-menu>
        <el-sub-menu index="2" >
          <template #title>顾客个人信息管理</template>
          <el-menu-item index="/selfinformation">个人信息</el-menu-item>
          <el-menu-item index="/person">修改个人信息</el-menu-item>
        </el-sub-menu>
      </el-menu>
      <!--  内容区域-->
      <router-view style="flex: 1"/>
    </div>
  </div>
</template>

<script>

export default{
  name:"Layout",
  props:['user'],
  data(){
    return{
      user:{}
    }
  },
  created() {
    let userStr =sessionStorage.getItem("user")||"{}"
    this.user=JSON.parse(userStr)
  }
}
</script>

<style scoped>

</style>