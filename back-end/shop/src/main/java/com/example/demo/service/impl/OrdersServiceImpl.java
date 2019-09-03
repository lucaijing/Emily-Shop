package com.example.demo.service.impl;

import com.example.demo.domain.Orders;
import com.example.demo.repository.OrdersRepository;
import com.example.demo.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrdersServiceImpl implements OrdersService {

    @Autowired
    private OrdersRepository ordersRepository;
    @Override
    public Orders findByOId(Integer oId) {
        return ordersRepository.findByOId(oId);
    }

    @Override
    public List<Orders> findByCId(Integer cId) {
        return ordersRepository.findByCId(cId);
    }

    @Override
    public List<Orders> findByProId(Integer proId) {
        return ordersRepository.findByProId(proId);
    }

    @Override
    public List<Orders> findAll() {
        return ordersRepository.findAll();
    }

    @Override
    public List<Orders> findByOIsdone(Integer oIsdone) {
        return ordersRepository.findByOIsdone(oIsdone);
    }

    @Override
    public String UpdateOIsdone(Integer oIsdone, Integer oId) {
        boolean flag=false;
        try{
            ordersRepository.UpdateOIsdone(oIsdone,oId);
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
    public String CreateO(Orders orders) {
        boolean flag=false;
        try{
            ordersRepository.save(orders);
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
    public String DeleteO(Integer oId) {
        Orders orders=ordersRepository.findByOId(oId);
        if(orders==null){
            return "null";
        }
        boolean flag=false;
        try {
            ordersRepository.delete(oId);
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
