<template>
  <div>
    <div style="margin-bottom: 8px">
      <el-breadcrumb separator-class="el-icon-arrow-right">
      <el-breadcrumb-item :to="{ path: '/index' }">首页</el-breadcrumb-item>
      <el-breadcrumb-item>商品列表</el-breadcrumb-item>
    </el-breadcrumb>
  </div>
    <el-tabs type="border-card" class="card-style" v-model="defaultName">
      <el-tab-pane label="排序" disabled="true" style="font-weight: bolder"></el-tab-pane>
      <el-tab-pane label="默认排序" name="默认排序">
        <div style="margin-top: 15px;margin-left: 30px;font-size: 12px">
<!--          <a href="http://localhost:8081/#/product/1">-->
          <div class="pic-css" v-for="pro in proList.slice((currentPage-1)*pageSize,currentPage*pageSize)"
          @click="LookDetail(pro.proId)">
            <el-card shadow="hover" style="width: 240px;height: 450px">
              <!--<el-row><img :src="pro.img" width="200px" height="280px" alt="product"></el-row>-->
              <!--<el-row><img :src="currentImg" width="200px" height="280px" alt="product">-->
              <el-row><img :src="pro.proPic" width="220x" height="220px" alt="product"></el-row>
              <el-row><div style="height: 40px">{{pro.proName}}</div></el-row>
              <el-row><div class="priceStyle">￥{{pro.proPrice}}</div></el-row>
              <el-row style="float: left">适用人群：{{pro.proSkin}}</el-row>
              <br>
              <el-row>
                <el-button type="primary" size="small"  @click="AddtoShopcart(pro.proId)" style="width: 140px ;height: 40px;float: left;margin-top: 5px">
                  <div>
                    <i class="el-icon-plus"></i>
                    添加到购物车
                  </div>
                </el-button>
              </el-row>
            </el-card>
          </div>
<!--          </a>-->
        </div>
      </el-tab-pane>

      <el-tab-pane label="价格降序" >
        <div style="margin-top: 15px;margin-left: 25px;font-size: 12px">
          <!--          <a href="http://localhost:8081/#/product/1">-->
          <div class="pic-css" v-for="pro in proList2.slice((currentPage-1)*pageSize,currentPage*pageSize)"
               @click="LookDetail(pro.proId)">
            <el-card shadow="hover" style="width: 250px;height: 450px">
              <!--<el-row><img :src="pro.img" width="200px" height="280px" alt="product"></el-row>-->
              <!--<el-row><img :src="currentImg" width="200px" height="280px" alt="product">-->
              <el-row><img :src="pro.proPic" width="220x" height="220px" alt="product"></el-row>
              <el-row><div style="height: 40px">{{pro.proName}}</div></el-row>
              <el-row><div class="priceStyle">￥{{pro.proPrice}}</div></el-row>
              <el-row style="float: left">适用人群：{{pro.proSkin}}</el-row>
              <br>
              <el-row>
                <el-button type="primary" size="small"  @click="AddtoShopcart(pro.proId)" style="width: 140px ;height: 40px;float: left;margin-top: 5px">
                  <div>
                    <i class="el-icon-plus"></i>
                    添加到购物车
                  </div>
                </el-button>
              </el-row>
            </el-card>
          </div>
          <!--          </a>-->
        </div>
      </el-tab-pane>
    </el-tabs>

    <el-pagination
      @current-change="handleCurrentChange"
      :current-page="currentPage"
      page-count="4"
      :page-size="pageSize"
      background
      layout="prev, pager, next"
      :total="currentTotal">
    </el-pagination>
  </div>
</template>

