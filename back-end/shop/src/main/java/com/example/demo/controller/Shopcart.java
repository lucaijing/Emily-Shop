package com.example.demo.controller;

public class Shopcart {

    private Integer proId;//化妆品
    private Integer num;//数量
    private String proName;//化妆品名
    private double proPrice;//单价
    private double sum;//总金额


    public Integer getproId() {
        return proId;
    }

    public void setproId(Integer proId) {
        this.proId = proId;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public String getproName() {
        return proName;
    }

    public void setproName(String proName) {
        this.proName = proName;
    }

    public double getproPrice() {
        return proPrice;
    }

    public void setproPrice(double proPrice) {
        this.proPrice = proPrice;
    }

    public double getSum() {
        return sum;
    }

    public void setSum(double sum) {
        this.sum = sum;
    }
}
