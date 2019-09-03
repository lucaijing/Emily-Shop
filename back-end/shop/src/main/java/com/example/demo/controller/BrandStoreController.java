package com.example.demo.controller;


import com.example.demo.domain.BrandStore;
import com.example.demo.service.BrandStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/BrandStore",produces = {"application/json;charset=UTF-8"})
public class BrandStoreController {
    @Autowired
    private BrandStoreService brandStoreService;

    @PostMapping("/CreateBS")
    public String CreatePS(@RequestBody BrandStore brandStore){
        return brandStoreService.CreatePS(brandStore);
    }
    @GetMapping("/DeleteBS/bsId={bsId}")
    public String DeletePS(@PathVariable("bsId") Integer bsId){
        return brandStoreService.DeletePS(bsId);
    }

    @GetMapping("/findAll")
    public List<BrandStore> findAll(){
        return brandStoreService.findAll();
    }
    @GetMapping("/findByBsId/bsId={bsId}")
    public BrandStore findByBsId(@PathVariable("bsId") Integer bsId){
        return brandStoreService.findByBsId(bsId);
    }

    @GetMapping("/findByBsIsdone/bsIsdone={bsIsdone}")
    public List<BrandStore> findByBsIsdone(@PathVariable("bsIsdone") Integer bsIsdone){
        return brandStoreService.findByBsIsdone(bsIsdone);
    }
    @GetMapping("/UpdateBsIsdone/bsIsdone={bsIsdone}bsId={bsId}")
    public String UpdateBsIsdone(@PathVariable("bsIsdone") Integer bsIsdone,
                                 @PathVariable("bsId") Integer bsId){
        return brandStoreService.UpdateBsIsdone(bsIsdone,bsId);
    }
}
