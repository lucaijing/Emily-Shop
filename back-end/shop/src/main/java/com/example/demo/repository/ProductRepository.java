package com.example.demo.repository;

import com.example.demo.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Integer> {

    Product findByProId(Integer proId);
    //List<Product> findByproName(String proName);
    //List<Product> findByProType(String proType);
    List<Product> findByProBraname(String proBraname);
    //List<Product> findByProPriceBetween(double max,double min);

    //高级搜索的品牌+肤质+价格+分类
    @Query(value = "SELECT b FROM  Product b WHERE b.proBraname LIKE %?1% AND b.proSkin LIKE %?2% " +
            "AND b.proType LIKE %?3% AND b.proPrice BETWEEN ?4 AND ?5")
    List<Product> findByProSenior(String proBraname,String proSkin,String proType,double price1,double price2);

    //高级搜索的品牌+肤质+价格+分类 降序
    @Query(value = "SELECT b FROM  Product b WHERE b.proBraname LIKE %?1% AND b.proSkin LIKE %?2% " +
            "AND b.proType LIKE %?3% AND b.proPrice BETWEEN ?4 AND ?5 ORDER BY b.proPrice DESC")
    List<Product> findByProSeniorDesc(String proBraname,String proSkin,String proType,double price1,double price2);

    @Query(value = "SELECT b FROM Product b WHERE b.proSkin LIKE %?1%")
    List<Product> findByProSkin(String proSkin);

    @Query(value="SELECT b FROM Product b WHERE  b.proType LIKE %?1%")
    List<Product> findByProType(String proType);

    @Query(value = "SELECT b FROM Product b ORDER BY b.proPrice DESC ")
    List<Product> findByAllDesc();

    @Query(value = "SELECT b FROM Product b WHERE b.proType=?1 ORDER BY b.proPrice DESC")
    List<Product> findByProTypeDesc(String proType);

    @Query(value = "SELECT b FROM Product b WHERE b.proPrice BETWEEN ?2 AND ?1")
    List<Product> findByProPriceBetween(double max, double min);

    @Query(value = "SELECT b FROM Product b WHERE b.proName LIKE %?1%")//化妆品名的模糊查询
    List<Product> findByproNameLike(String proName);

    @Query(value = "SELECT b FROM Product b WHERE b.proName LIKE %?1% ORDER BY b.proPrice DESC ")//化妆品名的模糊查询
    List<Product> findByproNameLikeDesc(String proName);

    @Query(value = "SELECT b FROM Product b WHERE b.proName LIKE %?1% AND b.proType=?2")//化妆品名及类型查询
    List<Product> findByproNameLikeAndProType(String proName, String proType);

    @Query(value = "SELECT b FROM Product b WHERE b.proBraname LIKE %?1%")//品牌的模糊查询
    List<Product> findByProBranameLike(String proBraname);

    @Query(value = "SELECT b FROM Product b WHERE b.proBraname LIKE %?1% ORDER BY b.proPrice DESC ")//品牌的模糊查询
    List<Product> findByProBranameLikeDesc(String proBraname);
////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Query(value = "SELECT b FROM Product b WHERE b.store.sId=?1 ")//根据仓库号查询
    List<Product> findBySId(Integer sId);
////////////////////////////////////////////////////////////////////////////////////////////////////////
    @Transactional
    @Modifying
    @Query(value = "UPDATE Product b SET b.proName=?1,b.proType=?2,b.proBraname=?3,b.proPrice=?4,b.proSkin=?5,b.proDescription=?6 WHERE b.proId=?7")
    void UpdateProduct(String proName, String proType, String proBraname, double proPrice, String proSkin, String proDescription, Integer proId);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Product b SET b.proPrice=?1 WHERE b.proId=?2")//单改价格
    void UpdatePrice(double proPrice, Integer proId);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Product b SET b.proDescription=?1 WHERE b.proId=?2 ")//单改介绍
    void UpdateDescription(String proDescription, Integer proId);
    @Transactional
    @Modifying
    @Query(value = "UPDATE Product b SET b.proNumber=?1 WHERE b.proId=?2 ")//单改库存
    void UpdateNumber(Integer proNumber, Integer proId);

}
