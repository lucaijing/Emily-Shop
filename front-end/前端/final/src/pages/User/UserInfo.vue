<template>
    <el-container>
      <el-main>
        <el-tabs type="border-card">
          <br>
        <el-row>
          <el-col>
            <div>
              <el-breadcrumb separator="/">
                <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                <el-breadcrumb-item>个人信息</el-breadcrumb-item>
              </el-breadcrumb>
            </div>
          </el-col>
        </el-row>

          <br>
          <br>

          <el-tab-pane label="个人信息设置" name="first">

            <el-form ref="form" label-width="80px">

              <el-form-item label="用户名">
                <el-input  :disabled="true" v-model="cName"></el-input>
              </el-form-item>
              <el-form-item label="收货地址">
                <el-input v-model="cAddress"></el-input>
              </el-form-item>
              <el-form-item label="联系电话">
                <el-input v-model="cPhone"></el-input>
              </el-form-item>
              <el-form-item>
               <!-- <el-button>取消</el-button>-->
                <el-button type="primary" @click="UpdateCustomer">保存</el-button>
              </el-form-item>

            </el-form>
          </el-tab-pane>

          <el-tab-pane label="密码设置" name="second">

            <el-form ref="form" label-width="80px" >
              <el-form-item label="用户名">
                <el-input  :disabled="true" v-model="cName"></el-input>
              </el-form-item>
              <el-form-item label="原始密码">
                <el-input v-model="cPassword" type="password"></el-input>
              </el-form-item>
              <el-form-item label="新密码">
                <el-input v-model="cNewPassword" type="password"></el-input>
              </el-form-item>
              <el-form-item label="确认密码">
                <el-input v-model="cNewPasswordSecond" type="password"></el-input>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="UpdatePassword">保存</el-button>
              </el-form-item>
            </el-form>
          </el-tab-pane>
        </el-tabs>
      </el-main>
    </el-container>
</template>

<script>
    export default {
        name: "UserInfo",
        data(){
          return{
            activeName: 'first',
            cId:null,
            thisUser:null,

              cName:null,
              cAddress:null,
              cPhone:null,

              cPassword:null,
              cNewPassword:null,
              cNewPasswordSecond:null,



          }
      },
      mounted() {
          //加载时获取当前用户信息
          this.$ajax.get("http://localhost:8080/Customer/getCurrentCustomer").then(response=>{
            this.thisUser=response.data;
            this.cName=this.thisUser.cName;
            this.cId=this.thisUser.cId;
            this.cAddress=this.thisUser.cAddress;
            this.cPhone=this.thisUser.cPhone;

          })
      },
      methods:{
        handleClick(tab, event) {
          console.log(tab, event);
        },
          UpdateCustomer(){
            console.log(this.cAddress);
            this.$ajax.get("http://localhost:8080/Customer/UpdateCustomer/cAddress="+this.cAddress+"cPhone="+this.cPhone+"cId="+this.cId).then(response=>{
              console.log(response.data);
              if(response.data){
                this.$message({
                  message:"基本信息已修改成功！",
                  type:"success"
                });
                this.cAddress=null;
                this.cPhone=null;
              }
              else{
                this.$message({
                  message:"修改失败，请稍后重试！",
                  type:"error"
                })
              }

            })


          },
          UpdatePassword(){
            this.$ajax.get("http://localhost:8080/Customer/UpdateCPassword/First_cPassword="+this.cNewPassword+
              "Second_cPassword="+this.cNewPasswordSecond+"cId="+this.cId+"Initial_cPassword="+this.cPassword).then(response=>{
                console.log(response.data);
                if(response.data=="Initial_false"){
                  this.$message({
                    message:"密码输入错误，请重新输入！",
                    type:"error"
                  })
                }
                else if(response.data=="Second_false"){
                  this.$message({
                    message:"两遍输入密码不一致！",
                    type:"error"
                  })
                }
                else if(response.data){
                  this.$message({
                    message:"密码修改成功！",
                    type:"success"
                  });
                  this.cNewPassword=null;
                  this.cNewPasswordSecond=null;
                  this.cPassword=null;
                }
                else{
                  this.$message({
                    message:"密码修改错误，请稍后重试！",
                    type:"error"
                  })
                }

            })

          },
      }
    }
</script>

<style scoped>

</style>
