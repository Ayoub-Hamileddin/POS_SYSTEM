package com.eyuup.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eyuup.modal.Store;

public interface StoreRepository extends JpaRepository<Store,Long> {
    Store findByStoreAdminId(Long adminId);
}
