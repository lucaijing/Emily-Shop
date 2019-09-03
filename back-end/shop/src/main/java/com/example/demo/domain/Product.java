package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "product_table")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Product {
    @GeneratedValue
    @Id
    private Integer proId;//主键商品号

    @Column//(nullable = false)
    private String proName;//商品名


    @ManyToOne
    @JoinColumn(name="sId")
    private Store store;//仓库号//外键store.sId

   //////////////////////////////////////////////////////////////
    @ManyToMany//多对多关联Customer
    @JoinTable(name = "Customer_Product",joinColumns = {@JoinColumn(name = "proId")},inverseJoinColumns = {@JoinColumn(name = "cId")})
    private List<Customer> customers=new ArrayList<>();

    @OneToMany(mappedBy = "product",fetch = FetchType.LAZY)
    private List<CustomerProduct> customerProducts=new ArrayList<>();
 /////////////////////////////////////////////////////////////////////////

    @Column//(nullable = false)
    private String proType;//商品种类

    @Column//(nullable = false)
    private String proBraname;//品牌名称

    @Column//(nullable = false)
    private double proPrice;//价格

    @Column//(nullable = false)
    private String proSkin;//适用肤质

    @Column
    private Integer proNumber;//库存数量

    @Column//(nullable = false)
    private String proDescription;//简介

    @Column
    private String proPic;

    public void setproId(Integer proId){this.proId=proId;}
    public Integer getproId(){return proId;}

    public void setproName(String proName){this.proName=proName;}
    public String getproName(){return proName;}

    public void setstore(Store store){this.store=store;}
    public  Store getstore(){return store;}

    public void setproType(String proType){this.proType=proType;}
    public String getproType(){return  proType;}

    public void setproBraname(String proBraname){this.proBraname=proBraname;}
    public String getproBraname(){return proBraname;}

    public void setproPrice(double proPrice){this.proPrice=proPrice;}
    public double getproPrice(){return proPrice;}

    public void setproSkin(String proSkin){this.proSkin=proSkin;}
    public  String getproSkin(){return proSkin;}

    public void setproNumber(Integer proNumber){this.proNumber=proNumber;}
    public  Integer getproNumber(){return proNumber;}

    public void setproDescription(String proDescription){this.proDescription=proDescription;}
    public String getproDescription(){return proDescription;}

    @JsonBackReference(value = "customer")
    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }
    public List<Customer> getCustomers() {
        return customers;
    }

    @JsonBackReference(value = "customerproduct")
    public void setCustomerProducts(List<CustomerProduct> customerProducts) {
        this.customerProducts = customerProducts;
    }
    public List<CustomerProduct> getCustomerProducts() {
        return customerProducts;
    }

    public void setproPic(String proPic){
        this.proPic=proPic;
    }
    public String getproPic(){
        return proPic;
    }
}
