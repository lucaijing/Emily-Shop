<template>
  <div id="ProductManage">
    <el-container>
      <el-main>

        <el-col :span="10" >
          <div class="grid-content bg-purple">
            <el-input @input="valueChanged" v-model="queriedProName" placeholder="请输入您想要查询的商品名" ></el-input>
          </div>
        </el-col>


          <el-col :span="1" offset="1">
            <el-button type="primary" icon="el-icon-search" @click="queryByproName">搜索</el-button>
          </el-col>


        <el-col :span="4" offset="4">
          <template>
            <el-select v-model="TypeValue" placeholder="选择分类">
              <el-option
                v-for="item in options"
                :key="item.value"
                :label="item.label"
                v-model="item.label"
              >{{item.label}}
              </el-option>
            </el-select>
          </template>
        </el-col>

        <el-col :span="1" offset="1">
          <el-button type="info" icon="el-icon-search" @click="findTypeProduct">搜索</el-button>
        </el-col>


        <el-col :span="4" offset="3">
          <el-button type="primary" icon="el-icon-edit" @click="createProduct">添加商品信息</el-button>


        </el-col>
        <!--<el-col :span="6" offset="1">-->
          <!--<el-button type="primary">显示全部商品信息</el-button>-->
          <!--<el-button type="primary" icon="el-icon-delete">删除商品信息</el-button>-->
        <!--</el-col>-->


        <!--修改商品信息-->
        <el-dialog
          title="修改商品信息"
          :visible.sync="dialogUpdateVisible"
          :close-on-click-modal="false"
          :close-on-press-escape="false">
          <el-form id="#update" :model="update" ref="update" label-width="100px">
            <el-form-item label="编号" prop="proId">
              <el-input :disabled="true" v-model="update.proId"></el-input>
            </el-form-item>
            <el-form-item label="商品名">
              <el-input v-model="update.proName"></el-input>
            </el-form-item>
            <el-form-item label="仓库号">
              <el-input v-model="update.store.sId"></el-input>
            </el-form-item>
            <el-form-item label="类别">
              <el-input v-model="update.proType"></el-input>
            </el-form-item>
            <el-form-item label="品牌">
              <el-input v-model="update.proBraname"></el-input>
            </el-form-item>
            <el-form-item label="价格">
              <el-input v-model="update.proPrice"></el-input>
            </el-form-item>
            <el-form-item label="适用人群">
              <el-input v-model="update.proSkin"></el-input>
            </el-form-item>
            <el-form-item label="库存数量">
              <el-input v-model="update.proNumber"></el-input>
            </el-form-item>
            <el-form-item label="简介">
              <el-input v-model="update.proDescription"></el-input>
            </el-form-item>
          </el-form>
          <span slot="footer" class="dialog-footer">
             <el-button @click="dialogUpdateVisible = false">取 消</el-button>
              <el-button type="primary" @click="updateProduct">提交</el-button>
             </span>
        </el-dialog>

        <el-table
          :data="productData"
          style="width: 100%">
          <el-table-column
            fixed
            prop="proId"
            label="商品ID">
          </el-table-column>
          <el-table-column
            prop="proName"
            label="商品名称">
          </el-table-column>
          <el-table-column
            prop="store.sId"
            label="仓库号">
          </el-table-column>
          <el-table-column
            prop="proType"
            label="类别">
          </el-table-column>
          <el-table-column
            prop="proBraname"
            label="品牌">
          </el-table-column>
          <el-table-column
            prop="proPrice"
            label="价格">
          </el-table-column>
          <el-table-column
            prop="proSkin"
            label="适用人群">
          </el-table-column>
          <el-table-column
            prop="proNumber"
            label="库存数量">
          </el-table-column>
          <!--<el-table-column-->
            <!--label="操作">-->
            <!--<el-button type="primary">编辑</el-button>-->
            <!--<el-button type="primary">删除</el-button>-->
          <!--</el-table-column>-->
          <el-table-column label="操作" width="220" align="center">
            <template slot-scope="scope">
              <el-button type="success" size="mini" icon="el-icon-setting" @click="setCurrent(scope.row)">编辑</el-button>
              <el-button type="danger" size="small" icon="el-icon-delete" @click="removed(scope.row)">删除</el-button>
            </template>
          </el-table-column>
        </el-table>
      </el-main>
    </el-container>
  </div>
