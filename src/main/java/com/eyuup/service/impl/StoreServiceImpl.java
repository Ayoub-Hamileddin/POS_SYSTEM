package com.eyuup.service.impl;

import java.util.List;

import org.apache.catalina.StoreManager;
import org.springframework.stereotype.Service;

import com.eyuup.exceptions.StoreException;
import com.eyuup.mapper.StoreMapper;
import com.eyuup.modal.Store;
import com.eyuup.modal.StoreContact;
import com.eyuup.modal.User;
import com.eyuup.payload.dto.StoreDTO;
import com.eyuup.repository.StoreRepository;
import com.eyuup.service.StoreService;
import com.eyuup.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final UserService userService;


    @Override
    public StoreDTO createStore(StoreDTO storeDTO, User user) {

        Store store=StoreMapper.toEntity(storeDTO, user);

            storeRepository.save(store);


        return StoreMapper.toDTO(store);
    }






    @Override
    public StoreDTO getStoreById(Long id) {

        Store store= storeRepository.findById(id)
        .orElseThrow(
            ()->new StoreException("store not found")
        );

        return  StoreMapper.toDTO(store);
    }





    @Override
    public List<StoreDTO> getAllStores() {
        List<Store> stores= storeRepository.findAll();
        return  stores.stream()
                    .map(StoreMapper::toDTO)
                    .toList();
    }



    @Override
    public Store getStoreByAdmin() {

        User admin=userService.getCurrentUser();

        return storeRepository.findByStoreAdminId(admin.getId());
    }






    @Override
    public StoreDTO updateStore(Long id, StoreDTO storeDTO) {

      User currentUser=userService.getCurrentUser();

      Store existingStore=storeRepository.findByStoreAdminId(currentUser.getId());

      existingStore.setBrand(storeDTO.getBrand());
      existingStore.setDescription(storeDTO.getDescription());

      if (storeDTO.getStoreType()!=null) {
            existingStore.setStoreType(storeDTO.getStoreType());
      }

      if (storeDTO.getStoreContact()!=null) {
           StoreContact contact= StoreContact.builder()
                .address(storeDTO.getStoreContact().getAddress())
                .email(storeDTO.getStoreContact().getEmail())
                .phone(storeDTO.getStoreContact().getPhone())
           .build();
           existingStore.setStoreContact(contact);
      }
      Store updatedStore=storeRepository.save(existingStore);

      return StoreMapper.toDTO(updatedStore);

     


    }







    @Override
    public StoreDTO deleteStore(Long id) {

       Store store=storeRepository.findById(id)
       .orElseThrow(()->new StoreException("store not found"));

        storeRepository.delete(store);

        return StoreMapper.toDTO(store);
    }







    @Override
    public StoreDTO getStoreByEmployee() {

        User currentUser=userService.getCurrentUser();
        return StoreMapper.toDTO(currentUser.getStore());
    }

   





}
