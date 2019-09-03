<template>
  <div>
    <el-tabs type="border-card">
      <el-tab-pane label="购物车">
    <div>
      <el-row>
        <el-col :span="12" >
          <!--<el-form :inline="true" style="text-align:right">
            <el-form-item>
                <el-input v-model="proName" placeholder="在购物车中搜索" style="width:300px" ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button v-model="proName" type="primary" icon="el-icon-search" @click="this.dialogVisible=true"></el-button>
            </el-form-item>
          </el-form>-->

        </el-col>
      </el-row>
    </div>



    <!--提交订单-->

<!--    <el-button @click="add">点击添加一个商品到购物车</el-button>-->
    <!--点击搜索，则弹出对话框，显示搜索商品的信息。点击好的，谢谢，或者添加到购物车-->
   <!-- <el-dialog
      :visible.sync="dialogVisible"
      width="50%"
      title="您搜索的商品信息如下！"
      :before-close="handleClose">

      <el-table
      <span slot="footer" class="dialog-footer">
             <el-button @click="dialogVisible = false">好的，谢谢！</el-button>
              <el-button type="primary" @click="CreateShopcart">添加到购物车！</el-button>
             </span>
    </el-dialog>-->
    <!--<table width="20cm" >-->
      <!--<table width="20cm" border="1" cellpadding="1" cellspacing="1">-->
        <!--<table  width="20cm" border="1" cellpadding="0" cellspacing="0">-->
<!---------------------------------------------------------------------------------------------------------------
    <table  class="table table-hover table-shop">
          <thead>
            <tr>
              <th>商品号</th>
              <th>商品名</th>
              <th>购买数量</th>
              <th>单价</th>
              <th>总金额</th>
              <th>操作</th>

           </tr>
          </thead>
          <tbody>
          --------------------------------------------------------------------------------------------------------------->
            <!--<tr v-for="(item,index) in productList">-->
              <!--<td><input type="checkBox"   v-bind:checked="item.isSelect" @click="item.isSelect=!item.isSelect"/></td>-->
              <!--<td>{{item.proName}}</td>-->
              <!--<td><span><a href="#" @click="item.proNum>0?item.proNum&#45;&#45;:''">-</a></span><strong>{{item.proNum}}</strong><span><a href="#" @click="item.proNum++">+</a></span></td>-->
              <!--<td>{{item.proPrice}}</td>-->
              <!--<td>{{item.proPrice*item.proNum}}</td>-->
              <!--<td><a href="#" @click="deletePro(index)" class="btn btn-success" >删除</a></td>-->
            <!--</tr>-->
    <!---------------------------------------------------------------------------------------------------------------
            <tr v-for="(cart,index) in productList">
              <td>{{cart.id}}</td>
              <td>{{cart.proName}}</td>
              <td><span><a href="#" @click="cart.proNum>0?cart.proNum--:''">-</a></span><strong>{{cart.proNum}}</strong><span><a href="#" @click="cart.proNum++">+</a></span></td>
--------------------------------------------------------------------------------------------------------------->
              <!--<td>{{cart.proNum}}</td>-->
              <!--<td class="td-num">-->
                <!--<div class="product-num">-->
                  <!--<a href="javascript:;" class="num-reduce num-do fl" @click="item.proNum&#45;&#45;"><span></span></a>-->

                    <!--<input type="text" class="num-input" v-model="item.proNum">-->

                  <!--<a href="javascript:;" class="num-add num-do fr" @click="item.proNum++"><span></span></a>-->
                <!--</div>-->
              <!--</td>-->
    <!---------------------------------------------------------------------------------------------------------------
              <td>{{cart.proPrice}}</td>
              <td>{{cart.proPrice*cart.proNum}}</td>
              <td>
                <button type="button" @click='deletePro(index)' class="btn btn-success" >删除</button></td>
            </tr>
          </tbody>


    </table>
-------------------------------------------------------------------------------------------------------------->
    <el-table
      :data="productList"
      height="400"
      border
      style="width: 100%">
      <el-table-column prop="proName" label="名称" width="180"></el-table-column>
      <el-table-column prop="num" label="购买数量"></el-table-column>
      <el-table-column prop="proPrice" label="单价"></el-table-column>
      <el-table-column prop="sum" label="总金额"></el-table-column>
      <el-table-column label="更改数量">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" @click="subCurrent(scope.row)">-</el-button>
          <el-tag>{{showNum(scope.row)}}</el-tag>
          <el-button type="primary" size="mini" @click="addCurrent(scope.row)">+</el-button>
        </template>
      </el-table-column>
      <el-table-column label="操作">
        <template slot-scope="scope">
          <el-button type="primary" size="mini" icon="el-icon-delete" @click="DeleteShopcart(scope.row)">移出购物车</el-button>
        </template>
      </el-table-column>
    </el-table>
        <div style="float: right;margin-right: 5%">
          <el-row>
              合计 ：{{allPay}}元
          </el-row>
          <el-button v-model="proName" type="primary" style="margin-top: 20px;margin-bottom: 20px;float: right" @click="CreateOrders">提交订单</el-button>
        </div>

    </el-tab-pane>
    </el-tabs>
  </div>


</template>

