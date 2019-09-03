<template>
  <div id="Home1" class="bg">
    <el-container>
      <el-header style="color: white">
        <el-row type="flex" class="row-bg" justify="space-around">
          <el-col :span="3">
            <div>
              {{timeShow}}
            </div>
            <!--<div v-if="isLogin">
              {{user.cName}}
            </div>-->
          </el-col>
          <el-col :span="2">
            <div v-if="hours<11">早上好！</div>
            <div v-if="hours>=11 && hours<14">中午好！</div>
            <div v-if="hours>=14 && hours<18">下午好！</div>
            <div v-if="hours>=18">晚上好！</div>
          </el-col>
          <el-col :span="3"  offset="4"><div class="title1">爱美丽</div></el-col>
          <el-col :span="8"  offset="0"><div class="title2">—— 护肤品购物平台</div></el-col>

          <el-col :span="2" offset="3">

            <div v-if="!isLogin">
            <el-button type="primary" v-on:click="dialogVisible=true">登陆</el-button>
            </div>

            <el-dialog
              :visible.sync="dialogVisible"
              width="30%"
              title="用户登录">
              <el-form :model="Login">
                <el-form-item>
                  <el-input v-model="Login.cName" placeholder="请输入用户名/管理员Id" style="margin-top: 10px;"></el-input>
                </el-form-item>
                <el-form-item>
                  <el-input v-model="Login.cPassword" type="password" placeholder="请输入密码" style="margin-top: 10px;"></el-input>
                </el-form-item>
                  <el-radio v-model="loginFlag" label="1">用户</el-radio>
                  <el-radio v-model="loginFlag" label="2">管理员</el-radio>
                <el-form-item>

                    <el-button @click="dialogVisible = false">取 消</el-button>
                    <el-button type="primary" @click="UserLogin">确 定</el-button>

                </el-form-item>
              </el-form>
            </el-dialog>

          </el-col>
          <el-col :span="2">
            <a href="http://localhost:8081/#/Registe">注册</a>
          </el-col>


          <el-col :span="2">
            <el-dropdown>
              <span class="el-dropdown-link" style="color: white">
                个人中心<i class="el-icon-arrow-down el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item><a href="http://localhost:8081/#/userinfo">个人信息</a></el-dropdown-item>
                <el-dropdown-item><a href="http://localhost:8081/#/order">订单列表</a></el-dropdown-item>
                <el-dropdown-item><el-button type="text" @click="LogoutVisible=true" style="color:#99a9bf;background-color: white">注销</el-button></el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </el-col>
        </el-row>
      </el-header>

      <el-dialog :visible.sync="LogoutVisible" width="30%" title="确认注销用户">
        <el-button @click="LogoutVisible=false">取消</el-button>
        <el-button @click="Logout" type="primary">确定</el-button>
      </el-dialog>

    </el-container>

    <el-main>
      <div  style="margin-top: 20px;margin-bottom: 20px">
        <el-row :gutter="20" >
          <el-form >
            <el-col span="2" offset="1">
              <a href="http://localhost:8081/#/seniorSearch">高级搜索</a>
            </el-col>

            <el-col :span="10" offset="1">
              <div>
                <el-form-item>
                  <el-input v-model="productName" placeholder="请您想要购买的化妆品名~~O(∩_∩)O~~"></el-input>
                </el-form-item>
              </div>

            </el-col>

            <el-col :span="2" offset="0">
              <el-form-item>
                <el-button type="primary" icon="el-icon-search" @click="findPro()" >搜索</el-button>
              </el-form-item>
            </el-col>
          </el-form>

          <!--<el-col :span="2" offset="1" style="margin-bottom:20px ;">
            <el-button type="primary" icon="el-icon-search" @click="findTypeProduct"  >搜索</el-button>
          </el-col>-->
          <el-col span="4" offset="4">
            <!--<el-button type="primary"><i class="el-icon-shopping-cart-2"></i></el-button>-->
            <el-button icon="el-icon-check" @click=" turnToCart">去购物车结算</el-button>
          </el-col>
          <el-col>

          </el-col>
        </el-row>
      </div>
      <div>
        <el-menu :default-active="activeIndex" mode="horizontal" @select="handleSelect"
                 background-color="#333333"
                 text-color="#fff"
                 active-text-color="#ffd04b">
          <el-menu-item index="7">首页</el-menu-item>
          <el-menu-item index="0">全部分类</el-menu-item>
          <el-menu-item index="1">化妆水</el-menu-item>
          <el-menu-item index="2">面霜</el-menu-item>
          <el-menu-item index="3">乳液</el-menu-item>
          <el-menu-item index="4">面膜</el-menu-item>
          <el-submenu index="5">
            <template slot="title">眼部护理</template>
            <el-menu-item index="5-1">眼霜</el-menu-item>
            <el-menu-item index="5-2">眼部精华</el-menu-item>
          </el-submenu>
          <el-menu-item index="6">护肤套装</el-menu-item>
        </el-menu>
      </div>

<!--      <div style="float: left">
        <el-card shadow="hover">
          <el-row><img src="/static/pic/1.jpg" width="200px" height="280px"></el-row>
          <el-row>测试</el-row>
        </el-card>
      </div>-->
    </el-main>

      <el-main>
        <router-view></router-view>
      </el-main>

      <el-footer height="120px">
          <img src="/static/back2.jpg" width="100%" height="100%">
      </el-footer>
  </div>
</template>

