<template>
  <div style="width: 100%;height: 100vh;background-color: darkslategray;overflow: hidden">
    <div style="width: 400px;margin: 150px auto">
      <div style="color: #cccccc;font-size: 30px;text-align: center;padding:30px 0">欢迎登录</div>
      <el-form ref="form" :model="form" size="normal"  :rules="rules">
        <el-form-item  prop="username">
          <div style="flex: 1">
          <el-input v-model="form.username" placeholder="用户名"></el-input>
          </div>
        </el-form-item>
        <el-form-item prop="password">
          <el-input v-model="form.password" placeholder="密码" show-password></el-input>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%" type="primary" @click="login">登 录</el-button>
        </el-form-item>
        <el-form-item>
          <el-button style="width: 100%;color: darkgrey"  @click="register">点 击 注 册</el-button>
        </el-form-item>
      </el-form>
    </div>
  </div>
</template>

<script>
import request from "../../utils/request";

export default {
  name: "Login",
  data(){
    return{
      form:{},
      rules: {
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur',},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur',},
        ],
      }
    }
  },
  methods:{
    login(){
      this.$refs['form'].validate((valid)=>{
        if(valid){
          request.post("/user/login",this.form).then(res=>{
            if(res.code ==='0'){
              this.$message({
                type:"success",
                message:"登录成功"
              })
              //缓存用户信息
              sessionStorage.setItem("user",JSON.stringify(res.data))
              //登陆成功之后进行页面的跳转，跳转到主页面
              this.$router.push("/selfinformation")
            }else{
              this.$message({
                type:"error",
                message:res.msg
              })
            }
          })
        }
      })
    },
    register(){
      this.$router.push("/register")//跳转注册页面
    }
  }
}
</script>

<style scoped>

</style>