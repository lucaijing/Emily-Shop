package com.example.demo.controller;

import com.example.demo.domain.Store;
import com.example.demo.service.StoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Store",produces = {"application/json;charset=UTF-8"})
public class StoreController {

    @Autowired
    private StoreService storeService;

    @PostMapping("/CreateStore")
    public String CreateStore(@RequestBody Store store){
        return storeService.CreateStore(store);
    }
    @GetMapping("/DeleteStore/sId={sId}")
    public String DeleteStore(@PathVariable("sId") Integer sId){
        return storeService.DeleteStore(sId);
    }
    @GetMapping("/findAll")
    public List<Store> findAll(){
        return storeService.findAll();
    }
    @GetMapping("/findBySId/sId={sId}")
    public Store findBySId(@PathVariable("sId") Integer sId){
        return storeService.findBySid(sId);
    }

    @GetMapping("/UpdateS/sAddress={sAddress}sNumber={sNumber}sId={sId}")
    public String UpdateS(@PathVariable("sAddress") String sAddress,
                          @PathVariable("sNumber") Integer sNumber,
                          @PathVariable("sId") Integer sId){
        return storeService.UpdateS(sAddress,sNumber,sId);
    }


    @GetMapping("/UpdateSNumber/sNumber={sNumber}sId={sId}")
    public String UpdateSNumber(@PathVariable("sNumber") Integer sNumber,
                                @PathVariable("sId") Integer sId){
        return storeService.UpdateSNumber(sNumber,sId);
    }

    @GetMapping("/AddSNumber/sNumb={sNumb}sId={sId}")
    public String AddSNumber(@PathVariable("sNumb") Integer sNumb,
                                @PathVariable("sId") Integer sId){
        return storeService.AddSNumber(sNumb,sId);
    }
}

