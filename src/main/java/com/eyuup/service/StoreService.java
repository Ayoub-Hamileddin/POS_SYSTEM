package com.eyuup.service;

import java.util.List;

import com.eyuup.domain.StoreStatus;
import com.eyuup.modal.Store;
import com.eyuup.modal.User;
import com.eyuup.payload.dto.StoreDTO;

public interface StoreService {

    StoreDTO createStore(StoreDTO storeDTO , User user);

    StoreDTO getStoreById(Long id);

    List<StoreDTO> getAllStores();

    Store getStoreByAdmin();

    StoreDTO updateStore(Long id,StoreDTO storeDTO);

    void deleteStore(Long id);

    StoreDTO getStoreByEmployee();   

    StoreDTO moderateStore(Long id,StoreStatus status);

}
