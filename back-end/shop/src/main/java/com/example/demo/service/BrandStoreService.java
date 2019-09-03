package com.example.demo.service;

import com.example.demo.domain.BrandStore;

import java.util.List;

public interface BrandStoreService {
    String CreatePS(BrandStore brandStore);
    String DeletePS(Integer bsId);
    List<BrandStore> findAll();
    BrandStore findByBsId(Integer bsId);
////////////////////////////////////////
//    List<BrandStore> findByBraId(Integer braId);
//    List<BrandStore> findBySId(Integer sId);
///////////////////////////////////////
    List<BrandStore> findByBsIsdone(Integer bsIsdone);
    String UpdateBsIsdone(Integer bsIsdone, Integer bsId);
}
