package com.example.demo.service.impl;

import com.example.demo.domain.CustomerStore;
import com.example.demo.repository.CustomerStoreRepository;
import com.example.demo.service.CustomerStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerStoreImpl implements CustomerStoreService {

    @Autowired
    CustomerStoreRepository customerStoreRepository;
    @Override
    public String CreateCS(CustomerStore customerStore) {
        boolean flag=false;
        try{
            customerStoreRepository.save(customerStore);
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
    public String DeleteCS(Integer csId) {
        CustomerStore customerStore=customerStoreRepository.findByCsId(csId);
        if(customerStore==null){
            return "null";
        }
        boolean flag=false;
        try{
            customerStoreRepository.delete(csId);
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
    public List<CustomerStore> findAll() {
        return customerStoreRepository.findAll();
    }

    @Override
    public CustomerStore findByCsId(Integer csId) {
        return customerStoreRepository.findByCsId(csId);
    }

    @Override
    public List<CustomerStore> findByCsIsdone(Integer csIsdone) {
        return customerStoreRepository.findByCsIsdone(csIsdone);
    }

    @Override
    public String UpdateCsIsdone(Integer csIsdone, Integer csId) {
        boolean flag=false;
        try{
            customerStoreRepository.UpdateCsIsdone(csIsdone,csId);
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
