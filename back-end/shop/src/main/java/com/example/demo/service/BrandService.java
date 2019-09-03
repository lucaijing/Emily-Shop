package com.example.demo.service;

import com.example.demo.domain.Brand;

import java.util.List;

public interface BrandService {
    Brand findByBraId(Integer braId);
    List<Brand> findAll();
    String CreateP(Brand brand);
    String DeleteP(Integer braId);
}
