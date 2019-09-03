<template>
  <div id="Login">
  <el-container>
    <el-main style="background-color: #E9EEF3">
      <div style="position: fixed; top: 30%; left: 30%;width: 400px">
    <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
      <el-form-item label="用户名" prop="aName">
        <el-input v-model="ruleForm.aName"></el-input>
      </el-form-item>
      <el-form-item label="密码" prop="aPassword">
        <el-input v-model="ruleForm.aPassword"></el-input>
      </el-form-item>

      <el-form-item>
        <el-button type="primary" @click="submitForm('ruleForm')">登录</el-button>
        <el-button @click="resetForm('ruleForm')">重置</el-button>
      </el-form-item>
    </el-form>
  </div>
    </el-main>
  </el-container>
  </div>
</template>

<script>
    export default {
        name: "Login",
      data() {
        return {
          ruleForm: {
            aName: '',
            aPassword: ''
          },
          rules: {
            aName: [
              { required: true, message: '请输入用户名', trigger: 'blur' }
            ],
            aPassword: [
              { required: true, message: '请输入密码', trigger: 'blur' }
            ]
          }
        };
      },
      methods: {
        submitForm(formName) {
          this.$refs[formName].validate((valid) => {
            if (valid) {
              alert('submit!');
            } else {
              console.log('error submit!!');
              return false;
            }
          });
        },
        resetForm(formName) {
          this.$refs[formName].resetFields();
        }
      }
    }
</script>

<style scoped>

</style>
