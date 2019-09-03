package com.example.demo.repository;

import com.example.demo.domain.BrandStore;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface BrandStoreRepository extends JpaRepository<BrandStore,Integer> {
    //根据订单号查询
    BrandStore findByBsId(Integer bsId);

    List<BrandStore> findByBsIsdone(Integer bsIsdone);
    //根据品牌号查询
   // List<BrandStore> findByBraId

    //根据仓库号查询

    @Transactional
    @Modifying
    @Query(value = "UPDATE BrandStore p SET p.bsIsdone=?1 WHERE p.bsId=?2")
    void UpdateBsIsdone(Integer bsIsdone, Integer bsId);//更新是否完成状态
}
