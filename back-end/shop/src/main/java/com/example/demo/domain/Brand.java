package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "brand_table")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class Brand {
    @GeneratedValue//(strategy = GenerationType.AUTO)
    @Id
    private Integer braId;//主键 品牌号

    @ManyToMany//多对多关联Store
    @JoinTable(name="Brand_Store",joinColumns={@JoinColumn(name="braId")},inverseJoinColumns={@JoinColumn(name="sId")})
    private List<Store> stores=new ArrayList<>();

    @OneToMany(mappedBy="brand", fetch = FetchType.LAZY)
    private List<BrandStore> brandStores=new ArrayList<>();

    @Column//(nullable = false)
    private String braAddress;//地址

    @Column//(nullable = false)
    private String braName;//名称

    public Integer getBraId() {
        return braId;
    }

    public void setBraId(Integer braId) {
        this.braId = braId;
    }

    public void setbraAddress(String braAddress){this.braAddress=braAddress;}
    public String getbraAddress(){return  braAddress;}

    public void setbraName(String braName){this.braName=braName;}
    public String getbraName(){return braName;}

    @JsonBackReference(value = "store")//防止循环嵌套
    public void setStores(List<Store> stores){this.stores=stores;}
    public List<Store> getStores(){return stores;}

    @JsonBackReference(value = "brandstore")
    public void setBrandStores(List<BrandStore> brandStores){this.brandStores=brandStores;}
    public List<BrandStore> getBrandStores(){return brandStores;}
}
