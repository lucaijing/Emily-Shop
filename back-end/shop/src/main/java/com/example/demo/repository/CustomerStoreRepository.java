package com.example.demo.repository;

import com.example.demo.domain.CustomerStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomerStoreRepository extends JpaRepository<CustomerStore,Integer> {

    List<CustomerStore> findAll();
    CustomerStore findByCsId(Integer csId);

    List<CustomerStore> findByCsIsdone(Integer csIsdone);

    @Transactional
    @Modifying
    @Query(value = "UPDATE CustomerStore c SET c.csIsdone=?1 WHERE c.csId=?2")
    void UpdateCsIsdone(Integer csIsdone,Integer csId);//更新是否完成状态

}
