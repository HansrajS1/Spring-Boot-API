package com.example.demo.repo;

import com.example.demo.Model.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {

        @Query("SELECT p FROM Product p " +
                        "WHERE (:startDate IS NULL OR p.releaseDate >= :startDate) " +
                        "AND (:endDate IS NULL OR p.releaseDate <= :endDate) " +
                        "AND (:brands IS NULL OR p.brand IN :brands)")
        List<Product> findProductsByFilters(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate,
                        @Param("brands") List<String> brands);

        @Query("SELECT p FROM Product p " +
                        "WHERE (:startDate IS NULL OR p.releaseDate >= :startDate) " +
                        "AND (:endDate IS NULL OR p.releaseDate <= :endDate)")
        List<Product> findProductsByDateFilters(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate);

        @Query("SELECT p FROM Product p " +
                        "WHERE (:startDate IS NULL OR p.releaseDate >= :startDate) " +
                        "AND (:endDate IS NULL OR p.releaseDate <= :endDate) " +
                        "AND (:brands IS NULL OR p.brand IN :brands)")
        Page<Product> findProductsByFiltersWithPagination(@Param("startDate") LocalDate startDate,
                        @Param("endDate") LocalDate endDate,
                        @Param("brands") List<String> brands,
                        Pageable pageable);
}
