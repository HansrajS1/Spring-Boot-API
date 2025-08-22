package com.example.demo.repo;

import com.example.demo.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
        List<Product> findByReleaseDateBetween(Date startDate, Date endDate);
        List<Product> findByReleaseDateGreaterThanEqual(Date startDate);
        List<Product> findByReleaseDateLessThanEqual(Date endDate);
}

