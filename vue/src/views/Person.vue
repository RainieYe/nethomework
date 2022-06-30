<template>
  <div style="background-color: #dedfe0">
    <el-card style="width: 40%;margin: 90px auto">
      <el-form ref="form" :model="form" border label-width="80px">
        <!--      prop写表头属性 label 表格名字 width自适应-->
        <el-form-item label="用户名">
          <el-input v-model="form.username" disabled></el-input>
        </el-form-item>
        <el-form-item label="姓名">
          <el-input v-model="form.name" disabled></el-input>
        </el-form-item>
        <el-form-item label="密码">
          <el-input v-model="form.password" show-password></el-input>
        </el-form-item>
        <el-form-item label="手机号" >
          <el-input v-model="form.phone"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="form.tip"></el-input>
        </el-form-item>
      </el-form>
      <div style="text-align: center">
        <el-button style="border-radius:20px;background-color: #b7b1a5;text-decoration-color:#cccccc;"
                   @click="update">保存</el-button>
      </div>
    </el-card>
  </div>
</template>

<script>
import request from "../../utils/request";

export default {
  name: "Person",
  data(){
    return{
      form:{}
    }
  },
  created() {
    let str =sessionStorage.getItem("user")||"{}"
    this.form=JSON.parse(str)
  },
  methods:{
    update(){
      request.put("/user",this.form).then(res=>{
        console.log(res)
        if(res.code==='0'){
          this.$message({
            type:"success",
            message:"更新成功"
          })
          sessionStorage.setItem("user",JSON.stringify(this.form))
          this.$router.push("/selfinformation")
        }else{
          this.$message({
            type:"error",
            message:res.msg
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>