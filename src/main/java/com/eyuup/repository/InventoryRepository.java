package com.eyuup.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyuup.modal.Inventory;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {
   Optional<List<Inventory>> findByProductId(Long Productid);
    
   Optional<List<Inventory>> findByBranchId(Long Productid);
}
