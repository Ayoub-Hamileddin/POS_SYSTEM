package com.eyuup.service.impl;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.eyuup.mapper.InventoryMapper;
import com.eyuup.modal.Branch;
import com.eyuup.modal.Inventory;
import com.eyuup.modal.Product;
import com.eyuup.modal.Store;
import com.eyuup.modal.User;
import com.eyuup.payload.dto.InventoryDTO;
import com.eyuup.repository.BranchRepository;
import com.eyuup.repository.InventoryRepository;
import com.eyuup.repository.ProductRepository;
import com.eyuup.repository.StoreRepository;
import com.eyuup.service.InventoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService {
     
    private final ProductRepository productRepository;
    private final BranchRepository branchRepository;
    private final StoreRepository storeRepository;
    private final InventoryRepository inventoryRepository;
    
    @Override
    public InventoryDTO createInventory(InventoryDTO inventoryDTO, User user) throws Exception {
       Store store=storeRepository.findByStoreAdminId(user.getId()).orElseThrow(
            ()->new AccessDeniedException("you are not the manager of this store")
        );

       Branch branch=branchRepository.findById(inventoryDTO.getBranchId()).orElseThrow(
           ()->new Exception("barnch not found")
        );

       Product product=productRepository.findById(inventoryDTO.getProductId()).orElseThrow(
           ()->new Exception("barnch not found")
        );

        Inventory inventory=InventoryMapper.toEntity(inventoryDTO,branch,product);

        Inventory inventorySaved= inventoryRepository.save(inventory);

        return InventoryMapper.toDTo(inventorySaved);
    }

    @Override
    public InventoryDTO updateInventory(Long inventoryId, InventoryDTO inventoryDTO, User user) throws Exception {
             Store store=storeRepository.findByStoreAdminId(user.getId()).orElseThrow(
                    ()->new AccessDeniedException("you are not the manager of this store")
            );


            Inventory updatingInventory=inventoryRepository.findById(inventoryId).orElseThrow(
                ()-> new Exception("inventory not found")
            );

            updatingInventory.setQuantity(inventoryDTO.getQuantity());
            updatingInventory.setLastUpdatedTime(LocalDateTime.now());

            return InventoryMapper.toDTo(inventoryRepository.save(updatingInventory));

    }

    @Override
    public void delete(Long inventoryId, User user) throws Exception {
        Store store=storeRepository.findByStoreAdminId(user.getId()).orElseThrow(
            ()->new Exception("you are not the manager of this store ")
        );

        Inventory inventory=inventoryRepository.findById(inventoryId).orElseThrow(
                ()-> new Exception("inventory not found")
            );

        inventoryRepository.delete(inventory);    
        
    }

    @Override
    public InventoryDTO getInventoryById(Long inventoryId) throws Exception {

        Inventory inventory=inventoryRepository.findById(inventoryId).orElseThrow(
                ()-> new Exception("inventory not found")
        );   
        return InventoryMapper.toDTo(inventory);
        
        }

    @Override
    public List<InventoryDTO> getInventoryByProductId(Long ProductId) throws Exception {
         List<Inventory> inventories=inventoryRepository.findByProductId(ProductId).orElseThrow(
                ()-> new Exception("inventory not found")
        );   
        return inventories.stream()
            .map(invetory->InventoryMapper.toDTo(invetory))
            .toList();
    }

    @Override
    public List<InventoryDTO> getInventoryByBranchId(Long BranchId) throws Exception {
        List<Inventory> inventories=inventoryRepository.findByBranchId(BranchId).orElseThrow(
            ()->new Exception("Inventery Not Found")
        );
        
         return inventories.stream()
            .map(invetory->InventoryMapper.toDTo(invetory))
            .toList();
    }

}
