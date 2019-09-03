<template>
  <el-container>
    <el-main>
      <h2>
      <el-row>
      <el-col :span="6" >
        <div class="grid-content bg-purple">
          <el-input @input="valueChanged" v-model="queriedOId" placeholder="请输入您想要查询的订单号" ></el-input>
        </div>
      </el-col>
      <el-col :span="2" offset="1">
        <el-button type="primary" icon="el-icon-search" @click="queryByoId">搜索</el-button>
      </el-col>
      <el-col :span="2" offset="1">
        <el-button type="primary" >显示全部订单</el-button>
      </el-col>
      </el-row>
      </h2>

      <h1 align="left">正在进行的订单</h1>
      <!--正在进行的订单-->
      <el-table
        :data="orderData"
        style="width: 100%">
        <el-table-column
          fixed
          prop="oId"
          label="订单号">
        </el-table-column>
        <el-table-column
          prop="proId"
          label="商品号">
        </el-table-column>
        <el-table-column
          prop="oNumber"
          label="数量">
        </el-table-column>
        <el-table-column
          prop="cId"
          label="用户号">
        </el-table-column>
        <el-table-column
          prop="oSum"
          label="总金额">
        </el-table-column>
        <el-table-column
          prop="cAddress"
          label="用户地址">
        </el-table-column>
        <el-table-column
          label="状态">
          <el-button type="primary" @click="finished">完成</el-button>
        </el-table-column>
      </el-table>

      <h1 align="left" >已完成的订单</h1>
      <!--已完成的订单-->
      <el-table
        :data="finishedData"
        style="width: 100%">
        <el-table-column
          fixed
          prop="oId"
          label="订单号">
        </el-table-column>
        <el-table-column
          prop="proId"
          label="商品号">
        </el-table-column>
        <el-table-column
          prop="oNumber"
          label="数量">
        </el-table-column>
        <el-table-column
          prop="cId"
          label="用户号">
        </el-table-column>
        <el-table-column
          prop="oSum"
          label="总金额">
        </el-table-column>
        <el-table-column
          prop="cAddress"
          label="用户地址">
        </el-table-column>

      </el-table>

    </el-main>
  </el-container>
</template>

