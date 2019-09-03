<template>
  <el-container>
    <el-main>
      <el-row>
      <el-col :span="6" >
        <div class="grid-content bg-purple">
          <el-input @input="valueChanged" v-model="queriedInOId" placeholder="请输入您想要查询的入货单号" ></el-input>
        </div>
      </el-col>
      <el-col :span="2" offset="1">
        <el-button type="primary" icon="el-icon-search" @click="queryByInoId">搜索</el-button>
      </el-col>
      <el-col :span="2" offset="1">
        <el-button type="primary" icon="el-icon-edit" @click="createBrandStoreVisible=true">添加入货单</el-button>
        <el-dialog
          :visible.sync="createBrandStoreVisible"
          width="50%">
          <!--:before-close="handleClose">-->
          <el-form  :label-position="labelPosition" label-width="80px" :model="create">
            <!--入货单号均为默认-->
            <el-form-item label="商品号">
              <el-input v-model="create.proId"></el-input>
            </el-form-item>
            <el-form-item label="进货数量">
              <el-input v-model="create.bproNumber"></el-input>
            </el-form-item>
            <el-form-item label="进货单价">
              <el-input v-model="create.bproPrice"></el-input>
            </el-form-item>
            <el-button type="primary" size="small" @click="calculateSum">计算总金额</el-button>
            <el-form-item label="总金额">
              <el-input v-model="create.bsSum"></el-input>
            </el-form-item>
            <el-form-item label="品牌号">
              <el-input v-model="create.brand.braId"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
             <el-button @click="createProductVisible = false">取 消</el-button>
              <el-button type="primary" @click="createInorder">提交</el-button>
             </span>
        </el-dialog>

      <!--</el-col>-->
      <!--<el-col :span="6" offset="1">-->
        <!--<el-button type="primary">显示全部入货单</el-button>-->
        <!--<el-button type="primary" icon="el-icon-delete">删除入货单</el-button>-->
      </el-col>
    </el-row>

      <h1 align="left">正在进行的入货单</h1>
      <el-table
        :data="inData"
        style="width: 100%">
        <el-table-column
          fixed
          prop="bsId"
          label="进货单号">
        </el-table-column>
        <el-table-column
          prop="proId"
          label="商品号">
        </el-table-column>
        <el-table-column
          prop="bproNumber"
          label="进货数量">
        </el-table-column>
        <el-table-column
          prop="bproPrice"
          label="进单价">
        </el-table-column>
        <el-table-column
          prop="bsSum"
          label="总金额">
        </el-table-column>
        <el-table-column
          label="操作">
          <el-button type="primary" @click="finished">完成</el-button>
        </el-table-column>
      </el-table>


      <h1 align="left">已完成的入货单</h1>
      <el-table
        :data="finishedData"
        style="width: 100%">
        <el-table-column
          fixed
          prop="bsId"
          label="进货单号">
        </el-table-column>
        <el-table-column
          prop="proId"
          label="商品号">
        </el-table-column>
        <el-table-column
          prop="bproNumber"
          label="进货数量">
        </el-table-column>
        <el-table-column
          prop="bproPrice"
          label="进单价">
        </el-table-column>
        <el-table-column
          prop="bsSum"
          label="总金额">
        </el-table-column>
      </el-table>
    </el-main>
  </el-container>
</template>

