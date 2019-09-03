package com.example.demo.service;

import com.example.demo.domain.Product;

import java.util.List;

public interface ProductService {

    String CreateB(Product product);//保存一本化妆品
    String DeleteB(Integer proId);//删除一本化妆品
    List<Product> findALL();//找到所有化妆品目
    String UpdateB(String proName, String proType, String proBraname, double proPrice, String proSkin, String proDescription, Integer proId);//更新一本化妆品
    String UpdatePrice(double proPrice, Integer proId);//更新化妆品本价格
    String UpdateDescription(String proDescription, Integer proId);//更新化妆品本简介
    String UpdateNumber(Integer proNumber, Integer proId);//更新数量

    Product findByProId(Integer proId);

    List<Product> findByProSeniorDesc(String proBraname,String proSkin,String proType,double price1,double price2);
    List<Product> findByProSenior(String proBraname,String proSkin,String proType,double price1,double price2);
    List<Product> findByproNameLike(String proName);
    List<Product> findByproNameLikeDesc(String proName);
    List<Product> findByproNameLikeAndProType(String proName, String proType);
    List<Product> findByProBranameLike(String proBraname);
    List<Product> findByProBranameLikeDesc(String proBraname);

    List<Product> findByProType(String proType);
    List<Product> findByProTypeDesc(String proType);
    List<Product> findByAllDesc();
    List<Product> findByProBraname(String proBraname);
    List<Product> findByProPriceBetween(double max, double min);
    List<Product> findByProSkin(String proSkin);
    List<Product> findBySId(Integer sId);

}
