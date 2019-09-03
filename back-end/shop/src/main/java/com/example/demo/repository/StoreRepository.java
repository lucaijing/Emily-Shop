package com.example.demo.repository;

import com.example.demo.domain.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface StoreRepository extends JpaRepository<Store,Integer> {
    Store findBySId(Integer sId);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Store s SET s.sNumber=?1 WHERE s.sId=?2")
    void UpdateSNumber(Integer sNumber,Integer sId);//更新库存

    @Transactional
    @Modifying
    @Query(value = "UPDATE Store s SET s.sAddress=?1,s.sNumber=?2 WHERE s.sId=?3")
    void UpdateS(String sAddress,Integer sNumber,Integer sId);//更新仓库

    @Transactional
    @Modifying
    @Query(value = "UPDATE Store s SET s.sNumber=s.sNumber+?1 WHERE s.sId=?2")
    void AddSNumber(Integer sNum,Integer sId);//增加库存
}
