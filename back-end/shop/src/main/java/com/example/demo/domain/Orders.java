package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Data
@Entity
@Table(name="Orders_table")
public class Orders {

    @GeneratedValue//(strategy = GenerationType.AUTO)
    @Id
    private Integer oId;//主键 订单号

    @Column//(nullable = false)
    private Integer proId;//化妆品号

    @Column
    private String proName;

    @Column//(nullable = false)
    private Integer oNumber;//数量

    @Column//(nullable = false)
    private Integer cId;//用户号

    @Column//(nullable = false)
    private double oSum;//总金额

    @Column
    private String cAddress;//用户地址

    @Column
    private Integer oIsdone;//判断订单是否完成，0是未完成，1是已完成

    public void setoId(Integer oId){this.oId=oId;}
    public Integer getoId(){return oId;}

    public void setproId(Integer proId){this.proId=proId;}
    public Integer getproId(){return  proId;}

    public  void setoNumber(Integer oNumber){this.oNumber=oNumber;}
    public Integer getoNumber(){return oNumber;}

    public void setcId(Integer cId){this.cId=cId;}
    public Integer getcId(){return cId;}

    public void setoSum(double oSum){this.oSum=oSum;}
    public double getoSum(){return oSum;}

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public String getproName() {
        return proName;
    }

    public void setproName(String proName) {
        this.proName = proName;
    }

    public Integer getoIsdone() {
        return oIsdone;
    }

    public void setoIsdone(Integer oIsdone) {
        this.oIsdone = oIsdone;
    }
}
