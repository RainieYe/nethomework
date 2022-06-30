<template>
  <div style="padding: 10px;background-color: #dedfe0">
    <!--    功能区-->
    <div style="margin: 10px 0;background-color: #dedfe0">
      <el-button style="border-radius:20px;background-color: #b7b1a5" @click="add">新增</el-button>
      <!--      <el-button type="primary">导入</el-button>-->
      <el-button style="border-radius:20px;background-color: #b7b1a5" @click="exportExcel">导出</el-button>
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%;margin-left: 930px" clearable/>
      <el-button style="border-radius:20px;background-color: #b7b1a5;text-decoration-color:#cccccc;margin-left: 5px" @click="load">查询</el-button>
    </div>
    <div style="font: -apple-system-body;font-weight: lighter;">
    </div>
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
      <el-table-column prop="typename"
                       label="房型"/>
      <el-table-column prop="pricetoday"
                       label="单价" />
      <el-table-column prop="area"
                       label="面积" />
      <el-table-column prop="bednum"
                       label="床位数量" />
      <el-table-column  label="操作">
        <template #default="scope" >
          <el-button type="button"  @click="handleEdit(scope.row)"
                     style="margin-left: 20px;margin-bottom: 5px">编辑</el-button>
          <el-popconfirm title="确定删除？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="danger"  style="margin-left: 20px">删除</el-button>
            </template>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <div style="margin: 10px 0">
      <el-pagination
          v-model:currentPage="currentPage"
          :page-sizes="[5, 10,20]"
          :page-size="pageSize"
          layout="total, sizes, prev, pager, next, jumper"
          :total="total"
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange">
      </el-pagination>
      <el-dialog v-model="dialogVisible" title="提示" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="类型代码">
            <el-input v-model="form.type" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="房型">
            <el-input v-model="form.typename" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="单价">
            <el-input v-model="form.pricetoday" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="面积">
            <el-input v-model="form.area" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="床位数量">
            <el-input v-model="form.bednum" style="width: 80%"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
              <el-button @click="dialogVisible = false">取消</el-button>
              <el-button type="primary" @click="save">确认</el-button>
            </span>
        </template>
      </el-dialog>
    </div>
  </div>
</template>

<script>
import request from "../../utils/request";

export default {
  name: "RoomStandard",
  components: {
  },
  data(){
    return{
      form:{},
      search:'',
      currentPage:1,
      pageSize:10,
      dialogVisible:false,
      total:0,
      tableData:[]
    }
  },
  created() {
    this.load()
    //
    let str =sessionStorage.getItem("roomstandard")||"{}"
  },
  methods:{
    load(){
      request.get("/roomStandard",{
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
    save(){
      if(this.form.type){//更新
        request.put("/roomStandard",this.form).then(res=>{
          console.log(res)
          if(res.code ==='0'){
            this.$message({
              type:"success",
              message:"更新成功"
            })
            //
            sessionStorage.setItem("roomstandard",JSON.stringify(this.form))
          }else{
            this.$message({
              type:"error",
              message:res.msg
            })
          }
        })
      }else{//新增
        request.post("/roomStandard",this.form).then(res=>{
          console.log(res)
          if(res.code ==='0'){
            this.$message({
              type:"success",
              message:"新增成功"
            })
          }else{
            this.$message({
              type:"error",
              message:res.msg
            })
          }
        })
      }
      this.dialogVisible=false;
      this.load();
    },
    add(){
      this.dialogVisible=true;
      this.form={};//清空表单对象
    },
    handleDelete(type){
      console.log(type);
      request.delete("/roomStandard/${type}").then(res=>{
        if(res.code ==='0'){
          this.$message({
            type:"success",
            message:"删除成功"
          })
        }else{
          this.$message({
            type:"error",
            message:res.msg
          })
        }
      })
      this.load();//重新加载表格的数据
    },
    handleEdit(row){
      this.form=JSON.parse(JSON.stringify(row));//避免浅拷贝
      this.dialogVisible=true;
    },
    handleSizeChange(pageSize){//改变当前每页的个数触发
      this.pageSize=pageSize;
      this.load();
    },
    handleCurrentChange(pageNum){//改变当前页码触发
      this.currentPage=pageNum;
      this.load();
    },
    exportExcel(){
      // 设置当前日期
      let time = new Date();
      let year = time.getFullYear();
      let month = time.getMonth() + 1;
      let day = time.getDate();
      let name = year + "" + month + "" + day;
      //  .table要导出的是哪一个表格
      var wb = XLSX.utils.table_to_book(document.querySelector(".table"));
      /* get binary string as output */
      var wbout = XLSX.write(wb, {
        bookType: "xlsx",
        bookSST: true,
        type: "array"
      });
      try {
        //  name+'.xlsx'表示导出的excel表格名字
        FileSaver.saveAs(
            new Blob([wbout], { type: "application/octet-stream" }),
            name + ".xlsx"
        );
      } catch (e) {
        if (typeof console !== "undefined") console.log(e, wbout);
      }
      return wbout;
    },
  }
}
</script>

<style scoped>

</style>