<script>
    export default {
        name: "InManage",
      inject:["reload"],
    data() {
        return {
          createBrandStoreVisible:false,
          labelPosition: 'right',
          queriedInOId:null,
          inData:[],
          finishedData:[],
          boNumber:null,
          create: {
            store:{
              sId:null,
            },
            brand:{
              braId:null,
            },
            proId:null,

            bproNumber:null,
            bproPrice:null,
            bsSum:null,
            bsIsdone:0,//bsIsdone 默认为0 ，即为未完成状态
          }
        }

      },
      mounted() {
        console.log("loading data.");
        this.$ajax({
          method: "get",
          url: "http://localhost:8080/BrandStore/findByBsIsdone/bsIsdone=0"
        }).then(response => {
          console.log("123123123213")
          console.log(response.data);
          //for (let i = 0; i < response.data.length; i++) {
          //this.userData=push(response.data[i]);
          this.inData=response.data;
          //}
        })

        this.$ajax({
          method: "get",
          url: "http://localhost:8080/BrandStore/findByBsIsdone/bsIsdone=1"
        }).then(response => {
          console.log("finishedData")
          console.log(response.data);
          //for (let i = 0; i < response.data.length; i++) {
          //this.userData=push(response.data[i]);
          this.finishedData=response.data;
          //}
        })
      },

      methods:{
        calculateSum(){
          this.createProductVisible =true;
          this.create.bsSum=this.create.bproNumber*this.create.bproPrice;
          console.log(this.create.bsSum);
        },
        alterCreateSuccess() {
          this.$message({
            message: "恭喜你，添加成功",
            type: "success"
          });
          this.reload();
        },
        alterCreateError() {
          this.$message({
            message: "抱歉，添加失败",
            type: "error"
          });
          this.reload();
        },
        alterQuerySuccess() {
          this.$message({
            message: "恭喜你，查询成功",
            type: "true"
          });
        },
        alterQueryError() {
          this.$message({
            message: "抱歉，查询失败",
            type: "error"
          });
          //this.reload();
        },


        // 当查询框被删减为空时自动查询所有数据
        valueChanged() {
          if (this.queriedInOId === "") {
            console.log("当前为空字符串");
            this.queryByInoId();
          }
        },

        //根据订单号查询
        queryByInoId()
        {
          console.log(this.queriedInOId);
          if (this.queriedInOId==='')
          {
            console.log("空字符");
            this.reload();
            return;
          }
          this.$ajax.get("http://localhost:8080/BrandStore/findByBsId/bsId="+this.queriedInOId).then(response => {
            console.log(response);
            if(response.data.bsIsdone != null  )
            {
              //如果bsIsdone 为 0 ，即为未完成，则在未完成栏显示。
              //如果bsIsdone为1，即为已完成，则在已完成栏显示
              console.log("success")
              console.log(response.data);
              console.log("woaibeijign")
              console.log(response.data.oIsdone);//1 or 0
              console.log("success");
              if(response.data.bsIsdone===0)
              {this.inData=[];
                this.finishedData=[];//两个表单都清空
                console.log("userDate")
                console.log(this.inData)
                this.inData.push(response.data);
                // this.userData=response.data;
                console.log(this.inData);
                this.alterQuerySuccess();}
              if(response.data.bsIsdone===1)
              {
                this.inData=[];
                this.finishedData=[];
                console.log("finishedData")
                console.log(this.finishedData)
                this.finishedData.push(response.data);
                // this.userData=response.data;
                console.log(this.finishedData);
                this.alterQuerySuccess();
              }
            }
            else{
              console.log(response.data)
              console.log("failed!")
              this.alterQueryError()
            }
          }).catch((err) => {
            //抛出异常，关闭数据库
            console.log("query error!" + err);
            this.alterQueryError();
          })
        },

        //添加入货单
        createInorder(){
          this.$ajax.get("http://localhost:8080/Product/findByProId/proId="+this.create.proId).then(response=>{
            this.create.store.sId=response.data.store.sId;
            let data = this.create;
            console.log('创建的入货单为')
            console.log(data);
            this.$ajax.post("http://localhost:8080/BrandStore/CreateBS", JSON.stringify(data),
              {
                headers: { 'Content-Type': 'application/json;charset=UTF-8' }
              }
            ).then(response => {
              console.log('创入货单')
              this.createBrandStoreVisible = false;
              if (response.data != null) {
                this.alterCreateSuccess();
              } else {
                this.alterCreateError();
              }
            }).catch(() => {
              console.log("save error");
              this.alterCreateError();
            });
          })
        },


        //未完成出货单改为已完成
        finished()
        {
          console.log(this.inData[0].proNumber);
          console.log(this.inData[0].oId);
          this.$ajax({
            method: "get",
            url: "http://localhost:8080/BrandStore/UpdateBsIsdone/bsIsdone=1bsId="+this.inData[0].bsId
          }).then(response => {
            console.log("123123123213");
            console.log(response.data);
            console.log("入货单"+this.inData[0].bsId+"已完成");

            this.$message({
              message: "入货单" + this.inData[0].bsId + "已完成!！！",
              type: "true"
            });
            this.reload();
          });

          //找到product列表里的bnumber
          this.$ajax({
            method: "get",
            url: "http://localhost:8080/Product/findByProId/proId="+this.inData[0].proId
          }).then(response => {
            console.log("product列表里的吧number为：");
            console.log(response.data.proNumber);
            this.boNumber=response.data.proNumber;
            this.changebnumber();

            let sId=response.data.store.sId;
            console.log("zkjalkjsaflasfjaksdjflasjflksdf");
            console.log(sId);
            this.$ajax.get("http://localhost:8080/Store/findBySId/sId="+sId).then(res=>{
              let Store=res.data;
              let S=res.data.sNumber;

              //改变store列表里的snumber
              this.$ajax({
                method: "get",
                url: "http://localhost:8080/Store/UpdateSNumber/sNumber="+(S+this.inData[0].bproNumber)+"sId="+Store.sId
              }).then(response => {
                console.log("改变store表格里的snumber");

              })
            })


          })





        },

        changebnumber()
        {
          //改变product列表里的bnumber
          this.$ajax({
            method: "get",
            url: "http://localhost:8080/Product/UpdateNumber/proNumber="+(this.boNumber+this.inData[0].bproNumber)+"proId="+this.inData[0].proId
          }).then(response => {
            console.log("改变product表格里的bnumber")
            console.log(this.inData[0].bproNumber);
          })
        },
      },
    }
</script>

<style scoped>
  h1 {
    display: block;
    margin: 30px;
    color: #FFa631;
    align:left
  }

</style>
