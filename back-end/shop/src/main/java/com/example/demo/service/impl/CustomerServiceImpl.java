package com.example.demo.service.impl;

import com.example.demo.domain.Customer;
import com.example.demo.repository.CustomerRepository;
import com.example.demo.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Override//不能存在同名用户
    public String CreateC(Customer customer) {
        Customer customer1=customerRepository.findByCName(customer.getcName());
        if(customer1!=null){
            return "Already_Exist";//不能存在同名用户
        }

        boolean flag=false;
        try{
            customerRepository.save(customer);
            flag=true;
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        if(flag){
            return "true";
        }
        return "false";
    }

    @Override
    public String DeleteC(Integer cId) {
        Customer customer=findByCId(cId);
        if(customer==null){
            return "null";
        }
        boolean flag=false;
        try{
            customerRepository.delete(cId);
            flag=true;
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        if(flag){
            return "true";
        }
        return "false";
    }

    @Override//确认用户是否登录成功
    public Customer CheckC(String cName, String cPass) {
        Customer customer=customerRepository.findByCName(cName);
        if(customer==null){
            return null;
        }
        if(customer.getcPassword().equals(cPass)){

            return customer;//登录成功
        }
        return null;
    }

    @Override
    public Customer findByCId(Integer cId) {
        return customerRepository.findByCId(cId);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public List<Customer> findByCNameLike(String cName) {
        return customerRepository.findByCNameLIKE(cName);
    }

    @Override//更改密码
    public String UpdateCPassword(String First_cPassword,String Second_cPassword,Integer cId,String Initial_cPassword) {
        Customer customer=customerRepository.findByCId(cId);
        if(!Initial_cPassword.equals(customer.getcPassword())){
            return "Initial_false";//密码验证失败
        }
        if(!First_cPassword.equals(Second_cPassword)){
            return "Second_false";//两遍输入不一致
        }

        boolean flag=false;
        try{
            customerRepository.UpdateCPassword(Second_cPassword,cId);
            flag=true;
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        if(flag){
            return "true";
        }
        return "false";
    }

    @Override
    public String UpdateCustomer(String cAddress,String cPhone,Integer cId) {
        boolean flag=false;
        try{
            customerRepository.UpdateCustomer(cAddress,cPhone,cId);
            flag=true;
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        if(flag){
            return "true";
        }
        return "false";
    }
}
