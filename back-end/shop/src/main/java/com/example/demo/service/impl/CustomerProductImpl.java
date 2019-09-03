package com.example.demo.service.impl;

import com.example.demo.domain.CustomerProduct;
import com.example.demo.repository.CustomerProductRepository;
import com.example.demo.service.CustomerProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerProductImpl implements CustomerProductService {

    @Autowired
    private CustomerProductRepository customerProductRepository;

    @Override
    public CustomerProduct findByCproId(Integer cproId) {
        return customerProductRepository.findByCproId(cproId);
    }

    @Override
    public List<CustomerProduct> findByProId(Integer proId) {
        return customerProductRepository.findByProId(proId);
    }

    @Override
    public List<CustomerProduct> findByCId(Integer cId) {
        return customerProductRepository.findByCId(cId);
    }

    @Override
    public String CreateCB(CustomerProduct customerProduct) {
        boolean flag=false;
        try{
            customerProductRepository.save(customerProduct);
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
    public String DeleteCB(Integer cproId) {
        boolean flag=false;
        try {
            customerProductRepository.delete(cproId);
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
