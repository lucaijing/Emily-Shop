<template>
    <div id="Registe">
      <el-container>
          <el-tabs type="border-card" style="width: 100%;padding-left: 5%;padding-right: 5%">
            <el-tab-pane label="用户注册">
          <p>
            <el-breadcrumb separator="/" style="float:left">
              <el-breadcrumb-item :to="{ path: '/index' }" >首页</el-breadcrumb-item>
              <el-breadcrumb-item>注册</el-breadcrumb-item>
            </el-breadcrumb></p>
              <br>
          <!--<div id='three_dot_view'>
            <label class="gray_line"></label>
          </div>--><!--画一条分割线-->
          <!--<p style="align:center">
          <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="100px" class="demo-ruleForm">
            <el-form-item label="用户号" prop="cId">
              <el-input type="text" v-model="ruleForm2.cId" :disabled="true"></el-input>
            </el-form-item>
            <el-form-item label="真实姓名" prop="cName">
              <el-input type="text" v-model="ruleForm2.cName" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="密码">
              <el-input type="password" v-model="ruleForm2.pass" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码">
              <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="收货地址" prop="cAddress">
              <el-input type="text" v-model="ruleForm2.cAddress" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="联系电话" prop="cPhone">
              <el-input type="text" v-model="ruleForm2.cPhone" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="submitForm('ruleForm2')">提交</el-button>
              <el-button @click="resetForm('ruleForm2')">重置</el-button>
            </el-form-item>
          </el-form>
          </p>-->

          <el-form :model="ruleForm2" status-icon :rules="rules2" ref="ruleForm2" label-width="100px" class="demo-ruleForm">
            <el-form-item label="用户名" prop="cName" :rules="[{required: true,message:'请输入用户名',trigger:'blur'}]">
              <el-input v-model="ruleForm2.cName" ></el-input>
            </el-form-item>
            <el-form-item label="密码" prop="cPassword" :rules="[{required: true,message:'请输入密码',trigger:'blur'}]">
              <el-input type="password" v-model="ruleForm2.cPassword" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="确认密码" prop="checkPass" required="true">
              <el-input type="password" v-model="ruleForm2.checkPass" auto-complete="off"></el-input>
            </el-form-item>
            <el-form-item label="收货地址" prop="cAddress" :rules="[{required:true,message:'请输入收货地址',trigger:'blur'}]">
              <el-input v-model="ruleForm2.cAddress"></el-input>
            </el-form-item>
            <el-form-item label="联系电话" prop="cPhone" :rules="[{required: true,message:'请输入联系电话'},{type:'number',message:'电话必须为数字值',min:10}]">
              <el-input v-model.number="ruleForm2.cPhone"></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="CreateCustomer">提交</el-button>
              <el-button @click="resetForm('ruleForm2')">重置</el-button>
            </el-form-item>
          </el-form>
          </el-tab-pane>
        </el-tabs>
      </el-container>
    </div>
</template>

<script>
    export default {
      data() {
        var validatePass = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请输入密码'));
          } else {
            if (this.ruleForm2.checkPass !== '') {
              this.$refs.ruleForm2.validateField('checkPass');
            }
            callback();
          }
        };
        var validatePass2 = (rule, value, callback) => {
          if (value === '') {
            callback(new Error('请再次输入密码'));
          } else if (value !== this.ruleForm2.cPassword) {
            callback(new Error('两次输入密码不一致!'));
          } else {
            callback();
          }
        };
        return {

          ruleForm2: {
            cPassword:'',
            checkPass: '',
            cName:'',
            cAddress:'',
            cPhone:''
          },
          rules2: {
            cPassword: [
              { validator: validatePass, trigger: 'blur' }
            ],
            checkPass: [
              { validator: validatePass2, trigger: 'blur' }
            ],
/*            cname:[
              {
                required:true,message:"请输入用户名",trigger: 'blur'
              }
            ]*/
/*            age: [
              { validator: checkAge, trigger: 'blur' }
            ]*/
          }
        };
      },
      mounted(){

      },
      methods: {
        resetForm(formName) {
          this.$refs[formName].resetFields();
        },

        CreateCustomer(){
          let data=this.ruleForm2;
          console.log(data);
          this.$ajax.post("http://localhost:8080/Customer/CreateC",JSON.stringify(data),{
            headers:{'Content-Type': 'application/json;charset=UTF-8'}
          }).then(response=>{
            console.log(response.data);
            if(response.data){
              this.$message({
                message:"用户注册成功",
                type:"success"
              })
              window.location="http://localhost:8081/#/index";
            }
            else if(response.data=="Already_Exist"){
              this.$message({
                message:"该用户名已经被注册使用，请更换用户名",
                type:"error"
              })
            }
            else{
              this.$message({
                message:"用户注册失败，请稍后再试",
                type:"error"
              })
            }
          })
        }
      }
    }
</script>

<style scoped>

</style>
