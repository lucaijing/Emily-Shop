package com.example.demo.service.impl;

import com.example.demo.domain.Store;
import com.example.demo.repository.StoreRepository;
import com.example.demo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StoreServiceImpl implements StoreService {

    @Autowired
    private StoreRepository storeRepository;

    @Override
    public Store findBySid(Integer sId) {
        return storeRepository.findBySId(sId);
    }
    @Override
    public List<Store> findAll(){
        return storeRepository.findAll();
    }
    @Override
    public String CreateStore(Store store){
        boolean flag=false;
        try{
            storeRepository.save(store);
            flag=true;
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
        if(flag){
            return "true";
        }
        else {
            return "false";
        }
    }
    @Override
    public String DeleteStore(Integer sId){
        Store store=storeRepository.findBySId(sId);
        if(store==null){
            return "null";
        }
        boolean flag=false;
        try{
            storeRepository.delete(sId);
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
    public String UpdateSNumber(Integer sNumber, Integer sId) {
        Store store=findBySid(sId);
        if(store==null){
            return "null";
        }
        boolean flag=false;
        try{
            storeRepository.UpdateSNumber(sNumber,sId);
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
    public String UpdateS(String sAddress, Integer sNumber, Integer sId) {

        boolean flag=false;
        try{
            storeRepository.UpdateS(sAddress,sNumber,sId);
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
    public String AddSNumber(Integer sNum, Integer sId) {
        boolean flag=false;
        try{
            storeRepository.AddSNumber(sNum,sId);
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
