package com.eyuup.payload.dto;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import lombok.Builder;
import lombok.Data;




@Data
@Builder
public class BranchDTO {
   private Long Id;

    private String name ;

    private String address ;

    private String phone ;

    private String email ;

    private List<String> workingsDays;

    private LocalTime openTime;
    private LocalTime closeTime;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    private Long storeId;
    private StoreDTO store;

    private UserDto manager;

}
