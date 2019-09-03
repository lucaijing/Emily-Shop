<template>
    <div id="UserManage">
      <el-container>
        <el-main>
          <el-col :span="12" >
            <div class="grid-content bg-purple">
              <el-input @input="valueChanged" v-model="queriedCId" placeholder="请输入您想要查询的用户ID" ></el-input>
            </div>
          </el-col>
          <el-col :span="2" offset="1">
            <el-button type="primary" icon="el-icon-search" @click="queryBycId">搜索</el-button>
          </el-col>
          <!--<el-col :span="2" offset="1">-->
            <!--<el-button type="primary" icon="el-icon-delete">显示全部用户信息</el-button>-->
          <!--</el-col>-->

          <el-table
            :data="userData"
            style="width: 100%">
            <el-table-column
              fixed
              prop="cId"
              label="用户ID">
            </el-table-column>
            <el-table-column
              prop="cName"
              label="用户姓名">
            </el-table-column>
            <el-table-column
              prop="cPhone"
              label="联系电话">
            </el-table-column>
            <el-table-column
              prop="cAddress"
              label="联系地址">
            </el-table-column>
          </el-table>

        </el-main>
      </el-container>
    </div>
</template>

<script>
    export default {
        name: "UserManage",
      inject:["reload"],
      data(){
        return{
          userData:[],
          queriedCId: null,


    }
    },

      mounted() {
        console.log("loading data.");
        this.$ajax({
          method: "get",
          url: "http://localhost:8080/Customer/findAll"
        }).then(response => {
          console.log("123123123213")
          console.log(response.data);
          //for (let i = 0; i < response.data.length; i++) {
            //this.userData=push(response.data[i]);
            this.userData=response.data;
          //}
        })
      },
      methods:{
        alterQuerySuccess() {
          this.$message({
            message: "恭喜你，查询成功",
            type: "success"
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

        // 当查询框被删减为空时自动查询所有数据
        valueChanged() {
          if (this.queriedCId === "") {
            console.log("当前为空字符串");
            this.queryBycId();
          }
        },

          //根据顾客号查询
        queryBycId()
        {
          console.log(this.queriedCId);
          if (this.queriedCId=='')
          {
            console.log("空字符");
            this.reload();
            return;
          }
          this.$ajax.get("http://localhost:8080/Customer/findByCId/cId="+this.queriedCId).then(response => {
            console.log(response);
            if(response.data != null)
            {
              console.log("success")
              console.log(response.data);
              console.log("success");
              this.userData=[];
              console.log("userDate")
              console.log(this.userData)
              this.userData.push(response.data);
              // this.userData=response.data;
              console.log(this.userData);
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

          }


    }
</script>

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
