package com.example.demo.service;

import com.example.demo.domain.CustomerStore;

import java.util.List;

public interface CustomerStoreService {

    String CreateCS(CustomerStore customerStore);
    String DeleteCS(Integer csId);
    List<CustomerStore> findAll();
    CustomerStore findByCsId(Integer csId);

    List<CustomerStore> findByCsIsdone(Integer csIsdone);
    String UpdateCsIsdone(Integer csIsdone,Integer csId);
}
