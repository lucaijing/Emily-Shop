/*

import Vue from 'vue'
import Router from 'vue-router'
import HelloWorld from '@/components/HelloWorld'
import test1 from '@/pages/test1'
import index from '@/pages/index'
import Shopcart from '@/pages/Shopcart'
import seniorSearch from '@/pages/seniorSearch'
import Registe from '@/pages/Registe'
import Employee from '@/pages/Employee/Employee'
import Home from '@/pages/Box/Home'
import Home2 from '@/pages/Box/Home2'
Vue.use(Router)

export default new Router({
  routes: [
/!*    {
      path: '/',
      name: 'HelloWorld',
      component: HelloWorld
    },*!/

    {
      path: '/home',
      component: Home,
      children: [
        {
          path: '/Employee/Employee',
          name: 'Employee',
          component:Employee
        },
      ]
    },
    {
      path: '/',
      component: Home2,
      children: [
        {
          path:'/Shopcart',
          name:'Shopcart',
          component:Shopcart
        },
        {
          path:'/test1',
          name:'test1',
          component:test1
        },
        {
          path:'/',
          name:'index',
          component:index
        },
        {
          path:'/seniorSearch',
          name:'seniorSearch',
          component:seniorSearch
        },
        {
          path:'/Registe',
          name:'Registe',
          component:Registe
        },

      ]
    },


  ]

})
*/


import Vue from 'vue'
import Router from 'vue-router'
import index from '@/pages/User/index'
import seniorSearch from '@/pages/User/seniorSearch'
import Registe from '@/pages/User/Registe'
import home1 from '@/pages/User/Home1'
import home2 from '@/pages/Administrator/Home2'
import userManage from '@/pages/Administrator/UserManage'
import productManage from '@/pages/Administrator/ProductManage'
import storeManage from '@/pages/Administrator/StoreManage'
import shopcart from '@/pages/User/Shopcart'
import userinfo from '@/pages/User/UserInfo'
import product from '@/pages/User/product'
import order from  '@/pages/User/Order'
import inManage from '@/pages/Administrator/InManage'
import outManage from '@/pages/Administrator/OutManage'
import ordersManage from '@/pages/Administrator/OrdersManage'
import productList from '@/pages/User/productList'
import addPic from '@/pages/Administrator/addPic'

Vue.use(Router)

export default new Router({
  routes: [
    {
      path: '/home1',
      name: 'Home1',
      component: home1,
      children: [
       {
          path: '/index',
          name: 'index',
          component: index
        },
       {
          path: '/Registe',
          name: 'Registe',
          component: Registe
        },
        {
          path: '/seniorSearch',
          name: 'seniorSearch',
          component: seniorSearch
        },
        {
          path: '/shopcart',
          name: 'shopcart',
          component: shopcart
        },
        {
          path: '/userinfo',
          name: 'userinfo',
          component: userinfo
        },
        {
          path: '/product/:proId',
          name: 'product',
          component: product
        },
       {
          path: '/order',
          name: 'order',
          component: order
        },
        {
          path: '/ProductList/:k',
          name: 'productList',
          component: productList
        },
      ]

    },

    {
      path: '/home2',
      component: home2,
      children: [
        {
          path: '/userManage',
          name: 'userManage',
          component: userManage
        },
        {
          path: '/productManage',
          name: 'productManage',
          component: productManage
        },
        {
          path: '/storeManage',
          name: 'storeManage',
          component: storeManage
        },
        {
          path: '/inManage',
          name: 'inManage',
          component: inManage
        },
        {
          path: '/outManage',
          name: 'outManage',
          component: outManage
        },
        {
          path: '/ordersManage',
          name: 'ordersManage',
          component: ordersManage
        },
        {
          path:'/addPic/:pic',
          name:'addPic',
          component:addPic
        }
      ]
    }

  ]


})

