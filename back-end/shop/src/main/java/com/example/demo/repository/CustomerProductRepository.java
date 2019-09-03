package com.example.demo.repository;
import com.example.demo.domain.CustomerProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface CustomerProductRepository extends JpaRepository<CustomerProduct,Integer> {

    CustomerProduct findByCproId(Integer cproId);

    @Query(value = "SELECT cb FROM CustomerProduct cb WHERE cb.product.proId=?1")
    List<CustomerProduct> findByProId(Integer proId);

    @Query(value = "SELECT cb FROM CustomerProduct cb WHERE cb.customer.cId=?1")
    List<CustomerProduct> findByCId(Integer cId);

}
