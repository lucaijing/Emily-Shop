<template>
  <el-container>
    <el-main>
      <el-row>
      <el-col :span="6" >
        <div class="grid-content bg-purple">
          <el-input @input="valueChanged" v-model="queriedOId" placeholder="请输入您想要查询的出货单号" ></el-input>
        </div>
      </el-col>
      <el-col :span="2" offset="1">
        <el-button type="primary" icon="el-icon-search" @click="queryByoId">搜索</el-button>
      </el-col>
      <!--<el-col :span="2" offset="1">-->
        <!--<el-button type="primary" icon="el-icon-edit" @click="createCustomerStoreVisible=true">添加出货单</el-button>-->
        <!--<el-dialog-->
          <!--:visible.sync="createCustomerStoreVisible"-->
          <!--width="50%"-->
          <!--:before-close="handleClose">-->
          <!--<el-form :label-position="labelPosition" label-width="80px" :model="createCustomerStore">-->
            <!--<el-form-item label="商品号">-->
              <!--<el-input v-model="createCustomerStore.proId"></el-input>-->
            <!--</el-form-item>-->
            <!--<el-form-item label="购买数量">-->
              <!--<el-input v-model="createCustomerStore.ccNumber"></el-input>-->
            <!--</el-form-item>-->
            <!--<el-form-item label="出售单价">-->
              <!--<el-input v-model="createCustomerStore.proPrice"></el-input>-->
            <!--</el-form-item>-->
            <!--<el-form-item label="总售价">-->
              <!--<el-input v-model="createCustomerStore.ccSum"></el-input>-->
            <!--</el-form-item>-->
          <!--</el-form>-->
          <!--<span slot="footer" class="dialog-footer">-->
             <!--<el-button @click="createCustomerStoreVisible = false">取 消</el-button>-->
              <!--<el-button type="primary" @click="createCustomerStoreVisible = false">提交</el-button>-->
             <!--</span>-->
        <!--</el-dialog>-->


        <el-col :span="2" offset="1">
          <el-button type="primary" icon="el-icon-edit" @click="createBrandVisible=true">添加品牌</el-button>
          <el-dialog
            :visible.sync="createBrandVisible"
            width="50%">
            <el-form :label-position="labelPosition" label-width="100px" :model="createBrand1">
              <el-form-item label="品牌名称">
                <el-input v-model="createBrand1.pName"></el-input>
              </el-form-item>
              <el-form-item label="品牌地址">
                <el-input v-model="createBrand1.pAddress"></el-input>
              </el-form-item>
            </el-form>
            <span slot="footer" class="dialog-footer">
             <el-button @click="createBrandVisible = false">取 消</el-button>
              <el-button type="primary" @click="createBrand">提交</el-button>
             </span>
          </el-dialog>

        </el-col>
      <!--<el-col :span="6" offset="1">-->
        <!--<el-button type="primary">显示全部出货单</el-button>-->
        <!--<el-button type="primary" icon="el-icon-delete">删除出货单</el-button>-->
      <!--</el-col>-->
    </el-row>

      <!--正在进行的出货单-->
      <!--<el-table-->
        <!--:data="userData"-->
        <!--style="width: 100%"-->
        <!--height="250">-->
        <!--<el-table-column-->
          <!--fixed-->
          <!--prop="csId"-->
          <!--label="出货单号"-->
          <!--width="200">-->
        <!--</el-table-column>-->
        <!--<el-table-column-->
          <!--prop="proId"-->
          <!--label="商品号"-->
          <!--width="200">-->
        <!--</el-table-column>-->
        <!--<el-table-column-->
          <!--prop="ccNumber"-->
          <!--label="购买数量"-->
          <!--width="200">-->
        <!--</el-table-column>-->
        <!--<el-table-column-->
          <!--prop="proPrice"-->
          <!--label="销售单价"-->
          <!--width="200">-->
        <!--</el-table-column>-->
        <!--<el-table-column-->
          <!--prop="ccSum"-->
          <!--label="总售价"-->
          <!--width="200">-->
        <!--</el-table-column>-->
        <!--<el-table-column-->
          <!--label="操作"-->
          <!--width="200">-->
          <!--<el-button type="primary">完成</el-button>-->
        <!--</el-table-column>-->
      <!--</el-table>-->


      <el-table
        :data="finishedData"
        style="width: 100%">
        <el-table-column
          fixed
          prop="oId"
          label="出货单号">
        </el-table-column>
        <el-table-column
          prop="proId"
          label="商品号">
        </el-table-column>
        <el-table-column
          prop="oNumber"
          label="购买数量">
        </el-table-column>
        <!--<el-table-column-->
          <!--prop="cId"-->
          <!--label="用户号">-->
        <!--</el-table-column>-->
        <el-table-column
          prop="oSum"
          label="总金额">
        </el-table-column>
        <!--<el-table-column-->
          <!--prop="cAddress"-->
          <!--label="用户地址">-->
        <!--</el-table-column>-->

      </el-table>



      <!--已完成的出货单-->
      <!--<el-table-->
        <!--:data="outData"-->
        <!--style="width: 100%"-->
        <!--height="250">-->
        <!--<el-table-column-->
          <!--fixed-->
          <!--prop="csId"-->
          <!--label="出货单号"-->
          <!--width="200">-->
        <!--</el-table-column>-->
        <!--<el-table-column-->
          <!--prop="proId"-->
          <!--label="商品号"-->
          <!--width="200">-->
        <!--</el-table-column>-->
        <!--<el-table-column-->
          <!--prop="ccNumber"-->
          <!--label="购买数量"-->
          <!--width="200">-->
        <!--</el-table-column>-->
        <!--<el-table-column-->
          <!--prop="proPrice"-->
          <!--label="销售单价"-->
          <!--width="200">-->
        <!--</el-table-column>-->
        <!--<el-table-column-->
          <!--prop="ccSum"-->
          <!--label="总售价"-->
          <!--width="200">-->
        <!--</el-table-column>-->

      <!--</el-table>-->

    </el-main>
  </el-container>
