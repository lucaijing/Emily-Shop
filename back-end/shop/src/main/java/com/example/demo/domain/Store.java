package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "store_table")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Store {
    @GeneratedValue//(strategy = GenerationType.AUTO)
    @Id
    private Integer sId;//主键 仓库号

    @OneToMany(mappedBy = "store")//一对多关联Product
    private List<Product> products=new ArrayList<>();//仓库中化妆品的集合
////////////////////////////////////////////////////////////////////////////////////////////
    @ManyToMany//多对多关联Brand
    @JoinTable(name="Brand_Store",joinColumns = {@JoinColumn(name = "sId")},inverseJoinColumns = {@JoinColumn(name = "braId")})
    private List<Brand> brandes=new ArrayList<>();

    @OneToMany(mappedBy="store", fetch = FetchType.LAZY)//入货单
    private List<BrandStore> brandStores=new ArrayList<>();
////////////////////////////////////////////////////////////////////////////////////////////////
    @ManyToMany//多对多关联Customer
    @JoinTable(name="Customer_Store",joinColumns = {@JoinColumn(name = "sId")},inverseJoinColumns = {@JoinColumn(name = "cId")})
    private List<Customer> customers=new ArrayList<>();

    @OneToMany(mappedBy="store", fetch = FetchType.LAZY)//出货单
    private List<CustomerStore> customerStores=new ArrayList<>();
//////////////////////////////////////////////////////////////////////////////////////////////
    @Column//(nullable = false)
    private String sAddress;//地址

    @Column//(nullable = false)
    private Integer sNumber;//库存数量

    public void setsId(Integer sId){this.sId=sId;}
    public Integer getsId(){return sId;}


    @JsonBackReference(value = "product")
    public void setProducts(List<Product> list){
        this.products=list;
    }
    public List<Product> getProducts(){
        return products;
    }

    public void setsAddress(String sAdd){this.sAddress=sAdd;}
    public String getsAddress(){return sAddress;}

    public void setsNumber(Integer sNumber){this.sNumber=sNumber;}
    public Integer getsNumber(){return sNumber;}

    @JsonBackReference(value = "brand")//防止循环嵌套
    public void setBrandes(List<Brand> brandes){this.brandes=brandes;}
    public List<Brand> getBrandes(){return brandes;}

    @JsonBackReference(value = "brandstore")
    public void setBrandStores(List<BrandStore> brandStores){this.brandStores=brandStores;}
    public List<BrandStore> getBrandStores(){return brandStores;}

    @JsonBackReference(value = "customer")
    public void setCustomers(List<Customer> customers){this.customers=customers;}
    public List<Customer> getCustomers(){return customers;}

    @JsonBackReference(value = "customerstore")
    public void setCustomerStores(List<CustomerStore> customerStores){this.customerStores=customerStores;}
    public List<CustomerStore> getCustomerStores(){return customerStores;}
}
