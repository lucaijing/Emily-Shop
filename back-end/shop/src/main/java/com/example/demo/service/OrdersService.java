package com.example.demo.service;

import com.example.demo.domain.Orders;

import java.util.List;

public interface OrdersService {

    Orders findByOId(Integer oId);
    List<Orders> findByCId(Integer cId);
    List<Orders> findByProId(Integer proId);
    List<Orders> findAll();

    List<Orders> findByOIsdone(Integer oIsdone);
    String UpdateOIsdone(Integer oIsdone,Integer oId);

    String CreateO(Orders orders);
    String DeleteO(Integer oId);
}