</template>

<script>
    export default {
        name: "OutManage",
      inject:["reload"],
      data() {
        return {
          finishedData:[],
          createCustomerStoreVisible:false,
          createBrandVisible:false,
          labelPosition: 'right',
          queriedOId:null,
          outData:[],

          createCustomerStore: {
            proId:null,
            customer:{
              cId:null,
            },
            store:{
              sId:null,
            },
            cName:null,
            cAddress:null,
            ccNumber:null,
            proPrice:null,
            ccSum:null

          },
          createBrand1:{
            pId:null,
            pName:null,
            pAddress:null
          }
        }

      },

      //出货单就是已完成的订单
      mounted() {
        this.$ajax({
          method: "get",
          url: "http://localhost:8080/Orders/findByOIsdone/oIsdone=1"
        }).then(response => {
          console.log("finishedData")
          console.log(response.data);
          let ords=response.data;
          for(var i=0;i<ords.length;i++){
            let ord=ords[i];

            this.createCustomerStore.proId=ord.proId;
            this.createCustomerStore.proPrice=ord.oSum/ord.oNumber;
            this.createCustomerStore.cAddress=ord.cAddress;
            this.createCustomerStore.ccNumber=ord.oNumber;
            this.createCustomerStore.ccSum=ord.oSum;
            this.createCustomerStore.cName=ord.proName;////这里错了
            this.createCustomerStore.customer.cId=ord.cId;
            this.createCustomerStore.store.sId=1;////这里错了

            let data=this.createCustomerStore;
            this.$ajax.post("http://localhost:8080/CustomerStore/CreateCS",JSON.stringify(data),{
              headers:{'Content-Type': 'application/json;charset=UTF-8'}
            });
          }
          //for (let i = 0; i < response.data.length; i++) {
          //this.userData=push(response.data[i]);
          this.finishedData=response.data;
          //this.finishedData.push(response.data);
          //}
        })
      },

      methods:{
          createBrand(){
            let data=this.createBrand1;
            this.$ajax.post("http://localhost:8080/Brand/CreateP",JSON.stringify(data),{
              headers:{'Content-Type': 'application/json;charset=UTF-8'}
            }).then(response=>{
              this.$message({
                message:"添加成功",
                type:"success"
              })
              this.createBrandVisible=false;
            })
          },
        alterQuerySuccess() {
          this.$message({
            message: "恭喜你，查询成功",
            type: "success"
          });
        },
        alterQueryError()
        {
          this.$message({
            message: "抱歉，查询失败",
            type: "error"
          });
          //this.reload();
        },

        // 当查询框被删减为空时自动查询所有数据
        valueChanged() {
          if (this.queriedOId === "") {
            console.log("当前为空字符串");
            this.queryByoId();
          }
        },



        //根据出货单号查询
    queryByoId()
    {
      console.log(this.queriedOId);
      if (this.queriedOId==='')
      {
        console.log("空字符");
        this.reload();
        return;
      }
      this.$ajax.get("http://localhost:8080/Orders/findByOId/oId="+this.queriedOId).then(response => {
        console.log(response);
        if(response.data != null)
        {
          console.log("success")
          console.log(response.data);
          console.log("success");
          this.finishedData=[];
          console.log("finishedData")
          console.log(this.finishedData)
          this.finishedData.push(response.data);
          // this.userData=response.data;
          console.log(this.finishedData);
          this.alterQuerySuccess();
        }else{
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


    },
    }
</script>

<style scoped>

</style>
