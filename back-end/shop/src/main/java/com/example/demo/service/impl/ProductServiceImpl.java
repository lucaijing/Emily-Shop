package com.example.demo.service.impl;



import com.example.demo.domain.Product;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

   // private StoreRepository storeRepository;

    @Override
    public String CreateB(Product product)//创建
    {
        boolean flag = false;
        try {
            productRepository.save(product);
        //    Store store=storeRepository.findBySId(product.getstore().getsId());
        //    store.getProducts().add(product);//化妆品放到仓库
            flag = true;
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        if(flag){
            return "true";
        }
        else{return "false";}
    }

    @Override
    public String DeleteB(Integer proId)//删除
    {
        Product product=productRepository.findByProId(proId);//按照主键找到这本化妆品，之后删除
        boolean flag=false;
        if(product==null){
            return "null";
        }
        try
        {
            productRepository.delete(proId);
            flag=true;
        }catch (Exception e) {
            System.out.println(e.toString());
        }
        if(flag){
            return "true";
        }
        else{
            return "false";
        }
    }

    @Override
    public List<Product> findALL()//找到所有化妆品目
    {
        return productRepository.findAll();
    }

    @Override
    public String UpdateB(String proName,String proType,String proBraname,double proPrice,String proSkin,String proDescription,Integer proId){

        Product product=productRepository.findByProId(proId);
        if (product == null) {
            return "null";
        }
        boolean flag=false;
        try{
            productRepository.UpdateProduct(proName,proType,proBraname,proPrice,proSkin,proDescription,proId);
            flag=true;
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        if(flag){
            return "true";
        }
        else{
            return "false";
        }
    }

    @Override
    public String UpdatePrice(double proPrice,Integer proId){
        Product product=productRepository.findByProId(proId);
        if(product==null){
            return "null";
        }
        boolean flag=false;
        try{
            productRepository.UpdatePrice(proPrice,proId);
            flag=true;
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        if(flag){
            return "true";
        }
        else{
            return "false";
        }
    }

    @Override
    public String UpdateDescription(String proDescription,Integer proId){
        Product product=productRepository.findByProId(proId);
        if(product==null){
            return "null";
        }
        boolean flag=false;
        try{
            productRepository.UpdateDescription(proDescription,proId);
            flag=true;
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        if(flag){
            return "true";
        }
        else{
            return "false";
        }
    }

    @Override
    public String UpdateNumber(Integer proNumber,Integer proId){

        Product product=productRepository.findByProId(proId);
        if(product==null){
            return "null";
        }
        boolean flag=false;
        try{
            productRepository.UpdateNumber(proNumber,proId);
            flag=true;
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        if(flag){
            return "true";
        }
        else{
            return "false";
        }
    }

    @Override
    public Product findByProId(Integer proId){

        return productRepository.findByProId(proId);
    }

    @Override
    public List<Product> findByProSeniorDesc(String proBraname, String proSkin, String proType, double price1, double price2) {
        return productRepository.findByProSeniorDesc(proBraname, proSkin, proType, price1, price2);
    }

    @Override
    public List<Product> findByProSenior(String proBraname, String proSkin, String proType, double price1, double price2) {
        return productRepository.findByProSenior(proBraname,proSkin,proType,price1,price2);
    }

    @Override
    public List<Product> findByproNameLike(String proName){
        return productRepository.findByproNameLike(proName);
    }

    @Override
    public List<Product> findByproNameLikeDesc(String proName) {
        return productRepository.findByproNameLikeDesc(proName);
    }

    @Override
    public List<Product> findByproNameLikeAndProType(String proName,String proType){return productRepository.findByproNameLikeAndProType(proName,proType);}

    @Override
    public List<Product> findByProBranameLike(String proBraname) {
        return productRepository.findByProBranameLike(proBraname);
    }

    @Override
    public List<Product> findByProBranameLikeDesc(String proBraname) {
        return productRepository.findByProBranameLikeDesc(proBraname);
    }

    @Override
    public List<Product> findByProType(String proType){
        return productRepository.findByProType(proType);
    }

    @Override
    public List<Product> findByProTypeDesc(String proType) {
        return productRepository.findByProTypeDesc(proType);
    }

    @Override
    public List<Product> findByAllDesc() {
        return productRepository.findByAllDesc();
    }

    @Override
    public List<Product> findByProBraname(String proBraname){
        return productRepository.findByProBraname(proBraname);
    }
    @Override
    public List<Product> findByProPriceBetween(double max,double min){
        return productRepository.findByProPriceBetween(max,min);
    }
    @Override
    public List<Product> findByProSkin(String proSkin){
        return productRepository.findByProSkin(proSkin);
    }

    @Override
    public List<Product> findBySId(Integer sId) {
        return productRepository.findBySId(sId);
    }
}
