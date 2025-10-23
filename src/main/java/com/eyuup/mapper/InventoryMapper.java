package com.eyuup.mapper;

import com.eyuup.modal.Branch;
import com.eyuup.modal.Inventory;
import com.eyuup.modal.Product;
import com.eyuup.payload.dto.InventoryDTO;

public class InventoryMapper {
    public static InventoryDTO toDTo(Inventory inventory){
                return InventoryDTO.builder()
                    .branchId(inventory.getBranch()!=null?inventory.getBranch().getId():null)
                    .branch(BranchMapper.toDTO(inventory.getBranch()))
                    .productId(inventory.getProduct()!=null?inventory.getProduct().getId():null)
                    .product(ProductMapper.ToDTO(inventory.getProduct()))
                    .quantity(inventory.getQuantity())
                    .lastUpdatedTime(inventory.getLastUpdatedTime())
                .build();
    }

    public static Inventory toEntity(InventoryDTO inventoryDTO,Branch branch,Product product){
                return Inventory.builder()
                    .branch(branch)
                    .product(product)
                    .quantity(inventoryDTO.getQuantity())
                    .lastUpdatedTime(inventoryDTO.getLastUpdatedTime())
                .build();
    }
}
