<template>
  <div style="background-color: #dedfe0">
    <div>
      <el-card cell-style="{'text-align':'center'}" style="width: 40%;margin: 100px auto;
               border-radius:30px;border-color: gray;color: darkgrey">
        <el-form ref="form" border label-width="80px;" style="font-size: 40px;" align="center" >
          <!--      prop写表头属性 label 表格名字 width自适应-->
          <el-form-item label="用户名" label-width="100px" >
            {{user.username}}
          </el-form-item>
          <el-form-item label="姓名" label-width="100px">
            {{user.name}}
          </el-form-item>
          <el-form-item label="密码" label-width="100px">
            {{user.password}}
          </el-form-item>
          <el-form-item label="手机号" label-width="100px">
            {{user.phone}}
          </el-form-item>
          <el-form-item label="备注" label-width="100px">
            {{user.tip}}
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
export default {
  name: "selfinformation",
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