package com.eyuup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyuup.modal.Branch;

public interface BranchRepository extends JpaRepository<Branch,Long> {
    // getBranchByStoreId -> JPQL .
    Branch findyStoreId(Long storeId);
}
