package com.example.demo.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="administrator_table")

public class Administrator {
    @GeneratedValue
    @Id
   // @Column(nullable = false)
    private Integer aId;//主键 管理员号

    @Column
    private String aName;//姓名

    @Column //(nullable = false)
    private String aPass;//管理员密码

    @Column //(nullable = false)
    private String aPhone;//联系电话

    public void setaId(Integer aId){this.aId=aId;}
    public Integer getaId(){return  aId;}

    public void setaName(String aName){this.aName=aName;}
    public String getaName(){return  aName;}

    public void setaPass(String aPass){this.aPass=aPass;}
    public String getaPass(){return  aPass;}

    public void setaPhone(String aPhone){this.aPhone=aPhone;}
    public String getaPhone(){return aPhone;}


}

