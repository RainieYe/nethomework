<template>
  <el-table class=table :data="tableData" border stripe style="width: 100%">
    <!--      prop写表头属性 label 表格名字 width自适应-->
    <el-table-column
        label="图片" #default="scope"
        width="180">
      <el-card>
        <img   v-if="scope.row.type==1" src="@/assets/img1.png" style="width: 100px;height: 70px;margin-left: 7px">
        <img   v-if="scope.row.type==2" src="@/assets/img3.png" style="width: 100px;height: 70px;margin-left: 7px">
        <img   v-if="scope.row.type==3" src="@/assets/img2.png" style="width: 100px;height: 70px;margin-left: 7px">
        <img   v-if="scope.row.type==4" src="@/assets/img4.png" style="width: 100px;height: 70px;margin-left: 7px">
        <img   v-if="scope.row.type==5" src="@/assets/img5.png" style="width: 100px;height: 70px;margin-left: 7px">
        <img   v-if="scope.row.type==6" src="@/assets/img6.png" style="width: 100px;height: 70px;margin-left: 7px">
        <img   v-if="scope.row.type==7" src="@/assets/img7.png" style="width: 100px;height: 70px;margin-left: 7px">
        <img   v-if="scope.row.type>7" src="@/assets/img8.png" style="width: 100px;height: 70px;margin-left: 7px">
      </el-card>
    </el-table-column>
    <el-table-column v-if="false" prop="type"
                     label="房型号"/>
    <el-table-column prop="typename"
                     label="房型"/>
    <el-table-column prop="pricetoday"
                     label="单价" />
    <el-table-column prop="area"
                     label="面积" />
    <el-table-column prop="bednum"
                     label="床位数量" />
  </el-table>
</template>

<script>
import request from "../../utils/request";

export default {
  name: "RoomSee",
  components: {
  },
  data(){
    return{
      form:{},
      currentPage:1,
      pageSize:10,
      dialogVisible:false,
      total:0,
      tableData:[]
    }
  },
  created() {
    this.load()
  },
  methods:{
    load(){
      request.get("/roomstandard",{
        params:{
          pageNum:this.currentPage,
          pageSize:this.pageSize,
          search:this.search
        }
      }).then(res=>{
        console.log(res)
        this.tableData=res.data.records
        this.total=res.data.total
      })
    },
  }
}
</script>
<style scoped>

</style>