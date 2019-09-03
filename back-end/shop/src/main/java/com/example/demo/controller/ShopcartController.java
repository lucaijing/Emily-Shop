package com.example.demo.controller;

import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

@RestController
@RequestMapping(value = "/Shopcart")
//@SessionAttributes(types = {Map.class})//把Map购物车加入Session
public class ShopcartController {

    Map<Integer,Integer> shopcart=new HashMap<>();

    @Autowired
    private ProductService productService;

    //添加到购物车、加减购物车里的数量
    @GetMapping("/addcart/proId={proId}num={num}")
    public List<Shopcart> addcart(@PathVariable("proId") Integer proId,
                       @PathVariable("num") Integer num){
        boolean isin=shopcart.containsKey(proId);
        if(!isin){
            shopcart.put(proId,1);
            return findAll();
        }
        int i=shopcart.get(proId);
        i+=num;
        if(i<0){
            i=0;
        }
        shopcart.put(proId,i);
        return findAll();

    }

    @GetMapping("/findAll")
    public List<Shopcart> findAll(){

       // int numm=1;
       // System.out.println(numm++);
        Iterator iterator=shopcart.entrySet().iterator();
        List<Shopcart> all=new ArrayList<>();
        while(iterator.hasNext()){
            Map.Entry entry=(Map.Entry)iterator.next();
            Object key=entry.getKey();
            Object value=entry.getValue();

            Shopcart shopcart1=new Shopcart();

            Integer proId=(Integer)key;
            Integer num=(Integer)value;

            shopcart1.setproId(proId);
            shopcart1.setNum(num);

            double p=productService.findByProId(proId).getproPrice();

            shopcart1.setproName(productService.findByProId(proId).getproName());
            shopcart1.setproPrice(p);
            shopcart1.setSum(num*p);

            all.add(shopcart1);
        }
        return all;

    }

    //单个商品退出购物车
    @GetMapping("/clearone/proId={proId}")
    public String clearone(@PathVariable("proId") Integer proId){
        //int i=shopcart.get(proId);
        shopcart.remove(proId);
        return "true";
    }
    //清空购物车
    @GetMapping("/clearAll")
    public String clearAll(){
        shopcart.clear();
        return "true";
    }
}
