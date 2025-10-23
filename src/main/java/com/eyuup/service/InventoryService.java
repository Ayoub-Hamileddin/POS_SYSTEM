package com.eyuup.service;

import java.nio.file.AccessDeniedException;
import java.util.List;

import com.eyuup.modal.User;
import com.eyuup.payload.dto.InventoryDTO;

public interface InventoryService {
    
    InventoryDTO createInventory(InventoryDTO inventoryDTO,User user ) throws AccessDeniedException, Exception; 

    InventoryDTO updateInventory(Long inventoryId,InventoryDTO inventoryDTO,User user ) throws Exception; 

    void delete(Long inventoryId,User user ) throws AccessDeniedException, Exception; 

    InventoryDTO getInventoriesById(Long inventoryId) throws Exception; 

    List<InventoryDTO> getInventoryByProductId(Long ProductId ) throws Exception;

    List<InventoryDTO> getInventoryByBranchId(Long BranchId);



}
