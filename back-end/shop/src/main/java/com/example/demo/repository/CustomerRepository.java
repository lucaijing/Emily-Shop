package com.example.demo.repository;

import com.example.demo.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

    Customer findByCId(Integer cId);
    Customer findByCName(String cName);

    @Transactional
    @Modifying
    @Query(value = "UPDATE Customer c SET c.cPassword=?1 WHERE c.cId=?2")
    void UpdateCPassword(String cPassword,Integer cId);//更新密码
    @Transactional
    @Modifying
    @Query(value = "UPDATE Customer c SET c.cAddress=?1,c.cPhone=?2 WHERE c.cId=?3")
    void UpdateCustomer(String cAddress,String cPhone,Integer cId);//更新基本信息

    @Query("SELECT c FROM Customer c WHERE c.cName LIKE %?1%")
    List<Customer> findByCNameLIKE(String cName);//姓名模糊查找
}
