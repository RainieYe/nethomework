<template>
  <div>
    <!--        router-->
    <el-menu
        class="el-menu-vertical-demo"
        default-active="2"

        style="width: 180px;min-height: calc(100vh - 10px)">
        <el-sub-menu index="1" >
          <template #title>系统管理</template>
          <el-menu-item index="/user">用户管理</el-menu-item>
         <el-menu-item index="/jurisdiction">权限管理</el-menu-item>
        </el-sub-menu>
        <el-sub-menu index="2" >
          <template #title>个人信息管理</template>
            <el-menu-item index="/selfinformation">个人信息</el-menu-item>
            <el-menu-item index="/person">修改个人信息</el-menu-item>
        </el-sub-menu>
    </el-menu>
  </div>
</template>

<script>
import request from "../../utils/request";

export default {
  name: "Aside",
  // data(){
  //   return{
  //     user:{},
  //   }
  // },
  // created() {
  //   let userStr=sessionStorage.getItem("user")||"{}"
  //   this.user=JSON.stringify(userStr)
  //   // //请求服务端，确定当前用户的合法信息
  //   // request.get("/user"+this.user.id).then(res=>{
  //   //   if(res.code==='0'){
  //   //     this.users=res.data
  //   //   }
  //   // })
  // }
}
</script>

<style scoped>

</style>