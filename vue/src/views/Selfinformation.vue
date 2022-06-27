<template>
  <div>
    <div>
      <el-card style="width: 40%;margin: 20px auto;border-color: darkgray;color: darkgrey">
        <el-form ref="form" border label-width="80px">
          <!--      prop写表头属性 label 表格名字 width自适应-->
          <el-form-item label="用户名">
            {{user.username}}
          </el-form-item>
          <el-form-item label="密码">
            {{user.password}}
          </el-form-item>
          <el-form-item label="年龄" >
            {{user.age}}
          </el-form-item>
          <el-form-item label="性别" >
            {{user.sex}}
          </el-form-item>
          <el-form-item label="地址" >
            {{user.address}}
          </el-form-item>
          <el-form-item label="部门">
            {{user.department}}
          </el-form-item>
          <el-form-item label="职务">
            {{user.post}}
          </el-form-item>
          <el-form-item label="擅长">
            {{user.excel}}
          </el-form-item>
          <el-form-item label="门诊时间">
            {{user.outpatienttime}}
          </el-form-item>
          <el-form-item label="职称" >
            {{user.professionaltitles}}
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