package com.eyuup.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyuup.modal.Store;

public interface StoreRepository extends JpaRepository<Store,Long> {
   Optional<Store> findByStoreAdminId(Long adminId);
}
