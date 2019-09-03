package com.example.demo.controller;


import com.example.demo.domain.CustomerStore;
import com.example.demo.service.CustomerStoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/CustomerStore",produces = {"application/json;charset=UTF-8"})
public class CustomerStoreController {

    @Autowired
    CustomerStoreService customerStoreService;

    @PostMapping("/CreateCS")
    public String CreateCS(@RequestBody CustomerStore customerStore){
        return customerStoreService.CreateCS(customerStore);
    }
    @GetMapping("/DeleteCS/csId={csId}")
    public String DeleteCS(@PathVariable("csId") Integer csId){
        return customerStoreService.DeleteCS(csId);
    }
    @GetMapping("/findAll")
    public List<CustomerStore> findAll(){
        return customerStoreService.findAll();
    }
    @GetMapping("/findByCsId/csId={csId}")
    public CustomerStore findByCsId(@PathVariable("csId") Integer csId){
        return customerStoreService.findByCsId(csId);
    }

    @GetMapping("/findByCsIsdone/csIsdone={scIsdone}")
    public List<CustomerStore> findByCsIsdone(@PathVariable("csIsdone") Integer csIsdone){
        return customerStoreService.findByCsIsdone(csIsdone);
    }
    @GetMapping("/UpdateCsIsdone/csIsdone={csIsdone}csId={csId}")
    public String UpdateCsIsdone(@PathVariable("csIsdone") Integer csIsdone,
                                 @PathVariable("csId") Integer csId){
        return customerStoreService.UpdateCsIsdone(csIsdone,csId);
    }
}
