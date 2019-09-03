<!--
&lt;!&ndash;<template>&ndash;&gt;
  &lt;!&ndash;<div id="app">&ndash;&gt;
    &lt;!&ndash;<el-container>&ndash;&gt;
      &lt;!&ndash;<el-header>&ndash;&gt;
        &lt;!&ndash;<el-row type="flex" class="row-bg" justify="space-around">&ndash;&gt;
          &lt;!&ndash;<el-col :span="3">&ndash;&gt;
            &lt;!&ndash;<div>{{timeShow}}</div>&ndash;&gt;
          &lt;!&ndash;</el-col>&ndash;&gt;
          &lt;!&ndash;<el-col :span="2">&ndash;&gt;
            &lt;!&ndash;<div v-if="hours<12">早上好！</div>&ndash;&gt;
            &lt;!&ndash;<div v-if="hours>=12 && hours<18">中午好！</div>&ndash;&gt;
            &lt;!&ndash;<div v-if="hours>=18">晚上好！</div>&ndash;&gt;
          &lt;!&ndash;</el-col>&ndash;&gt;
          &lt;!&ndash;<el-col :span="10"  offset="3"><div class="biaoti">欢迎来到DHU商品购物平台！</div></el-col>&ndash;&gt;
          &lt;!&ndash;<el-col :span="2" offset="3">&ndash;&gt;
            &lt;!&ndash;<el-button type="primary" v-on:click="initLogin">登陆</el-button>&ndash;&gt;
            &lt;!&ndash;<el-dialog&ndash;&gt;
              &lt;!&ndash;:visible.sync="dialogVisible"&ndash;&gt;
              &lt;!&ndash;width="30%"&ndash;&gt;
              &lt;!&ndash;title="Welcome！"&ndash;&gt;
              &lt;!&ndash;:before-close="handleClose">&ndash;&gt;
              &lt;!&ndash;<el-input v-model="cId" placeholder="请输入用户号" style="margin-top: 10px;"></el-input>&ndash;&gt;
              &lt;!&ndash;<el-input v-model="cPassword" placeholder="请输入密码" style="margin-top: 10px;"></el-input>&ndash;&gt;
              &lt;!&ndash;<span slot="footer" class="dialog-footer">&ndash;&gt;
             &lt;!&ndash;<el-button @click="dialogVisible = false">取 消</el-button>&ndash;&gt;
              &lt;!&ndash;<el-button type="primary" @click="dialogVisible = false">确 定</el-button>&ndash;&gt;
             &lt;!&ndash;</span>&ndash;&gt;
            &lt;!&ndash;</el-dialog>&ndash;&gt;
          &lt;!&ndash;</el-col>&ndash;&gt;
          &lt;!&ndash;<el-col :span="2">&ndash;&gt;
            &lt;!&ndash;<router-link to="./Registe">注册</router-link>&ndash;&gt;
          &lt;!&ndash;</el-col>&ndash;&gt;


          &lt;!&ndash;<el-col :span="2" offset="0">&ndash;&gt;
            &lt;!&ndash;<el-dropdown>&ndash;&gt;
              &lt;!&ndash;<span class="el-dropdown-link">&ndash;&gt;
                &lt;!&ndash;个人中心<i class="el-icon-arrow-down el-icon&#45;&#45;right"></i>&ndash;&gt;
              &lt;!&ndash;</span>&ndash;&gt;
              &lt;!&ndash;<el-dropdown-menu slot="dropdown">&ndash;&gt;
                &lt;!&ndash;<router-link to="/Shopcart">&ndash;&gt;
                &lt;!&ndash;<el-dropdown-item>购物车</el-dropdown-item>&ndash;&gt;
                &lt;!&ndash;</router-link>&ndash;&gt;
                &lt;!&ndash;<el-dropdown-item>订单列表</el-dropdown-item>&ndash;&gt;
              &lt;!&ndash;</el-dropdown-menu>&ndash;&gt;
            &lt;!&ndash;</el-dropdown>&ndash;&gt;
          &lt;!&ndash;</el-col>&ndash;&gt;
        &lt;!&ndash;</el-row>&ndash;&gt;
      &lt;!&ndash;</el-header>&ndash;&gt;
      &lt;!&ndash;<el-main>&ndash;&gt;
        &lt;!&ndash;<router-view></router-view>&ndash;&gt;
      &lt;!&ndash;</el-main>>&ndash;&gt;


      &lt;!&ndash;<el-footer>&ndash;&gt;
        &lt;!&ndash;东华出品 必属精品&ndash;&gt;
      &lt;!&ndash;</el-footer>&ndash;&gt;
    &lt;!&ndash;</el-container>&ndash;&gt;
  &lt;!&ndash;</div>&ndash;&gt;
