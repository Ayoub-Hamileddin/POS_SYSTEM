package com.eyuup.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.eyuup.modal.Product;

public interface ProductRepository extends JpaRepository<Product,Long > {
    List<Product> findByStoreId(Long storeId);

    @Query(
            "Select p from Product p" +
            
            " where p.store.id= :storeId And (" + 

            " LOWER(p.name) Like LOWER(CONCAT('%', :query ,'%')) OR " +

            " LOWER(p.brand) Like LOWER(CONCAT('%', :query ,'%')) OR " +

            " LOWER(p.sku) Like LOWER(CONCAT('%', :query ,'%')) " +

                ")"
    )
    List<Product> searchByKeyword(@Param("storeId") Long id,@Param("query") String keyword);
}
