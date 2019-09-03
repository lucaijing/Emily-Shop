<template>
<div>
  <el-container style="margin-top: -20px">
    <el-header style="margin-top: -40px;font-size: 25px"><h1>添加商品</h1></el-header>
    <el-main>
      <el-container>
        <el-aside>
          <div>
            <form action="http://localhost:8080/Product/Picture" method="post" enctype="multipart/form-data">
              <el-upload :file-list="filelist" :auto-upload="false" >
                <el-button slot="trigger">上传图片</el-button>
                <input type="submit" value="提交" style="width: 50px;height: 38px"></input>
              </el-upload>
            </form>
            <img :src="create.proPic" alt="请先上传图片" width="200px" style="height: 200px">
          </div>

          <el-button type="primary" @click="createProduct">提交信息</el-button>
        </el-aside>
        <el-container style="margin-left: -20px">
          <el-main>
            <el-form label-width="80px" :model="create">

              <el-form-item label="商品名">
                <el-input v-model="create.proName"></el-input>
              </el-form-item>
              <el-form-item label="仓库号">
                <el-input v-model="create.store.sId"></el-input>
              </el-form-item>
              <el-form-item label="类别">
                <el-input v-model="create.proType"></el-input>
              </el-form-item>
              <el-form-item label="品牌">
                <el-input v-model="create.proBraname"></el-input>
              </el-form-item>
              <el-form-item label="价格">
                <el-input v-model="create.proPrice"></el-input>
              </el-form-item>
              <el-form-item label="适用人群">
                <el-input v-model="create.proSkin"></el-input>
              </el-form-item>
              <el-form-item label="库存数量">
                <el-input v-model="create.proNumber"></el-input>
              </el-form-item>
              <el-form-item label="商品简介">
                <el-input v-model="create.proDescription"></el-input>
              </el-form-item>
            </el-form>
          </el-main>
        </el-container>
      </el-container>
    </el-main>

  </el-container>
</div>
</template>

<script>
    export default {
        name: "addPic",
      data(){
          return{
            pic:this.$route.params.pic,
            labelPosition: 'right',
            create: {
              proId:null,
              proName:null,
              store:{
                sId:null,
                sAddress:null,
                sNumber:null,
              },
              proDescription:null,
              proType:null,
              proBraname:null,
              proPrice:null,
              proSkin:null,
              proNumber:null,
              proPic:this.$route.params.pic,
            },
          }
      },
      methods:{
        //新建商品
        createProduct(){
          let data = this.create;
          console.log(data);
          this.$ajax.post("http://localhost:8080/Product/CreateB", JSON.stringify(data),
            {
              headers: { 'Content-Type': 'application/json;charset=UTF-8' }
            }
          ).then(response => {
            console.log(response);
            if (response.data != null) {
              this.$message.success("成功");

              this.$ajax.get("http://localhost:8080/Store/AddSNumber/sNumb="+this.create.proNumber+"sId="+this.create.store.sId).then(response=>{
                console.log('库存'+this.create.store.sId+'数量增加了'+this.create.proNumber);
                window.location="http://localhost:8081/#/productManage";
              })

            } else {
              /*this.alterCreateError();*/
            }
          }).catch(() => {
            console.log("save error");
            /*this.alterCreateError();*/
          });

        },
      }
    }
</script>

<style scoped>

</style>