<script>
export default {
  name: 'Home1',
  data() {
    return {
      productName:null,
      loginFlag:null,
      hours: new Date().getHours(),
      timeShow: new Date().getFullYear() + "年" + (new Date().getMonth() + 1) + "月" + (new Date().getDate()) + "日",
      dialogVisible: false,
      LogoutVisible:false,
      cId:null,
      cPassword:null,
      customerData:[],
      isLogin:false,
      //登录
      Login:{
        cName:null,
        cPassword:null,
      },
      productTypeList:[],
      TypeName:null,

    }
  },
  mounted(){
    this.$ajax.get("http://localhost:8080/Customer/getCurrentCustomer").then(response=>{
      //console.log(response.data);
      let user=response.data;
      if(user!=null){
        this.isLogin=true;//当前有用户在线
      }
      else{
        this.isLogin=false;
      }
      console.log("this.isLogin:"+this.isLogin);
    })

  },
  methods:{
    turnToCart(){
      this.$router.push("/shopcart")
    },
     handleSelect(key) {
       console.log(key);
       if(key==='7'){this.$router.push('/index')}
       else{
         let k=key;
         /*       if(key==='1'){this.TypeName="化妆水";}
                else if(key==='2'){this.TypeName="面霜";}
                else if(key==='3'){this.TypeName="乳液";}
                else if(key==='4'){this.TypeName="面膜";}
                else if(key==='5-1'){this.TypeName="眼霜";}
                else if(key==='5-2'){this.TypeName="眼部精华";}
                else if(key==='5-3'){this.TypeName="眼膜";}
                else if(key==='6'){this.TypeName="护肤套装";}
                console.log(this.TypeName);*/
         //window.location="http://localhost:8081/#/ProductList/"+k;
         this.$router.push('/ProductList/'+k);
         location.reload();/**强制刷新*/
       }

       /*这里进行url传参，传递参数为typeName，在index.js中也需要进行设置，productList页面进行接受参数*/

       /*this.$ajax.get("http://localhost:8080/Product/findByProType/proType="+this.TypeName).then(response=>{
           this.productTypeList=response.data;
          console.log(this.productTypeList)
         }
       )*/
    },
    findPro(){
      let k=this.productName;
      this.$router.push('/ProductList/'+k);
      location.reload();/**强制刷新*/
    },


 /*   FindByType(){
       console.log(this.index);
       this.$ajax.get("http://localhost:8080/Product/findByProType/proType="+this.index).then(response=>{
         this.productTypeList=response.data;
         console.log(this.productTypeList)
         }
       )
    },*/
    UserLogin(){

      let cName=this.Login.cName;
      let cPass=this.Login.cPassword;
      if(this.loginFlag=='1'){
        this.$ajax.get("http://localhost:8080/Customer/CheckC/cName="+cName+"cPass="+cPass).then(response=>{
          console.log(response.data);
          if(response.data){
            this.$message({
              message:"登录成功",
              type:"success"
            });
            this.dialogVisible=false;
            this.isLogin=true;//登录成功后，登录按钮消失
          }
          else{
            this.$message({
              message:"登录失败",
              type:"error"
            })
          }

        });
      }
      else {
        this.$ajax.get("http://localhost:8080/Administrator/CheckA/aId="+cName+"aPass="+cPass).then(response=>{
          console.log(response.data);
          if(response.data){
            this.$message({
              message:"管理员登录成功",
              type:"success"
            });
            this.dialogVisible=false;
            //this.isLogin=true;//登录成功后，登录按钮消失
            window.location="http://localhost:8081/#/userManage";
            window.location.reload();
          }
          else{
            this.$message({
              message:"管理员登录失败",
              type:"error"
            })
          }

        });

      }

    },
    Logout(){
      this.isLogin=false;//注销后出现登录按钮
      this.$ajax.get("http://localhost:8080/Customer/Logout").then(response=>{
        this.$message({
          message:"注销成功",
          type:"success"
        });
      })
      this.LogoutVisible=false;
    }
  }
}
</script>

<style>

  .el-header{
    background-color: rgba(255, 68, 102, 0.56);
    color: #333;
    text-align: center;
    line-height: 60px;
    background-repeat: no-repeat;
    background-position: center;
  }

  .el-footer{
    background-color: rgba(255, 68, 102, 0.23);
    line-height: 100px;
  }

  .el-aside {
    background-color: rgba(255, 68, 102, 0.1);
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    background-color: rgba(255, 68, 102, 0.09);
    color: #333;
    text-align: center;
    line-height: 30px;
    padding-left: 5%;
    padding-right: 5%;
  }

/*  .el-button
  {
    background-color: #b3d8ff;
    border-color: white;
    color: #333333;
  }*/



  body > .el-container {
    margin-bottom: 20px;
/*    margin-left: 10%;
    margin-right: 10%;*/
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 20px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }

  /*分割线css样式*/
  #three_dot_view {
    width: 330px;
    height: 20px;
  }

  .gray_line {
    background: #b4bccc;
    /*background: #333333;*/
    width:1500px;
    height: 3px;
    float:left;
    margin: 10px 5px;
  }
  .title1{
    color:black;
    font-family: "Adobe 黑体 Std R";
    font-size:30px;
    font-weight: bolder;
  }
  .title2{
    color: rgb(255, 255, 255);
    font-family: "Adobe 黑体 Std R";
    font-size:30px;
  }
  .bg{
    background-image: url('/static/back.png');
    width: 100%;
    height: 100%;
    }



.el-carousel__item h3 {
  color: #475669;
  font-size: 18px;
  opacity: 0.75;
  line-height: 300px;
  margin: 0;
}

.el-carousel__item:nth-child(2n) {
  background-color: #99a9bf;
}

.el-carousel__item:nth-child(2n+1) {
  background-color: #d3dce6;}

  a:link{text-decoration-line: none}
  a:visited{color: #000008;}
  a:hover {color: #ff4466;}   /* 鼠标悬浮在上的链接 蓝色 */
  a:active {color: #000008;} /* 鼠标点中激活链接 蓝色 */


</style>
