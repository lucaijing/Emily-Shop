package com.example.demo.service;

import com.example.demo.domain.Store;

import java.util.List;

public interface StoreService {
    Store findBySid(Integer sId);
    List<Store> findAll();
    String CreateStore(Store store);
    String DeleteStore(Integer sId);
    String UpdateSNumber(Integer sNumber,Integer sId);//更新库存
    String UpdateS(String sAddress,Integer sNumber,Integer sId);
    String AddSNumber(Integer sNum,Integer sId);//增加库存
}
