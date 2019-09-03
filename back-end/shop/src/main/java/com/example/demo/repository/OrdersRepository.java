package com.example.demo.repository;

import com.example.demo.domain.Orders;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface OrdersRepository extends JpaRepository<Orders,Integer> {

    Orders findByOId(Integer oId);
    List<Orders> findByCId(Integer cId);
    List<Orders> findByProId(Integer proId);

    List<Orders> findByOIsdone(Integer oIsdone);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Orders o SET o.oIsdone=?1 WHERE o.oId=?2")
    void UpdateOIsdone(Integer oIsdone,Integer oId);//更新是否完成状态

}
