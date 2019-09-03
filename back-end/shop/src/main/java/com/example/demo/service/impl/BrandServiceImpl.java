package com.example.demo.service.impl;

import com.example.demo.domain.Brand;
import com.example.demo.repository.BrandRepository;
import com.example.demo.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandServiceImpl implements BrandService {

    @Autowired
    private BrandRepository brandRepository;
    @Override
    public Brand findByBraId(Integer braId) {
        return brandRepository.findByBraId(braId);
    }

    @Override
    public List<Brand> findAll() {
        return brandRepository.findAll();
    }

    @Override
    public String CreateP(Brand brand) {
        boolean flag=false;
        try{
            brandRepository.save(brand);
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
    public String DeleteP(Integer braId) {
        Brand brand=brandRepository.findByBraId(braId);
        if(brand==null){
            return "null";
        }
        boolean flag=false;
        try{
            brandRepository.delete(braId);
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
