package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "Customer_Store")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class CustomerStore {
    @Id
    @GeneratedValue
    private Integer csId;//出货单号

    @ManyToOne(fetch = FetchType.LAZY)
    // @Cascade(value = CascadeType.SAVE_UPDATE)
    @JoinColumn(name="cId")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    // @Cascade(value = CascadeType.SAVE_UPDATE)
    @JoinColumn(name="sId")
    private Store store;

    @Column
    private String cName;
    @Column
    private String cAddress;
    @Column
    private Integer proId;//化妆品号
    @Column
    private Integer ccNumber;//购买数量
    @Column
    private double proPrice;//售单价
    @Column
    private double ccSum;//总售价

    @Column
    private Integer csIsdone;//判断订单是否完成，0是未完成，1是已完成

    public Integer getCsId() {
        return csId;
    }

    public void setCsId(Integer csId) {
        this.csId = csId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getcAddress() {
        return cAddress;
    }

    public void setcAddress(String cAddress) {
        this.cAddress = cAddress;
    }

    public Integer getproId() {
        return proId;
    }

    public void setproId(Integer proId) {
        this.proId = proId;
    }

    public Integer getCcNumber() {
        return ccNumber;
    }

    public void setCcNumber(Integer ccNumber) {
        this.ccNumber = ccNumber;
    }

    public double getproPrice() {
        return proPrice;
    }

    public void setproPrice(double proPrice) {
        this.proPrice = proPrice;
    }

    public double getCcSum() {
        return ccSum;
    }

    public void setCcSum(double ccSum) {
        this.ccSum = ccSum;
    }

    public Integer getCsIsdone() {
        return csIsdone;
    }

    public void setCsIsdone(Integer csIsdone) {
        this.csIsdone = csIsdone;
    }
}
