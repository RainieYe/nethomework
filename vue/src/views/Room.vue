<template>
  <div style="padding: 10px;background-color: #dedfe0">
    <!--    功能区-->
    <div style="margin: 10px 0;background-color: #dedfe0">
      <el-button style="border-radius:20px;background-color: #b7b1a5" @click="flagmethod(),add()">新增房间</el-button>
<!--      <el-button style="border-radius:20px;background-color: #b7b1a5" @click="exportExcel">导出</el-button>-->
      <el-input v-model="delroomid" placeholder="需删除的房间号" style="width: 10%;margin-left: 700px"/>
      <el-button style="border-radius:20px;background-color: #b7b1a5" @click="deleteroom()">删除房间</el-button>
      <!--      <el-button type="primary">导入</el-button>-->

      <el-input v-model="search" placeholder="请输入关键字" style="width: 20%;margin-left: 30px" clearable/>
      <el-button style="border-radius:20px;background-color: #b7b1a5;text-decoration-color:#cccccc;margin-left: 5px" @click="load">查询</el-button>
    </div>
    <div style="font: -apple-system-body;font-weight: lighter;background-color: #dedfe0">
    </div>
    <el-table class=table :data="tableData" border stripe style="width: 100%">
      <!--      prop写表头属性 label 表格名字 width自适应-->
      <el-table-column prop="roomid"
                       label="房间号"/>
      <el-table-column prop="typename"
                       label="房型"/>
      <el-table-column prop="pricetoday"
                       label="客房单价" />
      <el-table-column prop="id"
                       label="身份证号" />
      <el-table-column prop="username"
                       label="用户账号" />
      <el-table-column prop="name"
                       label="顾客姓名" />
<!--      <el-table-column prop="identifier"-->
<!--                       label="身份证号"/>-->
      <el-table-column prop="phone"
                       label="手机号" />
      <el-table-column prop="day"
                       label="入住时间" />
      <el-table-column prop="tip"
                       label="备注" />
      <el-table-column  label="操作">
        <template #default="scope" >
          <el-button type="button"  @click="handleEdit(scope.row)"
                     style="margin-left: 20px;margin-bottom: 5px">入住</el-button>
          <el-button type="button" style="margin-left: 20px;margin-bottom: 5px;background-color:#003300"
                     @click="account(scope.row)">结账</el-button>
          <el-popconfirm title="确定删除？" @confirm="handleDelete(scope.row)">
          <template #reference>
            <el-button type="danger"  style="margin-left: 20px">退房</el-button>
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
      <el-dialog v-model="dialogVisible" title="添加房间" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="房间号">
            <el-input v-model="form.roomid" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="房型">
            <el-input v-model="form.roomtype" style="width: 80%"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
              <el-button @click="dialogVisible = false">取消</el-button>
              <el-button type="primary" @click="save">确认</el-button>
            </span>
        </template>
      </el-dialog>
      <el-dialog v-model="dialogVisible1" title="办理入住" width="30%">
        <el-form :model="form" label-width="120px">
          <el-form-item label="入住时间">
            <el-input v-model="form.day" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="身份证号">
            <el-input v-model="form.userid" style="width: 80%"></el-input>
          </el-form-item>
        </el-form>
        <el-form :model="form2" label-width="120px">
          <el-form-item label="顾客账号">
            <el-input v-model="form2.username" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="顾客姓名">
            <el-input v-model="form2.name" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="手机号">
            <el-input v-model="form2.phone" style="width: 80%"></el-input>
          </el-form-item>
          <el-form-item label="备注">
            <el-input type="textarea" v-model="form2.tip" style="width: 80%"></el-input>
          </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
              <el-button @click="dialogVisible1 = false">取消</el-button>
              <el-button type="primary" @click="flagmethod,save2()">确认</el-button>
            </span>
        </template>
      </el-dialog>

      <el-dialog v-model="dialogVisible2" title="结账" width="30%">
        <el-form label-width="120px">
          <el-form-item label="居住天数">
              <span>{{this.day}}</span>
          </el-form-item>
          <el-form-item label="房间单价">
            <span>{{this.price}}</span>
          </el-form-item>
          <el-form-item label="总价格">
            <span>{{this.money}}</span>
          </el-form-item>
        </el-form>
        <template #footer>
            <span class="dialog-footer">
              <el-button @click="dialogVisible2=false">取消</el-button>
              <el-button type="primary" @click="save3()">确认</el-button>
            </span>
        </template>
      </el-dialog>

    </div>
  </div>
</template>

<script>
import request from "../../utils/request";

export default {
  name: "Room",
  components: {
  },
  data(){
    return{
      form:{},
      form1:{},
      form2:{},
      flag:0,
      flag1:0,
      search:'',
      day:0,
      price:0,
      money:0,
      delroomid:null,
      currentPage:1,
      pageSize:10,
      dialogVisible:false,
      dialogVisible1:false,
      dialogVisible2:false,
      total:0,
      tableData:[],
    }
  },
  created() {
    this.load()
  },
  methods:{
    flagmethod(){
      this.flag=1;
    },

    deleteroom(){
      console.log(this.delroomid);
      request.delete("/room/"+this.delroomid).then(res=>{
        if(res.code ==='0'){
          this.$message({
            type:"success",
            message:"删除房间成功"
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
    load(){
      this.flag = 0;
      request.get("/example",{
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
        request.put("/room",this.form).then(res=>{
          console.log(res)
          if(res.code ==='0'){
            this.$message({
              type:"success",
              message:"room更新成功"
            })
          }else{
            this.$message({
              type:"error",
              message:res.msg
            })
          }
        })
      }else{//新增
        // this.form2.id=this.form.userid;
        request.post("/room",this.form).then(res=>{
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
    save1(){
      this.form2.id=this.form.userid;
      request.post("/user",this.form2).then(res=> {
        console.log(res)
        if (res.code === '0') {
          this.$message({
            type: "success",
            message: "user更新成功"
          })
        } else {
          this.$message({
            type: "error",
            message: res.msg
          })
        }
      })
      this.load();
      this.dialogVisible1=false;
    },
    save2(){
      this.save1()
      this.save()
    },
    add(){
      this.dialogVisible=true;
      this.form={};//清空表单对象
    },
    account(row){
      this.price=row.pricetoday;
      this.day=row.day;

      this.money=row.pricetoday*row.day;
      this.dialogVisible2=true;
    },
    save3(){
      this.dialogVisible2=false;
    },
    handleDelete(row){
      let roomid=row.roomid;
      console.log(roomid);
      request.get("/room/croom/"+roomid).then(res=>{
        if(res.code ==='0'){
          this.$message({
            type:"success",
            message:"退房成功"
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
      this.dialogVisible1=true;
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