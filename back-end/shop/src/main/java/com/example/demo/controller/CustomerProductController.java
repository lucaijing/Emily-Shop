package com.example.demo.controller;

import com.example.demo.domain.CustomerProduct;
import com.example.demo.service.CustomerProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/CustomerProduct")
public class CustomerProductController {

    @Autowired
    private CustomerProductService customerProductService;

    @PostMapping("/CreateCB")
    public String CreateCB(@RequestBody CustomerProduct customerProduct){
        return customerProductService.CreateCB(customerProduct);
    }

    @GetMapping("/DeleteCB/cproId={cproId}")
    public String DeleteCB(@PathVariable("cproId") Integer cproId){
        return customerProductService.DeleteCB(cproId);
    }

    @GetMapping("/findByCproId/cproId={cproId}")
    public CustomerProduct findByCproId(@PathVariable("cproId") Integer cproId){
        return customerProductService.findByCproId(cproId);
    }

    @GetMapping("/findByProId/proId={proId}")
    public List<CustomerProduct> findByProId(@PathVariable("proId") Integer proId){
        return customerProductService.findByProId(proId);
    }

    @GetMapping("/findByCId/cId={cId}")
    public List<CustomerProduct> findByCId(@PathVariable("cId") Integer cId){
        return customerProductService.findByCId(cId);
    }
}
