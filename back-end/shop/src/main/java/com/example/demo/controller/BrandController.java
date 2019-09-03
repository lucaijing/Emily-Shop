package com.example.demo.controller;

import com.example.demo.domain.Brand;
import com.example.demo.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @PostMapping("/CreateP")
    String CreateP(@RequestBody Brand brand){
        return brandService.CreateP(brand);
    }

    @GetMapping("/FindAll")
    List<Brand> findAll(){return brandService.findAll();}


}
