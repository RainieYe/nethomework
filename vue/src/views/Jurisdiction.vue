<template>
  <div style="padding: 10px">
    <!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%" clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>
    <el-table :data="tableData" border stripe style="width: 100%">
      <!--      prop写表头属性 label 表格名字 width自适应-->
      <el-table-column prop="id"
                       label="ID"
                       sortable/>
      <el-table-column prop="username"
                       label="用户名"/>
      <el-table-column prop="jurisdiction"
                       label="权限" />
      <el-table-column  label="操作">
        <template #default="scope" >
          <el-button type="button"  @click="handleEdit(scope.row)" size="mini"
                     style="margin-left: 20px;margin-bottom: 5px">编辑</el-button>
          <el-popconfirm title="确定删除？" @confirm="handleDelete(scope.row.id)">
            <template #reference>
              <el-button type="danger" size="mini" style="margin-left: 20px">删除</el-button>
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
          <el-form-item label="用户名">
            <el-input v-model="form.username" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="权限">
            <template #default="scope">
              <span v-if="scope.row.jurisdiction ===1">管理员</span>
              <span v-if="scope.row.jurisdiction ===2">普通用户</span>
            </template>
          </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
              <el-button @click="dialogVisible=false">取消</el-button>
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
  name: "Jurisdiction",
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
  },
  methods:{
    load(){
      request.get("/jurisdiction",{
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
      if(this.form.id){//更新
        request.put("/jurisdiction",this.form).then(res=>{
          console.log(res)
          if(res.code ==='0'){
            this.$message({
              type:"success",
              message:"更新成功"
            })
          }else{
            this.$message({
              type:"error",
              message:res.msg
            })
          }
        })
      }else{//新增
        request.post("/jurisdiction",this.form).then(res=>{
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
      this.load();
      this.dialogVisible=false;
    },
    add(){
      this.dialogVisible=true;
      this.form={};//清空表单对象
    },
    handleDelete(id){
      console.log(id);
      request.delete("/jurisdiction/"+id).then(res=>{
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
        this.load();//重新加载表格的数据
      })
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
    }
  }
}
</script>

<style scoped>

</style>