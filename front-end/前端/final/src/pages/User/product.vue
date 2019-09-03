<template>
    <div class="s1">
      <div style="margin-left: 20px;margin-right: 20px">
        <br>
      <el-breadcrumb separator-class="el-icon-arrow-right">
        <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
        <el-breadcrumb-item>宝贝详情</el-breadcrumb-item>
      </el-breadcrumb>
      <el-container>
        <el-aside width="300px" style="background-color: rgb(255, 255, 255);border-radius: 30px;text-align:left;margin-top: 80px;margin-right: 20px">
          <img :src="product.proPic" width="280px" height="280px" style="margin-left: 10px">
        </el-aside>
        <div class="s2">
            <div class="NameTitle">
              {{this.product.proName}}
            </div>

  <!--            <el-col :span="4">适用人群：{{this.product.proSkin}}</el-col>
              <el-col :span="6" offset="1">品牌：{{this.product.proBraname}}</el-col>
              <el-col :span="4" offset="1">所属类别：{{this.product.proType}}</el-col>-->
            <div style="margin-left:60px;text-align: left;margin-top: 20px">
              <hr>
              <el-col>适用人群：{{this.product.proSkin}}</el-col>
              <el-col>品牌名：{{this.product.proBraname}}</el-col>
              <el-col>护肤品种类：{{this.product.proType}}</el-col>
              <el-col style="margin-top: 20px">价格：<span class="price">{{this.product.proPrice}}元</span></el-col>
              <el-col style="margin-top: 20px;margin-bottom: 20px">

                <el-button type="primary" size="big"  @click="AddtoShopcart" style="width: 250px ;height: 80px;letter-spacing:2px;font-size: 25px">

                  <div>
                    <i class="el-icon-plus"></i>
                  添加到购物车
                  </div>


                </el-button>
              </el-col>

              <el-col style="margin-top: 20px;width: 600px">产品介绍：{{this.product.proDescription}}</el-col>
            </div>
        </div>
      </el-container>
      <el-container>

        <el-main style="background-color: rgba(255,0,0,0)">
          <br>
          <el-tabs type="border-card" style="width: 100%;margin-top: 60px;margin-bottom: 30px">
            <el-tab-pane label="大家的评价">
              <el-table :data="tableData" style="width: 90%;margin-left: 5%">
                <el-table-column prop="customer.cName" label="用户名" width="250">
                </el-table-column>
                <el-table-column prop="cpStar" label="评分" width="250">

                  <template slot-scope="scope">
                    <el-rate v-model="scope.row.cpStar" disabled show-score text-color="#ff9900" score-template="{value}"></el-rate>
                  </template>

                </el-table-column>
                <el-table-column prop="cpContent" label="评价">
                </el-table-column>
              </el-table>
            </el-tab-pane>
          </el-tabs>

          <div v-if="isinOrders" >
          <el-tabs type="border-card" style="margin-top: 10px">

            <el-tab-pane label="您的评价">
              <el-form >
                <el-form-item>
                  <el-rate v-model="CB.cpStar" show-text></el-rate>
                </el-form-item>
                <el-form-item>
                  <el-input type="textarea" :rows="4" placeholder="请输入您对于本商品的评价，其他买家需要您的宝贵意见" v-model="CB.cpContent"
                            style="margin-left:10px;margin-top: 10px;width: 1300px" >
                  </el-input>
                </el-form-item>
                <el-form-item>
                  <el-button type="primary" @click="CreateCB">提交<i class="el-icon-upload el-icon--right"></i></el-button>
                </el-form-item>
              </el-form>

            </el-tab-pane>

          </el-tabs>
      </div>


        </el-main>

      </el-container>
      </div>
    </div>
</template>

<script>
    export default {
        name: "product",
        data(){
          return{
            /*以下方法就是接收父页面传参
            获取id:this.$route.params.id
            获取title:this.$route.params.title,
            */
            proId:this.$route.params.proId,
            product:null,

            tableData:[],
            thisUser:null,
            isinOrders:false,

            CB:{
              customer:{
                cId:null,
              },
              product:{
                proId:null,
              },
              cpContent:null,
              cpStar:null,
            },
          }
        },
      mounted() {
        console.log(this.proId);
        this.lookName();
        //this.show();//三个函数放在一起不会顺序执行，会三个线程分别执行，不遵照顺序，会出现错误
        //this.judgeisinOrders();//判断该用户有无购买该商品，是否具有评论权力
      },
      methods:{
        lookName(){
          //先获取商品对象
          this.$ajax.get("http://localhost:8080/Product/findByProId/proId="+this.proId).then(response=>{
            this.product=response.data;
            console.log("lookName:");
            console.log(response.data);


            //再获取本商品的所有评论
            this.show();
          })


        },
        judgeisinOrders(){
          console.log("judgeisinOrders:");
          this.$ajax.get("http://localhost:8080/Customer/getCurrentCustomer").then(response=>{
            console.log(response.data);
            if(response.data==null){
              this.isinOrders=false;
            }
            else{
              this.thisUser=response.data;
              let cId=this.thisUser.cId;
              let OrderList=[];
              this.$ajax.get("http://localhost:8080/Orders/findByCId/cId="+cId).then(res=>{
                OrderList=res.data;
                for(var i=0;i<OrderList.length;i++){
                  if(OrderList[i].proId==this.product.proId){
                    this.isinOrders=true;
                    break;
                  }
                }
              })
            }
            console.log("isinOrders"+this.isinOrders);
          })
        },
        show(){
          console.log("show:");

          this.$ajax.get("http://localhost:8080/CustomerProduct/findByProId/proId="+this.proId).then(response=>{
            console.log(response.data);
            this.tableData=response.data;

            //再判断该用户有无购买该商品，是否具有评论权力
            this.judgeisinOrders();

          })
        },
        CreateCB(){
          this.CB.customer.cId=this.thisUser.cId;
          this.CB.product.proId=this.product.proId;
          let data=this.CB;
          this.$ajax.post("http://localhost:8080/CustomerProduct/CreateCB",JSON.stringify(data),{
            headers:{'Content-Type': 'application/json;charset=UTF-8'}
          }).then(response=>{
            console.log(response.data);
            if(response.data){
              this.$message({
                message:"评论成功",
                type:"success"
              });

              //立刻显示更新
              this.$ajax.get("http://localhost:8080/CustomerProduct/findByProId/proId="+this.proId).then(response=>{
                this.tableData=response.data;
                this.CB.cpStar=0;
                this.CB.cpContent=null;

              })
            }
            else{
              this.$message({
                message:"评论失败，请稍后重试",
                type:"error"
              });
            }

          })
        },
        AddtoShopcart(){
          this.$ajax.get("http://localhost:8080/Shopcart/addcart/proId="+this.proId+"num=1").then(response=>{
            console.log(response.data);
            this.$message({
              message:"购物车添加成功",
              type:"success"
            });
          })
        },
      },

    }
</script>

<style scoped>
.NameTitle{
  font-family: "微软雅黑";
  line-height: 1.5;
  font-size: 40px;
  letter-spacing: 1px;
}

.price{
  /*color: rgba(233, 255, 250, 0.79);*/
  color: #dd6161;
  font-size: 30px;

}

/*分割线css样式*/
#three_dot_view{
  width: 330px;
  height: 20px;
}

  .s1{
  margin-left: 8px;
    margin-right: 8px;
    background-color: rgba(255, 68, 102, 0.07)
  }

.s2{
  margin-left: 8px;
  margin-right: 8px;
  margin-top: 20px;
}


</style>
