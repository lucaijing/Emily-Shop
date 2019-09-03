package com.example.demo.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Brand_Store")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class BrandStore {

    @Id
    @GeneratedValue
    private Integer bsId;//进货单号

    @ManyToOne(fetch = FetchType.LAZY)
   // @Cascade(value = CascadeType.SAVE_UPDATE)
    @JoinColumn(name="braId")
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
   // @Cascade(value = CascadeType.SAVE_UPDATE)
    @JoinColumn(name="sId")
    private Store store;

    @Column
    private Integer proId;//化妆品号

    @Column
    private Integer bproNumber;//进货数量
    @Column
    private double bproPrice;//进单价
    @Column
    private double bsSum;//总金额

    @Column
    private Integer bsIsdone;//判断订单是否完成，0是未完成，1是已完成

    public void setBsId(Integer bsId){this.bsId=bsId;}
    public Integer getBsId(){return bsId;}

    public void setBrand(Brand brand){this.brand=brand;}
    public Brand getBrand(){return brand;}

    public void setStore(Store store){this.store=store;}
    public Store getStore(){return store;}

    public void setproId(Integer proId){this.proId=proId;}
    public Integer getproId(){return proId;}

    public void setBproNumber(Integer bproNumber){this.bproNumber=bproNumber;}
    public Integer getBproNumber(){return bproNumber;}

    public void setBproPrice(double bproPrice){this.bproPrice=bproPrice;}
    public double getBproPrice(){return bproPrice;}

    public void setBsSum(double bsSum){this.bsSum=bsSum;}
    public double getBsSum(){return bsSum;}

    public Integer getBsIsdone() {
        return bsIsdone;
    }

    public void setBsIsdone(Integer bsIsdone) {
        this.bsIsdone = bsIsdone;
    }
}
