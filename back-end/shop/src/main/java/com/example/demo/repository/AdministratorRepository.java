package com.example.demo.repository;

import com.example.demo.domain.Administrator;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;
import java.util.List;

public interface AdministratorRepository extends JpaRepository<Administrator,Integer> {

    Administrator findByAId(Integer aid);

    @Query(value = "UPDATE Administrator a SET a.aPass=?1,a.aPhone=?2 WHERE a.aId=?3")
    @Modifying
    @Transactional
    void updateA(String pass,String phone,Integer aid);//更新密码和电话

    List<Administrator> findAll();


}
