<template>
  <div style="width: 100%;height: 100vh;background-color:#b7b1a5;overflow: hidden">
      <el-card style="width: 600px;margin: 150px auto;background-color:#eeeeee;flex-direction: row">
          <el-row>
          <img src="@/assets/hotel.png" style="margin-left:35px;margin-top:90px;width: 150px;height: 150px"/>
            <div style="margin-left: 70px">
              <div style="color: #cccccc;font-size: 30px;padding:20px 80px">欢迎注册</div>
              <el-form ref="form" :model="form" size="normal" :rules="rules">
                <el-form-item prop="name">
                  <div style="flex: 1">
                    <el-input v-model="form.name" placeholder="姓名"></el-input>
                  </div>
                </el-form-item>
                <el-form-item prop="id">
                  <div style="flex: 1">
                    <el-input v-model="form.id" placeholder="身份证"></el-input>
                  </div>
                </el-form-item>
                <el-form-item prop="username">
                  <div style="flex: 1">
                    <el-input v-model="form.username" placeholder="用户名"></el-input>
                  </div>
                </el-form-item>
                <el-form-item prop="phone">
                  <div style="flex: 1">
                    <el-input v-model="form.phone" placeholder="手机号"></el-input>
                  </div>
                </el-form-item>
                <el-form-item prop="password">
                  <el-input style="border-radius: 20px;width:100%"
                            v-model="form.password" placeholder="密码" show-password></el-input>
                </el-form-item>
                <el-form-item prop="confirm">
                  <el-input style="border-radius: 20px;width:100%"
                            v-model="form.confirm" placeholder="确认密码" show-password></el-input>
                </el-form-item>
                <el-form-item>
                  <el-button style="width: 100%;background-color:#989898" @click="register">注 册</el-button>
                </el-form-item>
              </el-form>
            </div>
          </el-row>
      </el-card>
    </div>
</template>

<script>
import request from "../../utils/request";

export default {
  name: "Register",
  data() {
    return {
      form: {},
      rules: {
        name: [
          {required: true, message: '请输入姓名', trigger: 'blur',},
        ],
        id: [
          {required: true, message: '请输入身份证', trigger: 'blur',},
        ],
        username: [
          {required: true, message: '请输入用户名', trigger: 'blur',},
        ],
        phone: [
          {required: true, message: '请输入手机号', trigger: 'blur',},
        ],
        password: [
          {required: true, message: '请输入密码', trigger: 'blur',},
        ],
        confirm: [
          {required: true, message: '请确认密码', trigger: 'blur',},
        ],
      }
    }
  },
  methods:{
    register(){
      if(this.form.password!==this.form.confirm){
        this.$message({
          type:"error",
          message:'2次密码输入不一致'
        })
        return
      }
      this.$refs['form'].validate((valid)=>{
        if(valid){
          request.post("/user/register",this.form).then(res=>{
            if(res.code ==='0'){
              this.$message({
                type:"success",
                message:"注册成功"
              })
              this.$router.push("/login")//登陆成功之后进行页面的跳转，跳转到主而言
            }else{
              this.$message({
                type:"error",
                message:res.msg
              })
            }
          })
        }
      })
    }
  }
}
</script>

<style scoped>

</style>