<template>
  <div style="background-color: #dedfe0">
    <div>
      <el-card cell-style="{'text-align':'center'}" style="width:36%;margin: 100px auto;
               border-radius:50px;color: darkgrey">
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
        <el-table class=table :data="tableData" border stripe style="width: 100%">
          <el-table-column prop="roomid"
                           label="房间号"/>
          <el-table-column prop="typename"
                           label="房型"/>
          <el-table-column prop="pricetoday"
                           label="客房单价" />
          <el-table-column prop="day"
                           label="天数" />
        </el-table>
      </el-card>
    </div>
  </div>
</template>

<script>
import request from "../../utils/request";

export default {
  name: "selfinformation",
  props:['user'],
  data(){
    return{
      user:{},
      tableData:[],
      total:0,
    }
  },
  created() {
    let userStr =sessionStorage.getItem("user")||"{}"
    this.user=JSON.parse(userStr)
    request.get("/example",{
      params:{
        search:this.user.id
      }

    }).then(res=>{
      console.log(res)
      this.tableData=res.data.records
      this.total=res.data.total
    })
  }
}

</script>

<style scoped>

</style>