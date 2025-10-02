package com.eyuup.service;

import java.util.List;

import com.eyuup.modal.Store;
import com.eyuup.modal.User;
import com.eyuup.payload.dto.StoreDTO;

public interface StoreService {

    StoreDTO createStore(StoreDTO storeDTO , User user);

    StoreDTO getStoreById(Long id);

    List<StoreDTO> getAllStores();

    Store getStoreByAdmin();

    StoreDTO updateStore(Long id,StoreDTO storeDTO);

    StoreDTO deleteStore(Long id);

    StoreDTO getStoreByEmployee();   


}
