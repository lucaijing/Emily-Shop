package com.example.demo.controller;

import com.example.demo.domain.Orders;
import com.example.demo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/Orders")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    @PostMapping("/CreateO")
    String CreateO(@RequestBody Orders orders){
        return ordersService.CreateO(orders);
    }

    @GetMapping("/DeleteO/oId={oId}")
    String DeleteO(@PathVariable("oId") Integer oId){
        return ordersService.DeleteO(oId);
    }

    @GetMapping("/findByOId/oId={oId}")
    Orders findByOId(@PathVariable("oId") Integer oId){
        return ordersService.findByOId(oId);
    }
    @GetMapping("/findByCId/cId={cId}")
    List<Orders> findByCId(@PathVariable("cId") Integer cId){
        return ordersService.findByCId(cId);
    }
    @GetMapping("/findByProId/proId={proId}")
    List<Orders> findByProId(@PathVariable("proId") Integer proId){
        return ordersService.findByProId(proId);
    }

    @GetMapping("/findByOIsdone/oIsdone={oIsdone}")
    List<Orders> findByOIsdone(@PathVariable("oIsdone") Integer oIsdone){
        return ordersService.findByOIsdone(oIsdone);
    }
    @GetMapping("/UpdateOIsdone/oIsdone={oIsdone}oId={oId}")
    String UpdateOIsdone(@PathVariable("oIsdone") Integer oIsdone,
                         @PathVariable("oId") Integer oId){
        return ordersService.UpdateOIsdone(oIsdone,oId);
    }
}
