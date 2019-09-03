package com.example.demo.service;

import com.example.demo.domain.Administrator;

import java.util.List;

public interface AdministratorService {

    String updateA(String pass,String phone,Integer aid);//更新密码和电话
    String CreateA(Administrator administrator);//注册
    String DeleteA(Integer aId);//删除
    List<Administrator> findAll();//查找所有\
    String CheckA(Integer aId,String aPass);//管理员登录
}