&lt;!&ndash;</template>&ndash;&gt;

&lt;!&ndash;<script>&ndash;&gt;
&lt;!&ndash;export default {&ndash;&gt;
  &lt;!&ndash;name: 'App',&ndash;&gt;
  &lt;!&ndash;data() {&ndash;&gt;
    &lt;!&ndash;return {&ndash;&gt;
      &lt;!&ndash;hours: new Date().getHours(),&ndash;&gt;
      &lt;!&ndash;timeShow: new Date().getFullYear() + "年" + (new Date().getMonth() + 1) + "月" + (new Date().getDay()) + "日",&ndash;&gt;
      &lt;!&ndash;dialogVisible: false,&ndash;&gt;
      &lt;!&ndash;cId:null,&ndash;&gt;
      &lt;!&ndash;cPassword:null,&ndash;&gt;
      &lt;!&ndash;customerData:[&ndash;&gt;
      &lt;!&ndash;]&ndash;&gt;

    &lt;!&ndash;}&ndash;&gt;
  &lt;!&ndash;},&ndash;&gt;
  &lt;!&ndash;methods:{&ndash;&gt;
    &lt;!&ndash;initLogin(){&ndash;&gt;
      &lt;!&ndash;this.dialogVisible=true;&ndash;&gt;
    &lt;!&ndash;}&ndash;&gt;
  &lt;!&ndash;}&ndash;&gt;
&lt;!&ndash;}&ndash;&gt;
&lt;!&ndash;</script>&ndash;&gt;

&lt;!&ndash;<style>&ndash;&gt;
&lt;!&ndash;#app {&ndash;&gt;
  &lt;!&ndash;font-family: 'Avenir', Helvetica, Arial, sans-serif;&ndash;&gt;
  &lt;!&ndash; -webkit-font-smoothing: antialiased;&ndash;&gt;
  &lt;!&ndash; -moz-osx-font-smoothing: grayscale;&ndash;&gt;
  &lt;!&ndash;text-align: center;&ndash;&gt;
  &lt;!&ndash;color: #2c3e50;&ndash;&gt;
  &lt;!&ndash;margin-top: 15px;&ndash;&gt;
&lt;!&ndash;}&ndash;&gt;
&lt;!&ndash;.el-header, .el-footer {&ndash;&gt;
  &lt;!&ndash;background-color: #B3C0D1;&ndash;&gt;
  &lt;!&ndash;color: #333;&ndash;&gt;
  &lt;!&ndash;text-align: center;&ndash;&gt;
  &lt;!&ndash;line-height: 60px;&ndash;&gt;
&lt;!&ndash;}&ndash;&gt;

&lt;!&ndash;.biaoti{&ndash;&gt;
  &lt;!&ndash;font-size: 30px;&ndash;&gt;
&lt;!&ndash;}&ndash;&gt;

&lt;!&ndash;.el-header, .el-footer {&ndash;&gt;
  &lt;!&ndash;background-color: #B3C0D1;&ndash;&gt;
  &lt;!&ndash;color: #333;&ndash;&gt;
  &lt;!&ndash;text-align: center;&ndash;&gt;
  &lt;!&ndash;line-height: 60px;&ndash;&gt;
&lt;!&ndash;}&ndash;&gt;

