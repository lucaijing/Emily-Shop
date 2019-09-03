<template>
    <div id="seniorSearch">
      <!--高级搜索导航栏-->
      <el-container>
        <el-main style="background-color: rgba(255,0,0,0);padding-left: 0px;padding-right: 3px">
          <el-tabs type="border-card" style="width: 100%">
          <el-tab-pane label="高级搜索">
            <el-row>
            <div class="out">
              <el-col span="2"><div class="ss">品牌</div></el-col>
              <el-col span="14" >
                <div class="s3">
                  <el-checkbox-group v-model="checkboxGroup2" size="medium" max=1>
                    <el-checkbox-button v-for="brand in brands" :label="brand" :key="brand" @change="showList">{{brand}}</el-checkbox-button>
                  </el-checkbox-group>
                </div>
              </el-col>
            </div>
          </el-row>
            <el-row>
              <div class="out">
                <el-col span="2"><div class="ss">适用人群</div></el-col>
                <el-col span="16" >
                  <div class="s3">
                    <el-checkbox-group v-model="defaultSkin" size="medium" max=1>
                      <el-checkbox-button v-for="skin in skins" :label="skin" :key="skin " @change="showList">{{skin}}</el-checkbox-button>
                    </el-checkbox-group>
                  </div>
                </el-col>
              </div>
            </el-row>
            <el-row>
              <div class="out">
                <el-col span="2"><div class="ss">分类</div></el-col>
                <el-col span="16" >
                  <div class="s3">
                    <el-checkbox-group v-model="defaultType" size="medium" max=1>
                      <el-checkbox-button v-for="type in types" :label="type" :key="type" @change="showList">{{type}}</el-checkbox-button>
                    </el-checkbox-group>
                  </div>
                </el-col>
              </div>
            </el-row>
            <el-row>
              <div class="out">
                <el-col span="2"><div class="ss">价格</div></el-col>
                <el-col span="16" >
                  <div class="s3">
                    <el-checkbox-group v-model="defaultPrice" size="medium" max=1>
                      <el-checkbox-button v-for="price in prices" :label="price" :key="price" @change="showList">{{price}}</el-checkbox-button>
                    </el-checkbox-group>
                  </div>
                </el-col>
              </div>
            </el-row>
            <!--<div v-if="type===null||skin===null||name===null"></div>-->

          </el-tab-pane>
        </el-tabs>
        </el-main>

      </el-container>

      <!--显示列表内容-->
      <div>
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
                  <el-row><img :src="pro.proPic" width="220px" height="220px" alt="product"></el-row>
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
          :page-size="pageSize"
          background
          layout="prev, pager, next"
          :total="currentTotal">
        </el-pagination>
      </div>

    </div>
</template>

<script>
  const brandOptions = ['所有', '法国lancome', '雅诗兰黛EsteeLauder', '玉兰油Olay','百雀羚PECHOIN','欧莱雅Loreal','丸美Perfect','自然堂CHANDO'];
  const skinOptions=['所有','干性皮肤','中性皮肤','油性皮肤','任何肤质'];
  const typeOptions=['所有','化妆水','面霜','乳液','面膜','眼霜','眼部精华','护肤套装'];
  const priceOptions=['所有','0-149','150-339','340-829','830-1499']
  export default {
    name: "seniorSearch",
    data() {
      return {

        /*高级搜索选项*/
        checkboxGroup2: ['所有'],
        brands: brandOptions ,

        defaultSkin:['所有'],
        skins:skinOptions,

        defaultType:['所有'],
        types:typeOptions,

        defaultPrice:['所有'],
        prices:priceOptions,

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

      }
    },
    mounted(){
      this.showList();
    },
    methods: {
      /*传参给product，进入商品详情*/
      LookDetail(proId)
      {
        this.$router.push('/Product/'+proId);
      },

      /*分页*/
      handleCurrentChange(val) {
        this.currentPage = val;
        console.log(`当前页: ${val}`);
      },

      /*加入购物车*/
      AddtoShopcart(proId){
        this.$ajax.get("http://localhost:8080/Shopcart/addcart/proId="+proId+"num=1").then(response=>{
          console.log(response.data);
          this.$message({
            message:"购物车添加成功",
            type:"success"
          });
        })
      },

      /*显示商品列表*/
      showList(){
        console.log('change');
        let price1=0;
        let price2=999999;
        let name='';
        let skin='';
        let type='';


        if(this.checkboxGroup2[0]!=='所有'&& this.checkboxGroup2[0]){
          console.log(this.checkboxGroup2[0]);
            name=this.checkboxGroup2[0];
        }


        if(this.defaultSkin[0]!=='所有'&&this.defaultSkin[0]){
          skin=this.defaultSkin[0];
        }

        if(this.defaultType[0]!=='所有'&&this.defaultType[0]){
          type=this.defaultType[0];
        }

        if(this.defaultPrice[0]==='所有'){
          price1=0;
          price2=99999;
        }
        else if(this.defaultPrice[0]==='0-149'){
          price1=0;
          price2=149;
        }
        else if(this.defaultPrice[0]==='150-339'){
          price1=150;
          price2=339;
        }
        else if(this.defaultPrice[0]==='340-829'){
          price1=340;
          price2=829;
        }
        else if(this.defaultPrice[0]==='830-1499'){
          price1=830;
          price2=1499;
        }

 /*       var money=[];
        money = price.split("-");
        console.log('///////////////////////////');
        console.log(money[0],money[1]);
        console.log('///////////////////////////');*/
        /*price1=money[0].parseInt();
        price2=money[1].parseInt();
        console.log(price1,price2)*/

        console.log(name,skin,type,price1,price2);
        this.$ajax.get("http://localhost:8080/Product/findByProSenior/proBraname="+name+
          "proSkin="+skin+"proType="+type+"price1="+price1+"price2="+price2).then(response=>{
            console.log(response.data);
            this.proList=response.data;
            console.log('proList');
            console.log(this.proList);
            this.currentTotal=Math.ceil(this.proList.length/8-2)*10;
        })

        this.$ajax.get("http://localhost:8080/Product/findByProSeniorDesc/proBraname="+name+
          "proSkin="+skin+"proType="+type+"price1="+price1+"price2="+price2).then(response=>{
          console.log(response.data);
          this.proList2=response.data;
          console.log('proList');
          console.log(this.proList);
        })
      }
    }
  }
</script>

<style scoped>
  .out{
    margin-top: 10px;
  }
  .ss{
    font-size: 13px;
    margin-left: 3%;
  }
  .s3{
    font-size: 13px;
    float: left;
  }

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
