
<template>
  <div>
    <!--首页轮播图-->
    <div class="s1">
      <el-carousel :interval="2000" arrow="always" height="450px">
        <el-carousel-item v-for="item in 3" :key="item">
          <div v-if="item==1">
            <img src="/static/index1.png" height="450" width="1400">
          </div>
          <div v-if="item==2">
            <img src="/static/index2.jpg" height="450" width="1400">
          </div>
          <div v-if="item==3">
            <img src="/static/index3.png" height="450" width="1400">
          </div>
        </el-carousel-item>
      </el-carousel>
    </div>

    <!--美妆品牌-->
    <div>
      <el-container>
        <el-main style="background-color: rgba(255,0,0,0);margin-top: 20px">
          <div class="text1">美妆品牌</div>
          <hr>
          <div style="margin-top: 50px;margin-left: 1%">
            <div v-for="brand in brandList" style="float: left;margin-left: 2%;margin-top: 20px">
              <div v-if="brand.braId==1"><el-row><img src="/static/brand/1.png" style="width:450px;height: 230px"></el-row></div>
              <div v-if="brand.braId==2"><el-row><img src="/static/brand/2.png" style="width:450px;height: 230px"></el-row></div>
              <div v-if="brand.braId==3"><el-row><img src="/static/brand/3.png" style="width:450px;height: 230px"></el-row></div>
              <div v-if="brand.braId==4"><el-row><img src="/static/brand/4.png" style="width:450px;height: 230px"></el-row></div>
              <div v-if="brand.braId==5"><el-row><img src="/static/brand/5.png" style="width:450px;height: 230px"></el-row></div>
              <div v-if="brand.braId==6"><el-row><img src="/static/brand/6.png" style="width:450px;height: 230px"></el-row></div>
              <div v-if="brand.braId==7"><el-row><img src="/static/brand/7.png" style="width:450px;height: 230px"></el-row></div>
              <div v-if="brand.braId==8"><el-row><img src="/static/brand/8.png" style="width:450px;height: 230px"></el-row></div>

            <el-row><div class="text2"  @click="turnToProList(brand.braName)">{{brand.braName}}</div></el-row>
            </div>
          </div>
        <div>
        </div>
        </el-main>
      </el-container>
    </div>

    <br>
    <hr width="90%">

    <!--页尾平台介绍-->
    <div class="s2">
      <el-collapse accordion>
        <el-collapse-item>
          <template slot="title">
            为什么选择我们 The reasons to choose emily.  <i class="el-icon-caret-bottom"></i>
          </template>
          <div>让我们给出你无法拒绝的理由</div>
        </el-collapse-item>
        <el-collapse-item>
          <template slot="title">
            专业性 Professional<i class="header-icon el-icon-info"></i>
          </template>
          <div>我们是一家专注于做护肤品的平台；</div>
          <div>我们的理念是，只做好一样事，将最好的化妆品带到最美丽的你身边；</div>
          <div>每个女孩子都是天使，天使爱美丽。</div>
        </el-collapse-item>
        <el-collapse-item>
          <template slot="title">
            效率 Efficiency<i class="header-icon el-icon-info"></i>
          </template>
          <div>简化流程：设计简洁直观的操作流程；</div>
          <div>清晰明确：商品分类明确，让用户快速找到适合自己的护肤品。</div>
        </el-collapse-item>
        <el-collapse-item>
          <template slot="title">
            及时性 Timeliness<i class="header-icon el-icon-info"></i>
          </template>
          <div>及时同步中华地区上新的护肤品，只为给你最好的体验；</div>
          <div>保证正品，商品来源于专柜，接受广大消费者的监督。</div>
        </el-collapse-item>
      </el-collapse>
    </div>
  </div>
</template>

<script>
    export default {
      name: "index",
      data(){
        return{
          brandList:[],
        }
      },

      mounted(){
        this.showBrand();

      },

      methods:{
        showBrand(){
          console.log(111111111)
          this.$ajax.get("http://localhost:8080/Brand/FindAll").then(response=>{
            console.log(response.data);
            this.brandList=response.data;
          })
        },
        turnToProList(braName){
          let k=braName;
          this.$router.push('/ProductList/'+k);
        }
      }
    }
</script>

<style scoped>
.s1{
  margin-top: 20px;
  width: 90%;
  margin-left: 5%;
}

.s2{
  margin-top: 50px;
  width: 90%;
  margin-left: 5%;
  margin-bottom: 10px
}

  .text1{
    font-size:20px;
    color: #575757;
  }

  .text2{
    font-weight: bold;
  }

  .text2:hover{
  color: rgba(252, 0, 47, 0.76);
}
</style>
