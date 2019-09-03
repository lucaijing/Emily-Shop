package com.example.demo.repository;

import com.example.demo.domain.Brand;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BrandRepository extends JpaRepository<Brand,Integer> {
    Brand findByBraId(Integer braId);
}
