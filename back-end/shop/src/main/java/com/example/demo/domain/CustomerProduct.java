package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="Customer_Product")
@JsonIgnoreProperties({"handler","hibernateLazyInitializer"})
public class CustomerProduct {

    @GeneratedValue
    @Id
    private Integer cproId;//主键评论号

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="cId")
    private Customer customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="proId")
    private Product product;

    @Column
    private String cpContent;//评价内容


    @Column
    private Integer cpStar;





    public Integer getCproId() {

        return cproId;

    }



    public void setCproId(Integer cproId) {

        this.cproId = cproId;

    }



    public String getCpContent() {

        return cpContent;

    }



    public void setCpContent(String cpContent) {

        this.cpContent = cpContent;

    }



    public Integer getCpStar() {

        return cpStar;

    }



    public void setCpStar(Integer cpStar) {

        this.cpStar = cpStar;

    }



    public Customer getCustomer() {

        return customer;

    }



    public void setCustomer(Customer customer) {

        this.customer = customer;

    }



    public Product getProduct() {

        return product;

    }



    public void setProduct(Product product) {

        this.product = product;

    }

}
