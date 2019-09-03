package com.example.demo.service.impl;

import com.example.demo.domain.BrandStore;
import com.example.demo.repository.BrandStoreRepository;
import com.example.demo.service.BrandStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandStoreImpl implements BrandStoreService {

    @Autowired
    private BrandStoreRepository brandStoreRepository;
    @Override
    public String CreatePS(BrandStore brandStore) {
        boolean flag=true;
        try{
            brandStoreRepository.save(brandStore);
            flag=false;
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
    public String DeletePS(Integer bsId) {
        BrandStore brandStore=brandStoreRepository.findByBsId(bsId);
        if(brandStore==null){
            return "null";
        }
        boolean flag=false;
        try{
            brandStoreRepository.delete(bsId);
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
    public List<BrandStore> findAll() {
        return brandStoreRepository.findAll();
    }

    @Override
    public BrandStore findByBsId(Integer bsId) {
        return brandStoreRepository.findByBsId(bsId);
    }

    @Override
    public List<BrandStore> findByBsIsdone(Integer bsIsdone) {
        return brandStoreRepository.findByBsIsdone(bsIsdone);
    }

    @Override
    public String UpdateBsIsdone(Integer bsIsdone, Integer bsId) {
        boolean flag=false;
        try{
            brandStoreRepository.UpdateBsIsdone(bsIsdone,bsId);
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
