package com.example.demo.service;

import com.example.demo.domain.CustomerProduct;

import java.util.List;

public interface CustomerProductService {

    CustomerProduct findByCproId(Integer cproId);
    List<CustomerProduct> findByProId(Integer proId);
    List<CustomerProduct> findByCId(Integer cId);

    String CreateCB(CustomerProduct customerProduct);
    String DeleteCB(Integer cproId);

}
