package com.eyuup.payload.dto;

import java.time.LocalDateTime;


import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class InventoryDTO {
    private Long id;

    private Long branchId; 
    private BranchDTO branch;


    private Long productId;
    private ProductDTO product ;

    private Integer quantity;

    private LocalDateTime lastUpdatedTime;

}
