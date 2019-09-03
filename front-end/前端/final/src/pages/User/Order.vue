<template>
  <div>
    <el-tabs type="border-card">
      <el-tab-pane label="我的订单">
    <el-row>
      <el-col>
        <div>
          <br>
          <el-breadcrumb separator="/">
            <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
            <el-breadcrumb-item>我的订单</el-breadcrumb-item>
          </el-breadcrumb>
        </div>
      </el-col>
    </el-row>


    <br>
    <br>
    <el-table
      :data="orderList"
      style="width: 100%">
      <el-table-column type="expand">
        <template slot-scope="props">
          <el-form label-position="left" inline class="demo-table-expand">
            <el-form-item label="订单ID:">
              <span>{{ props.row.oId }}</span>
            </el-form-item>
            <el-form-item label="商品ID:">
              <span>{{ props.row.proId }}</span>
            </el-form-item>
            <el-form-item label="用户ID:">
              <span>{{ props.row.cId }}</span>
            </el-form-item>
            <<el-form-item label="商品名称:">
              <span>{{ props.row.proName }}</span>
            </el-form-item>
            <!--<el-form-item label="商品单价">
              <span>{{ props.row. }}</span>
            </el-form-item>-->
            <el-form-item label="购买数量:">
              <span>{{ props.row.oNumber }}</span>
            </el-form-item>
            <el-form-item label="总金额:">
              <span>{{ props.row.oSum }}</span>
            </el-form-item>
            <!--<el-form-item label="商品简介">
              <span>{{ props.row.id }}</span>
            </el-form-item>-->
          </el-form>
        </template>
      </el-table-column>
      <el-table-column label="商品名称" prop="proName"></el-table-column>
      <el-table-column label="数量" prop="oNumber"></el-table-column>
      <el-table-column label="总金额" prop="oSum"></el-table-column>
      <el-table-column>
        <template slot-scope="scope">
          <el-button @click="LookDetail(scope.row)">评价</el-button>
        </template>
      </el-table-column>
    </el-table>
      </el-tab-pane>
    </el-tabs>
  </div>
</template>

<script>
    export default {
      name: "Order",
      data() {
        return {
          orderList: [],
          thisUser: null,
        }
      },
      mounted() {
        this.findOrder();
      },
      methods: {
        LookDetail(current)
        {
          let b=current.proId;
          this.$router.push('/Product/'+b);
        },
        findOrder() {

          this.$ajax.get("http://localhost:8080/Customer/getCurrentCustomer").then(res => {
            this.thisUser = res.data;
            if(this.thisUser==null){
              this.$message({
                message:"请先登录用户",
                type:"error"
              });
            }
            else{
              this.$ajax.get("http://localhost:8080/Orders/findByCId/cId=" + this.thisUser.cId).then(response => {
                console.log("返回的订单列表");
                console.log(response.data);
                this.orderList = response.data;
              })
            }
          });

        },
      }
    }
</script>

<style scoped>
  .demo-table-expand {
    font-size: 0;
  }
  .demo-table-expand label {
    width: 90px;
    color: #99a9bf;
  }
  .demo-table-expand .el-form-item {
    margin-right: 0;
    margin-bottom: 0;
    width: 50%;
  }
</style>
