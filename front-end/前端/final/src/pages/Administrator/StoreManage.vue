<template>
  <div id="StoreManage">
    <el-container>
      <el-main>

        <el-col :span="10" >
          <div class="grid-content bg-purple">
            <el-input @input="valueChanged" v-model="queriedSId" placeholder="请输入您想要查询的仓库号" ></el-input>
          </div>
        </el-col>
        <el-col :span="2" offset="1">
          <el-button type="primary" icon="el-icon-search" @click="queryBysId">搜索</el-button>
        </el-col>
        <el-col :span="2" offset="2">
          <el-button type="primary" icon="el-icon-edit" @click="createStoreVisible=true">添加仓库信息</el-button>
        </el-col>

        <!--新建仓库-->
        <el-dialog
          :visible.sync="createStoreVisible"
          width="50%"
          :before-close="handleClose">
          <el-form sid="#index":label-position="labelPosition" label-width="80px" :model="create">
            <!--<el-form-item label="仓库ID">-->
              <!--<el-input v-model="create.sId"></el-input>-->
            <!--</el-form-item>-->
            <el-form-item label="仓库地址">
              <el-input v-model="create.sAddress"></el-input>
            </el-form-item>
            <el-form-item label="库存">
              <el-input v-model="create.sNumber"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
             <el-button @click="createStoreVisible = false">取 消</el-button>
              <el-button type="primary" @click="createStore">提交</el-button>
             </span>
        </el-dialog>
        <!--<el-col :span="6" offset="1">-->
          <!--<el-button type="primary">显示全部仓库信息</el-button>-->
          <!--<el-button type="primary" icon="el-icon-delete">删除仓库信息</el-button>-->
        <!--</el-col>-->


        <!--修改仓库信息-->
        <el-dialog
          title="修改仓库信息"
          :visible.sync="dialogUpdateVisible"
          :close-on-click-modal="false"
          :close-on-press-escape="false">
          <el-form id="#update" :model="update" ref="update" label-width="100px">
            <el-form-item label="仓库编号" prop="sId">
              <el-input :disabled="true" v-model="update.sId"></el-input>
            </el-form-item>
            <el-form-item label="仓库地址" prop="sAddress">
              <el-input v-model="update.sAddress"></el-input>
            </el-form-item>
            <el-form-item label="库存" prop="sNumber">
              <el-input v-model="update.sNumber"></el-input>
            </el-form-item>

          </el-form>
          <!--按钮-->
          <div slot="footer">
            <el-button @click="dialogUpdateVisible = false">取 消</el-button>
            <el-button type="primary" @click="updateStore">确 定</el-button>
          </div>
        </el-dialog>


        <el-table
          :data="storeData"
          style="width: 100%">
          <!--height="250">-->
          <el-table-column
            fixed
            prop="sId"
            label="仓库ID">
            <!--width="200">-->
          </el-table-column>
          <el-table-column
            prop="sAddress"
            label="仓库地址">
            <!--width="600">-->
          </el-table-column>
          <el-table-column
            prop="sNumber"
            label="库存">
            <!--width="200">-->
          </el-table-column>
          <!--<el-table-column-->
            <!--label="操作">-->
            <!--&lt;!&ndash;width="100">&ndash;&gt;-->
            <!--<el-button type="success" @click="dialogUpdateVisible=true">编辑</el-button>-->
            <!--<el-button type="danger">删除</el-button>-->
          <!--</el-table-column>-->

          <!--操作：编辑，删除-->
          <el-table-column label="操作" width="220" align="center">
            <template slot-scope="scope">
              <el-button type="success" size="mini" icon="el-icon-setting" @click="setCurrent(scope.row)">编辑</el-button>
              <el-button type="danger" size="small" icon="el-icon-delete" @click="removed(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </div>
</template>