&lt;!&ndash;.el-aside {&ndash;&gt;
  &lt;!&ndash;background-color: #D3DCE6;&ndash;&gt;
  &lt;!&ndash;color: #333;&ndash;&gt;
  &lt;!&ndash;text-align: center;&ndash;&gt;
  &lt;!&ndash;line-height: 200px;&ndash;&gt;
&lt;!&ndash;}&ndash;&gt;

&lt;!&ndash;.el-main {&ndash;&gt;
  &lt;!&ndash;background-color: #E9EEF3;&ndash;&gt;
  &lt;!&ndash;color: #333;&ndash;&gt;
  &lt;!&ndash;text-align: center;&ndash;&gt;
  &lt;!&ndash;line-height: 160px;&ndash;&gt;
&lt;!&ndash;}&ndash;&gt;

&lt;!&ndash;body > .el-container {&ndash;&gt;
  &lt;!&ndash;margin-bottom: 40px;&ndash;&gt;
&lt;!&ndash;}&ndash;&gt;

&lt;!&ndash;.el-container:nth-child(5) .el-aside,&ndash;&gt;
&lt;!&ndash;.el-container:nth-child(6) .el-aside {&ndash;&gt;
  &lt;!&ndash;line-height: 260px;&ndash;&gt;
&lt;!&ndash;}&ndash;&gt;

&lt;!&ndash;.el-container:nth-child(7) .el-aside {&ndash;&gt;
  &lt;!&ndash;line-height: 320px;&ndash;&gt;
&lt;!&ndash;}&ndash;&gt;

&lt;!&ndash;/*分割线css样式*/&ndash;&gt;
&lt;!&ndash;#three_dot_view, {&ndash;&gt;
  &lt;!&ndash;width: 330px;&ndash;&gt;
  &lt;!&ndash;height: 20px;&ndash;&gt;
&lt;!&ndash;}&ndash;&gt;

&lt;!&ndash;.gray_line {&ndash;&gt;
  &lt;!&ndash;background: #e5e5e5;&ndash;&gt;
  &lt;!&ndash;width:1200px;&ndash;&gt;
  &lt;!&ndash;height: 3px;&ndash;&gt;
  &lt;!&ndash;float:left;&ndash;&gt;
  &lt;!&ndash;margin: 10px 5px;&ndash;&gt;
&lt;!&ndash;}&ndash;&gt;

&lt;!&ndash;.el-carousel__item h3 {&ndash;&gt;
  &lt;!&ndash;color: #475669;&ndash;&gt;
  &lt;!&ndash;font-size: 18px;&ndash;&gt;
  &lt;!&ndash;opacity: 0.75;&ndash;&gt;
  &lt;!&ndash;line-height: 300px;&ndash;&gt;
  &lt;!&ndash;margin: 0;&ndash;&gt;
&lt;!&ndash;}&ndash;&gt;

&lt;!&ndash;.el-carousel__item:nth-child(2n) {&ndash;&gt;
  &lt;!&ndash;background-color: #99a9bf;&ndash;&gt;
&lt;!&ndash;}&ndash;&gt;

&lt;!&ndash;.el-carousel__item:nth-child(2n+1) {&ndash;&gt;
  &lt;!&ndash;background-color: #d3dce6;}&ndash;&gt;
&lt;!&ndash;</style>&ndash;&gt;


<template>
  <div id="app">
    <router-view></router-view>
  </div>
</template>
<script>
  export default {
    name: "App"
  };
</script>
<style>
  body {
    margin: 0;
    padding: 0;
  }
  .el-header {
    background-color: #b3c0d1;
    color: #333;
  }
  .mainContainer {
    display: flex;
    min-height: 800px;
  }
  .el-aside {
    background-color: #d3dce6;
    flex: 1;

  }

  /*.el-main {*/
    /*background-color: #ffffff;*/
    /*flex: 8;*/
  /*}*/
</style>
-->


<template>
  <router-view></router-view>
</template>

<script>
  export default {
    name: "App"
  };
</script>

<style>
  #app {
    font-family: 'Avenir', Helvetica, Arial, sans-serif;
    -webkit-font-smoothing: antialiased;
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
    margin-top: 15px;
  }

</style>
