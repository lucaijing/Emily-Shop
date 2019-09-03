<template>
  <div>
    <el-container>
      <el-aside width="20%">
        <el-menu
          class="el-menu-vertical-demo"
          background-color="#545c64"
          text-color="#fff"
          active-text-color="#ffd04b">
          <el-menu-item index="1" >
            <i class="el-icon-menu"></i>
            <span slot="title"><a href="http://localhost:8081/#/userManage">用户管理</a></span>
          </el-menu-item>
          <el-menu-item index="2">
            <i class="el-icon-menu"></i>
            <span slot="title"><a href="http://localhost:8081/#/productManage">商品管理</a></span>
          </el-menu-item>
          <el-menu-item index="3">
            <i class="el-icon-menu"></i>
            <span slot="title"><a href="http://localhost:8081/#/storeManage">仓库管理</a></span>
          </el-menu-item>
          <el-submenu index="4">
            <template slot="title"><i class="el-icon-menu"></i>订单管理</template>
            <el-menu-item index="4-1"><a href="http://localhost:8081/#/inManage">入货单</a></el-menu-item>
            <el-menu-item index="4-2"><a href="http://localhost:8081/#/outManage">历史订单</a></el-menu-item>
            <el-menu-item index="4-3"><a href="http://localhost:8081/#/ordersManage">订单</a></el-menu-item>
          </el-submenu>
        </el-menu>
        <div style="margin-top: 40px"></div>
        <el-button type="text" style="background-color: cornflowerblue"><router-link to="/index">退出管理员</router-link></el-button>
      </el-aside>
        <div class="line"></div>
        <el-container>
          <el-main>
            <router-view v-if="isReloadAlive"></router-view>
          </el-main>
        </el-container>

    </el-container>
  </div>

  <!--<div id="Home2">
    <el-container>
      <el-main style="height: 600px">
        <el-menu :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect">
          <el-menu-item index="1" ><router-link to="/userManage">用户管理</router-link></el-menu-item>
          <el-menu-item index="2"><router-link to="/productManage">商品管理</router-link></el-menu-item>
          &lt;!&ndash;<el-menu-item index="3"><a href="https://www.ele.me" target="_blank">仓库管理</a></el-menu-item>&ndash;&gt;
          <el-menu-item index="3"><router-link to="/storeManage">仓库管理</router-link></el-menu-item>
          <el-submenu index="4">
            <template slot="title">订单管理</template>
            <el-menu-item index="4-1"><router-link to="/inManage">入货单</router-link></el-menu-item>
            <el-menu-item index="4-2"><router-link to="/outManage">出货单</router-link></el-menu-item>
            <el-menu-item index="4-3"><router-link to="/ordersManage">订单</router-link></el-menu-item>
          </el-submenu>
          <el-menu-item>
            <el-button type="text" style="background-color: white"><router-link to="/">退出管理员</router-link></el-button>
          </el-menu-item>
        </el-menu>
        <div class="line"></div>
        <el-container>
          <el-main>
            <router-view v-if="isReloadAlive"></router-view>
          </el-main>
        </el-container>
      </el-main>
    </el-container>
  </div>-->
</template>

<script>
    export default {
        name: "Home2",
        provide() {

        return {
/*
          reload: this.reload
*/
        }
      },
      data() {
        return {
          isReloadAlive: true,
        }
      },
      methods:{
/*        reload() {
          this.isReloadAlive = false;
          this.$nextTick(function(){
            this.isReloadAlive = true;
          })
        },*/
      }
    }
</script>

<style scoped>

  .el-header, .el-footer {
    background-color: #e4e4e4;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  .el-aside {
    background-color: #545c64;
    color: #333;
    text-align: center;
    line-height: 200px;
  }

  .el-main {
    background-color: #ffffff;
    color: #333;
    text-align: center;
    line-height: 60px;
  }

  body > .el-container {
    margin-bottom: 40px;
  }

  .el-container:nth-child(5) .el-aside,
  .el-container:nth-child(6) .el-aside {
    line-height: 60px;
  }

  .el-container:nth-child(7) .el-aside {
    line-height: 320px;
  }

  a:link{text-decoration-line: none}
  a:visited{color: #ffffff;}
  a:hover {color: #ff4466;}   /* 鼠标悬浮在上的链接 蓝色 */
 a:active {color: #ffffff;} /* 鼠标点中激活链接 蓝色 */
</style>


