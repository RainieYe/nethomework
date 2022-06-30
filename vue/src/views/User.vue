<template>
  <div style="padding: 10px">
    <!--    功能区-->
    <div style="margin: 10px 0">
      <el-button type="primary" @click="flagmethod(), add()">新增</el-button>
<!--      <el-button type="primary">导入</el-button>-->
<!--      <el-button type="primary" @click="exportExcel">导出</el-button>-->
    </div>
    <!--    搜索区域-->
    <div style="margin: 10px 0">
      <el-input v-model="search" placeholder="请输入id、姓名、或用户名" style="width: 20%" clearable/>
      <el-button type="primary" style="margin-left: 5px" @click="load">查询</el-button>
    </div>
    <div style="font: -apple-system-body;font-weight: lighter">
    说明：权限值1表示管理员，2表示普通用户
    </div>
    <el-table class=table :data="tableData" border stripe style="width: 100%">
      <!--      prop写表头属性 label 表格名字 width自适应-->
      <el-table-column prop="id"
                       label="ID"/>
      <el-table-column prop="username"
                       label="用户名"/>
      <el-table-column prop="password"
                       label="密码" />
      <el-table-column prop="name"
                       label="姓名" />
      <el-table-column prop="jurisdiction"
                       label="权限" />
      <el-table-column prop="tip"
                       label="备注" />
      <el-table-column  label="操作">
        <template #default="scope" >
          <el-button type="button"  @click="showmethod(), handleEdit(scope.row)"
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
      <el-dialog v-model="dialogVisible" title="用户操作" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="用户名">
            <el-input v-model="form.username" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="密码">
            <el-input v-model="form.password" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item v-if="showClass" label="id">
            <el-input v-model="form.id" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="姓名">
            <el-input v-model="form.name" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="权限" >
            <template #default="scope">
              <el-radio v-model="form.jurisdiction" label="1">管理员</el-radio>
              <el-radio v-model="form.jurisdiction" label="2">消费者</el-radio>
            </template>
          </el-form-item>
          <el-form-item label="备注">
            <el-input v-model="form.tip" style="width: 80%"></el-input>
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
import {exportMethod} from "../../utils/util";

import FileSaver from "file-saver";
import xlsx from "xlsx";


export default {
  name: 'Home',
  components: {
  },
  data(){
    return{
      show:true,
      flag:0,
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
    showmethod(){
      this.show = false;
    },
    flagmethod(){
      this.flag=1;
    },
    load(){
      this.flag = 0;
      this.show = true;
      request.get("/user",{
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
      if(this.flag===0){//更新
        request.put("/user",this.form).then(res=>{
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
            this.load();
          }
        })
      }else{//新增
        request.post("/user",this.form).then(res=>{
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
            this.load();
          }
        })
      }
      this.dialogVisible=false;
    },
    add(){
      this.dialogVisible=true;
      this.form={};//清空表单对象
    },
    handleDelete(id){
      console.log(id);
      request.delete("/user/"+id).then(res=>{
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
    },
    exportExcel(){
      // 设置当前日期
      let time = new Date();
      let year = time.getFullYear();
      let month = time.getMonth() + 1;
      let day = time.getDate();
      let name = year + "" + month + "" + day;
      // console.log(name)
      /* generate workbook object from table */
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
