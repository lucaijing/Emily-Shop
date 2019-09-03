package com.example.demo.service;

import com.example.demo.domain.Customer;

import java.util.List;

public interface CustomerService {

    String CreateC(Customer customer);
    String DeleteC(Integer cId);
    Customer CheckC(String cName,String cPass);

    Customer findByCId(Integer cId);
    List<Customer> findAll();
    List<Customer> findByCNameLike(String cName);

    String UpdateCPassword(String First_cPassword,String Second_cPassword,Integer cId,String Initial_cPassword);//更新密码
    String UpdateCustomer(String cAddress,String cPhone,Integer cId);//更新基本信息

}