<script>
    export default {
        name: "StoreManage",
        inject:["reload"],
      data() {
        return {
          storeData:[],
          queriedSId: null,
          createStoreVisible: false,
          dialogUpdateVisible: false,
          labelPosition: 'right',
          create: {
            sId: null,
            sAddress: null,
            sNumber: null,
          },
          update:{
            sId: null,
            sAddress: null,
            sNumber: null,
          },

        }
      },
      mounted() {
        console.log("loading data.");
        this.$ajax({
          method: "get",
          url: "http://localhost:8080/Store/findAll"
        }).then(response => {
          console.log("123123123213")
          console.log(response.data);
          //for (let i = 0; i < response.data.length; i++) {
          //this.userData=push(response.data[i]);
          this.storeData=response.data;
          //}
        })
      },
      methods:{
        alterQuerySuccess() {
          this.$message({
            message: "恭喜你，查询成功",
            type: "true"
          });
        },
        alterQueryError()
        {
          this.$message({
            message: "抱歉，查询失败",
            type: "error"
          });
          //this.reload();
        },
        alterDeleteSuccess() {
          this.$message({
            message: "恭喜你，删除成功",
            type: "success"
          });
          this.reload();
        },
        alterDeleteError() {
          this.$message({
            message: "抱歉，删除失败",
            type: "error"
          })
        },
        alterCreateSuccess() {
          this.$message({
            message: "恭喜你，添加成功",
            type: "success"
          });
          this.reload();
        },
        alterCreateError() {
          this.$message({
            message: "抱歉，添加失败",
            type: "error"
          });
          this.reload();
        },
        alterUpdateSuccess() {
          this.$message({
            message: "恭喜你，修改成功",
            type: "success"
          });
          this.reload();
        },
        alterUpdateError() {
          this.$message({
            message: "抱歉，修改失败",
            type: "error"
          });
          this.reload();
        },





        // 当查询框被删减为空时自动查询所有数据
        valueChanged() {
          if (this.queriedSId === "") {
            console.log("当前为空字符串");
            this.queryBysId();
          }
        },

        //添加仓库
        createStore(){
          let data = this.create;
          console.log(data);
          this.$ajax.post("http://localhost:8080/Store/CreateStore/", JSON.stringify(data),
            {
              headers: { 'Content-Type': 'application/json;charset=UTF-8' }
            }
          ).then(response => {
            console.log(response);
            this.dialogCreateVisible = false;
            if (response.data != null) {
              this.alterCreateSuccess();
            } else {
              this.alterCreateError();
            }
          }).catch(() => {
            console.log("save error");
            this.alterCreateError();
          });
        },
        //编辑仓库数据
        setCurrent(currentInfo){
          console.log(currentInfo);
          this.update.sId = currentInfo.sId;
          this.update.sAddress = currentInfo.sAddress;
          this.update.sNumber = currentInfo.sNumber;
          this.dialogUpdateVisible = true;
        },



        updateStore() {
          let data = this.update;
          console.log(data);
          this.$ajax.get("http://localhost:8080/Store/UpdateS/sAddress="+data.sAddress+"sNumber="+data.sNumber+"sId="+data.sId).then(response => {
            console.log("现在都四点了，而我还没写完")
            console.log(response);
            this.dialogUpdateVisible = false;
            if (response.data!= null) {
              this.alterUpdateSuccess();
            } else {
              this.alterUpdateError();
            }
          }).catch(() => {
            console.log("save error");
            this.alterUpdateError();
          });
        },





        //根据仓库号查询
        queryBysId()
        {
          console.log(this.queriedSId);
          if (this.queriedSId=='')
          {
            console.log("空字符");
            this.reload();
            return;
          }
          this.$ajax.get("http://localhost:8080/Store/findBySId/sId="+this.queriedSId).then(response => {
            console.log(response);
            if(response.data != null)
            {
              console.log("success")
              console.log(response.data);
              console.log("success");
              this.storeData=[];
              console.log("storeDate")
              console.log(this.storeData)
              this.storeData.push(response.data);
              // this.userData=response.data;
              console.log(this.storeData);
              this.alterQuerySuccess();
            }else{
              console.log(response.data)
              console.log("failed!")
              this.alterQueryError()
            }
          }).catch((err) => {
            //抛出异常，关闭数据库
            console.log("query error!" + err);
            this.alterQueryError();
          })
        },

        //删除这个仓库
        removed(currentInfo) {
          console.log("删除员工");
          this.$confirm(
            "此操作将永久删除该仓库" + currentInfo.sId + "，是否继续？", "提示",
            {
              type: "warning"
            }
          ).then(() => {
            console.log("确认删除");
            // 向服务器请求删除
            let sId = currentInfo.sId;
            console.log(sId);
            this.$ajax.get('http://localhost:8080/Store/DeleteStore/sId=' + sId).then(response => {
              console.log(response);
              if ( response.data != null) {
                this.alterDeleteSuccess();

              }
              else {
                this.alterDeleteError();
              }
            }).catch(() => {
              console.log("delete error!");
              this.alterDeleteError();
            });
          }).catch(() => {
            this.$message.info("已取消操作！");
          });
        },

      }
    }
</script>

<!--<style scoped>-->
  <!--.el-table__header tr,-->
  <!--.el-table__header th {-->
    <!--padding: 0;-->
    <!--height: 40px;-->
  <!--}-->
<!--</style>-->
<style scoped>
  h1,
  h2 {
    font-weight: normal;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    margin: 0 10px;
  }
  a {
    color: #42b983;
  }

  .orderTitle {
    border-bottom: 2px #409eff solid;
    font-size: 24px;
    padding-bottom: 10px;
  }
</style>
