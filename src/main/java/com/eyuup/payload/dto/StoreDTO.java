package com.eyuup.payload.dto;

import java.time.LocalDateTime;

import com.eyuup.domain.StoreStatus;
import com.eyuup.modal.StoreContact;


import lombok.Data;


@Data
public class StoreDTO {

    private Long  id ;

    private String brand;

    private UserDto storeAdmin;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String description;

    private String storeType;

    private StoreStatus status;

    private StoreContact storeContact ;        
}