</template>

<script>
    export default {
        name: "ProductManage",
      inject:["reload"],
      data() {
        return {
          productData:[],
          Alllist:[],
          queriedProName: null,
          createProductVisible:false,
          dialogUpdateVisible:false,

          TypeValue:null,
          value:'',
          /*下拉列表分类*/
          options: [
            {
            value: 'opt1',
            label: '全部分类'
          },
            {
            value: 'opt2',
            label: '历史古籍'
          },
            {
            value: 'opt3',
            label: '哲学'
          },
            {
            value: 'opt4',
            label: '政治'
          },
            {
            value: 'opt5',
            label: '社会科学'
          },
            {
            value: 'opt6',
            label: '文学'
          },
            {
            value: 'opt7',
            label: '人物传记'
          },
            {
            value: 'opt8',
            label: '古诗词'
          },
            {
            value: 'opt9',
            label: '考研必备商品'
          },
            {
            value: 'opt10',
            label: '四六级英语备考'
          },
            {
            value: 'opt11',
            label: '计算机系列丛商品'
          },
            {
            value: 'opt12',
            label: '程序设计'
          },
            {
            value: 'opt13',
            label: '农业林业'
          },
            {
            value: 'opt14',
            label: '科普读物'
          },
            {
            value: 'opt15',
            label: '旅游'
          },
            {
            value: 'opt16',
            label: '烹饪'
          },
            {
            value: 'opt17',
            label: '摄影'
          },
            {
            value: 'opt18',
            label: '英语原版商品'
          },
            {
            value: 'opt19',
            label: '期刊'
          }
          ],

          update:{
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
          },
        }

      },
      mounted() {
        console.log("loading data.");
        this.$ajax({
          method: "get",
          url: "http://localhost:8080/Product/findAll"
        }).then(response => {
          console.log("123123123213")
          console.log(response.data);
          //for (let i = 0; i < response.data.length; i++) {
          //this.userData=push(response.data[i]);
          this.productData=response.data;
          //}
        })
      },
      methods:{
        createProduct(){window.location="http://localhost:8081/#/addPic/1";},
        alterDeleteSuccess() {
          this.$message({
            message: "恭喜你，删除成功",
            type: "success"
          });
          this.reload();
        },
        alterDeleteError() {
          this.$message({
            message: "抱歉，删除失败",
            type: "error"
          })
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
        alterUpdateSuccess() {
          this.$message({
            message: "恭喜你，修改成功",
            type: "success"
          });
          this.reload();
        },
        alterUpdateError() {
          this.$message({
            message: "抱歉，修改失败",
            type: "error"
          });
          this.reload();
        },


        // 当查询框被删减为空时自动查询所有数据
        valueChanged() {
          if (this.queriedProName === "") {
            console.log("当前为空字符串");
            this.queryByproName();
          }
        },


        //删除这个商品
        removed(currentInfo) {
          console.log("删除商品");
          this.$confirm(
            "此操作将永久删除该商品" + currentInfo.proId + "，是否继续？", "提示",
            {
              type: "warning"
            }
          ).then(() => {
            console.log("确认删除");
            // 向服务器请求删除
            let proId = currentInfo.proId;
            console.log(proId);
            this.$ajax.get('http://localhost:8080/Product/DeleteB/proId=' + proId).then(response => {
              console.log(response);
              if ( response.data != null) {
                this.alterDeleteSuccess();

              }
              else {
                this.alterDeleteError();
              }
            }).catch(() => {
              console.log("delete error!");
              this.alterDeleteError();
            });
          }).catch(() => {
            this.$message.info("已取消操作！");
          });
        },


        //编辑
        updateProduct()
        {
          let data = this.update;
          console.log("woaibeijingtainanment")
          console.log(data);
          this.$ajax.get('http://localhost:8080/Product/UpdateB/proName='+data.proName+'proType='+data.proType+'proBraname='+data.proBraname+ 'proPrice='+data.proPrice+'proSkin='+data.proSkin+'proDescription='+data.proDescription+'proId='+data.proId).then(response => {
            console.log(response);
            this.dialogUpdateVisible = false;
            if (response.data != null) {
              this.alterUpdateSuccess();
            } else {
              this.alterUpdateError();
            }
          }).catch(() => {
            console.log("save error");
            this.alterUpdateError();
          });
        },
        setCurrent(currentInfo) {
          console.log(currentInfo);
          this.update.proId=currentInfo.proId;
          this.update.proName = currentInfo.proName;
          this.update.store.sId = currentInfo.store.sId;
          this.update.proType = currentInfo.proType;
          this.update.proBraname = currentInfo.proBraname;
          this.update.proPrice = currentInfo.proPrice;
          this.update.proSkin = currentInfo.proSkin;
          this.update.proNumber = currentInfo.proNumber;
          this.update.proDescription = currentInfo.proDescription;
          this.dialogUpdateVisible = true;
          console.log(this.dialogUpdateVisible);
        },

        //查询商品名
        queryByproName()
        {
          console.log(this.queriedProName);
          if (this.queriedProName=='')
          {
            console.log("空字符");
            this.reload();
            return;
          }
          this.$ajax.get("http://localhost:8080/Product/findByProNameLike/proName="+this.queriedProName).then(response => {
            console.log(response);
            if(response.data[0] != null)
            {
              console.log("success")
              console.log(response.data[0].proId);
              console.log("success");
              this.productData=[];
              console.log("userDate")
              console.log(this.productData)
              //this.productData.push(response.data[0]);
               this.productData=response.data;
              console.log(this.productData);
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

        //查询类型
        findTypeProduct()
        {
          this.pic_visible=1;
          console.log(this.TypeValue);
          if(this.TypeValue=="全部分类")
          {
            this.pic_visible=1;
            if(this.queriedProName==null){
              this.reload();
            }
            else{
              this.$ajax.get("http://localhost:8080/Product/findByProNameLike/proName="+this.queriedProName).then(response=>{
                this.productData=response.data;
                console.log(this.productData);
              })
            }

          }
          else
          {
            if(this.queriedProName==null){
              this.$ajax.get("http://localhost:8080/Product/findByProType/proType="+this.TypeValue).then(response=>{

                this.productData=response.data;
                console.log(this.productData)
                this.alterQuerySuccess();
              })
            }
            else{
              this.$ajax.get("http://localhost:8080/Product/findByProNameLikeAndProType/proName="+this.queriedProName+"proType="+this.TypeValue).then(response=>{
                this.productData=response.data;

                console.log(response.data);
                this.alterQuerySuccess();
              })
            }

          }


        },

      },
    }
</script>

<!--<style scoped>-->
  <!--.demo-table-expand {-->
    <!--font-size: 0;-->
  <!--}-->
  <!--.demo-table-expand label {-->
    <!--width: 90px;-->
    <!--color: #99a9bf;-->
  <!--}-->
  <!--.demo-table-expand .el-form-item {-->
    <!--margin-right: 0;-->
    <!--margin-bottom: 0;-->
    <!--width: 50%;-->
  <!--}-->
<!--</style>-->

<style scoped>
  h1,
  h2 {
    font-weight: normal;
  }
  ul {
    list-style-type: none;
    padding: 0;
  }
  li {
    display: inline-block;
    margin: 0 10px;
  }
  a {
    color: #42b983;
  }

  .orderTitle {
    border-bottom: 2px #409eff solid;
    font-size: 24px;
    padding-bottom: 10px;
  }
</style>