<script>
    export default {
        name: "productList",
      data() {
        return {
          /*分页参数*/
          currentPage: 1,
          pageSize: 8,
          currentTotal:0,
          //currentTotal:(proList.length%8+1)*10,
          /*以下方法就是接收父页面传参
          获取id:this.$route.params.id
          获取title:this.$route.params.title,
          */
          defaultName:'默认排序',
          key: this.$route.params.k,
          proList:[],
          proList2:[],
          TypeName:null,
         /* currentImg:"/static/pic/1.jpg",*/
        }
      },
      mounted(){
          this.showList();
      },
      methods:{
          /*传参给product，进入商品详情*/
        LookDetail(proId)
        {
          this.$router.push('/Product/'+proId);
        },
        handleCurrentChange(val) {
          this.currentPage = val;
          console.log(`当前页: ${val}`);
        },

        AddtoShopcart(proId){
          this.$ajax.get("http://localhost:8080/Shopcart/addcart/proId="+proId+"num=1").then(response=>{
            console.log(response.data);
            this.$message({
              message:"购物车添加成功",
              type:"success"
            });
          })
        },
          showList(){
            console.log('///');
            console.log(this.key);
            if(this.key==='0'){this.TypeName="全部分类";}
            else if(this.key==='1'){this.TypeName="化妆水";}
            else if(this.key==='2'){this.TypeName="面霜";}
            else if(this.key==='3'){this.TypeName="乳液";}
            else if(this.key==='4'){this.TypeName="面膜";}
            else if(this.key==='5-1'){this.TypeName="眼霜";}
            else if(this.key==='5-2'){this.TypeName="眼部精华";}
            else if(this.key==='6'){this.TypeName="护肤套装";}
            console.log(this.key);

            if(this.key==='1'||this.key==='2'||this.key==='3'||this.key==='4'||this.key==='5-1'||this.key==='5-2'||this.key==='6'){
              this.$ajax.get("http://localhost:8080/Product/findByProType/proType="+this.TypeName).then(response=>{
                console.log("ww1");
                console.log(response.data);
                this.proList=response.data;
                console.log('-----------');
                console.log(this.proList.length);
                console.log(this.proList.length/8);
                this.currentTotal=(this.proList.length/8-1)*10;
              })

              this.$ajax.get("http://localhost:8080/Product/findByProTypeDesc/proType="+this.TypeName).then(response=>{
                console.log(response.data);
                this.proList2=response.data;
              })
            }
            else if(this.key==='0'){
              console.log("ww2");
              this.$ajax.get("http://localhost:8080/Product/findAll").then(response=>{
                console.log(response.data);
                this.proList=response.data;
                this.currentTotal=(this.proList.length/8-1)*10;
              })

              this.$ajax.get("http://localhost:8080/Product/findByAlleDesc").then(response=>{
                console.log(response.data);
                this.proList2=response.data;
              })
            }
            else if(this.key==='法国lancome'||this.key==='百雀羚PECHOIN'||this.key==='雅诗兰黛EsteeLauder'||this.key==='玉兰油Olay'||this.key==='欧莱雅Loreal'
              ||this.key==='自然堂CHANDO'||this.key==='丸美Perfect'||this.key==='欧诗漫OSM'){
              console.log('品牌名');
              this.$ajax.get("http://localhost:8080/Product/findByProBraname/proBraname="+this.key).then(response=>{
                console.log(response.data);
                this.proList=response.data;
                this.currentTotal=(this.proList.length/8-1)*10;
              })
              this.$ajax.get("http://localhost:8080/Product/findByProBranameDesc/proBraname="+this.key).then(response=>{
                console.log(response.data);
                this.proList2=response.data;
              })
            }
            else{
              console.log("搜索的跳到这里来");
              console.log(this.key)
              this.$ajax.get("http://localhost:8080/Product/findByproNameLike/proName="+this.key).then(response=>{
                console.log(response.data);
                this.proList=response.data;
                this.currentTotal=(this.proList.length/8-1)*10;
              })

              this.$ajax.get("http://localhost:8080/Product/findByproNameLikeDesc/proName="+this.key).then(response=>{
                console.log(response.data);
                this.proList2=response.data;
              })

            }



            }
          }
    }
</script>

<style scoped>
  .priceStyle{
    color: rgb(255, 68, 102);
    font-size: 20px;
    float: left;
    margin-top: 22px;
  }
  .pic-css{
    float: left;
  }
  .pic-css:hover{
    transform: scale(1.1);
  }
</style>