<script>
    export default {
        name: "OrdersManage",
      inject:["reload"],
      data() {
        return {
          orderData:[],
          finishedData:[],
          queriedOId:null,
          createOrdersVisible:false,
          labelPosition: 'right',
          soId:null,//商品对应的仓库号
          soNumber:null,//store列表里的snumber
          boNumber:null,//product列表里的bnumber
          createOrders: {
            oId:null,
            proId:null,
            cId:null,
            oSum:null,
            oAddress:null,
            oNumber:null,


          }
        }

      },
      mounted() {
        console.log("loading data.");
        this.$ajax({
          method: "get",
          url: "http://localhost:8080/Orders/findByOIsdone/oIsdone=0"
        }).then(response => {
          console.log("orderData")
          console.log(response.data);
          //for (let i = 0; i < response.data.length; i++) {
          //this.userData=push(response.data[i]);
          this.orderData=response.data;
          //}
        })

        this.$ajax({
          method: "get",
          url: "http://localhost:8080/Orders/findByOIsdone/oIsdone=1"
        }).then(response => {
          console.log("finishedData")
          console.log(response.data);
          //for (let i = 0; i < response.data.length; i++) {
          //this.userData=push(response.data[i]);
          this.finishedData=response.data;
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
        alterQueryError() {
          this.$message({
            message: "抱歉，查询失败",
            type: "error"
          });
          //this.reload();
        },




        // 当查询框被删减为空时自动查询所有数据
        valueChanged() {
          if (this.queriedOId === "") {
            console.log("当前为空字符串");
            this.queryByoId();
          }
        },

        //根据订单号查询
        queryByoId()
        {
          console.log(this.queriedOId);
          if (this.queriedOId=='')
          {
            console.log("空字符");
            this.reload();
            return;
          }
          this.$ajax.get("http://localhost:8080/Orders/findByOId/oId="+this.queriedOId).then(response => {
            console.log(response);
            if(response.data.oIsdone != null  )
            {
              //如果oIsdone 为 0 ，即为未完成，则在未完成栏显示。
              //如果oIsdone为1，即为已完成，则在已完成栏显示
              console.log("success")
              console.log(response.data);
              console.log("woaibeijign")
              console.log(response.data.oIsdone);//1 or 0
              console.log("success");
              if(response.data.oIsdone===0)
              {this.orderData=[];//两个列表均清空
                this.finishedData=[];
              console.log("userDate")
              console.log(this.orderData)
              this.orderData.push(response.data);
              // this.userData=response.data;
              console.log(this.orderData);
              this.alterQuerySuccess();}
              if(response.data.oIsdone===1)
              {
                this.orderData=[];
                this.finishedData=[];
                console.log("finishedData")
                console.log(this.finishedData)
                this.finishedData.push(response.data);
                // this.userData=response.data;
                console.log(this.finishedData);
                this.alterQuerySuccess();
              }
            }
            else{
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

        //未完成订单改为已完成
        finished()
        {
          console.log(this.orderData[0].proNumber);
          console.log(this.orderData[0].oId);
          this.$ajax({
            method: "get",
            url: "http://localhost:8080/Orders/UpdateOIsdone/oIsdone=1oId="+this.orderData[0].oId
          }).then(response => {
            console.log("123123123213");
            console.log(response.data);
            console.log("订单"+this.orderData[0].oId+"已完成");

            this.$message({
              message: "恭喜你，订单" + this.orderData[0].oId + "已完成!",
              type: "true"
            });
            this.reload();


          });

          //找到product列表里的bnumber
          this.$ajax({
            method: "get",
            url: "http://localhost:8080/Product/findByProId/proId="+this.orderData[0].proId
          }).then(response => {
            console.log("product列表里的吧number为：");
            console.log(response.data.proNumber);
            this.boNumber=response.data.proNumber;
            this.changebnumber();

            let sId=response.data.store.sId;
            console.log("zkjalkjsaflasfjaksdjflasjflksdf");
            console.log(sId);
            this.$ajax.get("http://localhost:8080/Store/findBySId/sId="+sId).then(res=>{
              let Store=res.data;
              let S=res.data.sNumber;

              //改变store列表里的snumber
              this.$ajax({
                method: "get",
                url: "http://localhost:8080/Store/UpdateSNumber/sNumber="+(S-this.orderData[0].oNumber)+"sId="+Store.sId
              }).then(response => {
                console.log("改变store表格里的snumber");

              })
            })


            })



          //找到product列表里所在的仓库号
          // this.$ajax({
          //   method: "get",
          //   url: "http://localhost:8080/Product/findBySId/sId="+this.orderData[0].proId
          // }).then(response => {
          //   console.log("product列表里的商品籍数量为：")
          //   console.log(response.data.proNumber)
          //   this.boNumber=response.data.proNumber
          //   console.log("product列表里的仓库号为：")
          //   console.log(response.data.sId)
          //   this.soId=response.data.sId;
          //   this.changebnumber()
          //   this.findsnumber()
          // })







            //for (let i = 0; i < response.data.length; i++) {
            //this.userData=push(response.data[i]);
            //this.orderData[0]=response.data;
            //}
          //////////////////////////////////////////////////////////////////////////////////////////


        },
        //改变product列表里的bnumber
        changebnumber()
        {
          //改变product列表里的bnumber
          this.$ajax({
            method: "get",
            url: "http://localhost:8080/Product/UpdateNumber/proNumber="+(this.boNumber-this.orderData[0].oNumber)+"proId="+this.orderData[0].proId
          }).then(response => {
            console.log("改变product表格里的bnumber")
            console.log(this.orderData[0].proNumber);
          })
        },



        changesnumber()
        {
          //改变store列表里的snumber
          this.$ajax({
            method: "get",
            url: "http://localhost:8080/Store/UpdateSNumber/sNumber="+(this.S-this.orderData[0].oNumber)+"sId="+this.soId
          }).then(response => {
            console.log("改变store表格里的snumber");

          })
        },
      },

    }
</script>

<style scoped>
  h1 {
    display: block;
    margin: 50px;
    color: #FF0000;
  }
  h2 {
    display: block;
    margin: 0;
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