<script>
  export default {
    name: "Shopcart",
    data(){
      return{
        allPay:0,//总金额
        proName:'',
        dialogVisible: false,
        productList:[],//购物车表
        Orders:{
          proId:null,
          proName:null,
          oNumber:null,
          cId:null,
          oSum:null,
          cAddress:null,
          oIsdone:null,
        }
      }
    },
    mounted(){
      this.findAll();
    },
    methods: {

      showNum(current){
        return current.num;
      },
      findAll(){
        this.$ajax.get("http://localhost:8080/Shopcart/findAll").then(response=>{
          this.productList=response.data;
          console.log('wwwwwwww')
          console.log(response.data);
          console.log('???')
          console.log(this.productList.length)
          this.allPay=0;
          for(let i=0;i<this.productList.length;i++){
            console.log("///////");
            this.allPay=this.allPay+this.productList[i].sum;
            console.log(this.allPay);
          }
        })
      },
      subCurrent(current){
        let b=current.proId;
        console.log(b);
        this.$ajax.get("http://localhost:8080/Shopcart/addcart/proId="+b+"num="+"-1").then(response=>{
          this.productList=response.data;
          this.allPay=0;
          for(let i=0;i<this.productList.length;i++){
            console.log("///////");
            this.allPay=this.allPay+this.productList[i].sum;
            console.log(this.allPay);
          }
        })
      },
      addCurrent(current){
        let b=current.proId;
        console.log(b);
        this.$ajax.get("http://localhost:8080/Shopcart/addcart/proId="+b+"num="+"1").then(response=>{
          this.productList=response.data;
          this.allPay=0;
          for(let i=0;i<this.productList.length;i++){
            console.log("///////");
            this.allPay=this.allPay+this.productList[i].sum;
            console.log(this.allPay);
          }
        })
      },
      DeleteShopcart(current){
        let b=current.proId;
        this.$ajax.get("http://localhost:8080/Shopcart/clearone/proId="+b).then(response=>{
          this.$message({
            message:"删除成功",
            type:"success"
          });
          this.findAll();
        })

        //location.reload();
      },
      CreateOrders(){

        console.log(this.productList);
        let length=this.productList.length;
        console.log(length);
        for(let i=0;i<this.productList.length;i++){

          /*this.Orders.proId=this.productList[i].proId;
          this.Orders.proName=this.productList[i].proName;
          this.Orders.oNumber=this.productList[i].num;
          this.Orders.oSum=this.productList[i].sum;
          this.Orders.oIsdone=0;*/

          //调用后台获取session中的用户信息
          this.$ajax.get("http://localhost:8080/Customer/getCurrentCustomer").then(response=>{
            console.log(response);
            this.Orders.cId=response.data.cId;
            this.Orders.cAddress=response.data.cAddress;
            console.log(this.Orders.cId);

            this.Orders.proId=this.productList[i].proId;
            this.Orders.proName=this.productList[i].proName;
            this.Orders.oNumber=this.productList[i].num;
            this.Orders.oSum=this.productList[i].sum;
            this.Orders.oIsdone=0;

            let data=this.Orders;
            console.log(data);
            this.$ajax.post("http://localhost:8080/Orders/CreateO",JSON.stringify(data),{
              headers:{'Content-Type': 'application/json;charset=UTF-8' }
            }).then(response=>{//OrdersController接收对象，保存在数据库中
              //console.log(response.data);

            });
          });
          //this.Orders.cId=cId;
          //this.Orders.cAddress=cAdd;

        }
        //清空购物车
        this.$ajax.get("http://localhost:8080/Shopcart/clearAll").then(response=>{
          console.log(response.data);
          this.findAll();
          this.$message({
            message:"订单已发送，后台为您发货中",
            type:"success"
          })
        })
      },
/*      add(){
        this.$ajax.get("http://localhost:8080/Shopcart/addcart/proId="+11+"num=1").then(response=>{
          console.log(response.data);

        })
        this.$ajax.get("http://localhost:8080/Shopcart/addcart/proId="+12+"num=1").then(response=>{
          console.log(response.data);
        })
      },*/
     /* deletePro(index)
      {
        alert("你正在删除"+this.productList[index].proName);
        this.productList.splice(index,1);

      },*/

    }
  };


</script>

<style scoped lang='less'>

  .table-bordered{
    border: 1px solid #fff;
  }
  thead {
    display: table-header-group;
    vertical-align: middle;
    border-color: inherit;
    line-height: 1.42857143;

  }

  body {
    font-size: 14px;
    line-height: 1.42857143;
  }
  .table {
    width: 100%;
    max-width: 100%;
    margin-bottom: 20px;
  }
  table {
    border-spacing: 0;
    border-collapse: collapse;
  }
.el-main{background-color:#ffff;}
  /*table{width: 1200px;}*/
  /*table th{width: 100px}*/
  /*table td{width: 200px;text-align: center;}*/
  /*a{text-decoration:none;color: red}*/
  /*span{font-size: 20px;margin: 10px 10px}*/
  /*strong{border: 1px solid;}*/
  /*table th {*/
    /*white-space: nowrap;*/
  /*}*/

  /*table td {*/
    /*white-space: nowrap;*/
  /*}*/

  /*table {*/
    /*empty-cells: show;*/
    /*border-collapse: collapse;*/
    /*margin: 0 auto;*/
  /*}*/
  /*.table-bordered{*/
    /*border: 1px solid #ddd;*/
    /*border-collapse: separate;*/
    /*border-left: 0;*/

  /*}*/
  /*.table-hover tr:hover {background:#eee}*/





</style>
