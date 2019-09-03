package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name="customer_table")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Customer{
    @GeneratedValue
    @Id
    private Integer cId;//主键用户号

    @ManyToMany//多对多关联Store
    @JoinTable(name="Customer_Store",joinColumns={@JoinColumn(name="cId")},inverseJoinColumns={@JoinColumn(name="sId")})
    private List<Store> stores=new ArrayList<>();

    @OneToMany(mappedBy="customer", fetch = FetchType.LAZY)
    private List<CustomerStore> customerStores=new ArrayList<>();
////////////////////////////////////////////////////////////////////////////////////////////
    @ManyToMany//多对多关联Product
    @JoinTable(name = "Customer_Product",joinColumns = {@JoinColumn(name = "cId")},inverseJoinColumns = {@JoinColumn(name = "proId")})
    private List<Product> products=new ArrayList<>();

    @OneToMany(mappedBy = "customer",fetch = FetchType.LAZY)
    private List<CustomerProduct> customerProducts=new ArrayList<>();
//////////////////////////////////////////////////////////////////////////////////////////////
    @Column//(nullable = false)
    private String cPassword;//密码

    @Column//(nullable = false)
    private String cAddress;//住址

    @Column//(nullable = false)
    private String cName;//姓名

    @Column//(nullable = false)
    private String cPhone;//联系电话

    public void setcId(Integer cId) {this.cId=cId;}
    public Integer getcId(){return cId;}

    public void setcPassword(String cPassword){this.cPassword=cPassword;}
    public String getcPassword(){return  cPassword;}

    public void setcAddress(String cAddress){this.cAddress=cAddress;}
    public String getcAddress(){return  cAddress;}

    public void setcName(String cName){this.cName=cName;}
    public String getcName(){return  cName;}

    public void setcPhone(String cPhone){this.cPhone=cPhone;}
    public String getcPhone() { return cPhone; }

    @JsonBackReference(value = "store")
    public void setStores(List<Store> stores){this.stores=stores;}
    public List<Store> getStores(){return stores;}

    @JsonBackReference(value = "customerstore")
    public void setCustomerStores(List<CustomerStore> customerStores){this.customerStores=customerStores;}
    public List<CustomerStore> getCustomerStores(){return customerStores;}

    @JsonBackReference(value = "product")
    public void setProducts(List<Product> products) {
        this.products = products;
    }
    public List<Product> getProducts() {
        return products;
    }

    @JsonBackReference(value = "customerproduct")
    public void setCustomerProducts(List<CustomerProduct> customerProducts) {
        this.customerProducts = customerProducts;
    }
    public List<CustomerProduct> getCustomerProducts() {
        return customerProducts;
    }
}