package com.example.demo.controller;


import com.example.demo.domain.Administrator;
import com.example.demo.service.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Administrator",produces = {"application/json;charset=UTF-8"})
public class AdministratorController {

    @Autowired
    private AdministratorService administratorService;

    @PostMapping("/CreateA")//注册
    public String CreatA(@RequestBody Administrator administrator){
        return administratorService.CreateA(administrator);
    }

   @GetMapping("/DeleteA/aId={aId}")//删除
    public String DeleteA(@PathVariable("aId") Integer aId){
        return administratorService.DeleteA(aId);

    }

    @GetMapping("/UpdateA/aPass={aPass}aPhone={aPhone}aId={aId}")//更新
    public String upadteA(@PathVariable("aPass") String aPass,
                        @PathVariable("aPhone") String aPhone,
                        @PathVariable("aId") Integer aId){
        return administratorService.updateA(aPass,aPhone,aId);

    }

    @GetMapping("/findinfo")
    public List<Administrator> findinfo(){
        return administratorService.findAll();
    }


    @GetMapping("/CheckA/aId={aId}aPass={aPass}")
    public String CheckA(@PathVariable("aId") Integer aId,
                         @PathVariable("aPass") String aPass){
        return administratorService.CheckA(aId,aPass);
    }
}
