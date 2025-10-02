package com.eyuup.mapper;

import com.eyuup.modal.Store;
import com.eyuup.modal.User;
import com.eyuup.payload.dto.StoreDTO;

public class StoreMapper {

    public static StoreDTO toDTO (Store store){

            StoreDTO storeDTO= new StoreDTO();
            storeDTO.setId(store.getId());
            storeDTO.setBrand(store.getBrand()); 
            storeDTO.setStoreAdmin(UserMapper.ToDTO(store.getStoreAdmin()));
            storeDTO.setDescription(store.getDescription());
            storeDTO.setStoreType(store.getStoreType());
            storeDTO.setStatus(store.getStatus());
            storeDTO.setStoreContact(store.getStoreContact());
            storeDTO.setCreatedAt(store.getCreatedAt());
            storeDTO.setUpdatedAt(store.getUpdatedAt());



        return storeDTO;

    }


    public static Store toEntity (StoreDTO storeDTO,User storeAdmin){

            Store store= new Store();


            store.setId(storeDTO.getId());
            store.setBrand(storeDTO.getBrand()); 
            store.setStoreAdmin(storeAdmin);
            store.setDescription(storeDTO.getDescription());
            store.setStoreType(storeDTO.getStoreType());
            store.setStatus(storeDTO.getStatus());
            store.setStoreContact(storeDTO.getStoreContact());
            store.setCreatedAt(storeDTO.getCreatedAt());
            store.setUpdatedAt(storeDTO.getUpdatedAt());


        return store;

    }


}
