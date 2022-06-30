<template>
  <div style="width: 100%;height: 100vh;background-color: darkslategray;overflow: hidden;background-color: lightsteelblue">
    <el-card style="width: 500px;margin: 150px auto;background-color:#eeeeee">
      <el-row>
        <el-col style="width: 400px">
          <div style="color: #cccccc;font-size: 30px;padding:30px 0">欢迎登录</div>
        </el-col>
        <el-col>
          <el-form ref="form" :model="form" size="normal"  :rules="rules">
            <el-form-item  prop="username">
              <div style="flex: 1">
                <el-input style="border-radius: 20px;width:50%"
                          v-model="form.username" placeholder="用户名"></el-input>
              </div>
            </el-form-item>
            <el-form-item prop="password">
              <el-input  style="border-radius: 20px;width:50%"
                         v-model="form.password" placeholder="密码" show-password></el-input>
            </el-form-item>
            <el-form-item>
              <el-button style="width: 50% ;background-color:#ddddee" @click="login">登 录</el-button>
            </el-form-item>
            <el-form-item>
              <el-button style="width: 50%;color: darkgrey"  @click="register">点 击 注 册</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>
    </el-card>
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