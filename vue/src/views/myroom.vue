<template>
  <div style="background-color: #dedfe0">
    <div>
      <el-card cell-style="{'text-align':'center'}" style="width:36%;margin: 100px auto;
               border-radius:50px;color: darkgrey">
        <el-form ref="form" border label-width="80px;" style="font-size: 40px;" align="center" >
          <!--      prop写表头属性 label 表格名字 width自适应-->
          <el-form-item label="房间号" label-width="100px" >
            {{room.roomid}}
          </el-form-item>
          <el-form-item label="房型" label-width="100px">
            {{room.type}}
          </el-form-item>
          <el-form-item label="面积" label-width="100px">
            {{room.area}}
          </el-form-item>
        </el-form>
      </el-card>
    </div>
  </div>
</template>

<script>
import request from "../../utils/request";

export default {
  name: "myroom",
  props:['room'],
  data() {
    return {

      room: {}
    }
  },
  created() {
    request.get("/example",{
      params:{

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