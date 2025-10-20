package com.eyuup.mapper;

import java.time.LocalDateTime;

import com.eyuup.modal.Branch;
import com.eyuup.modal.Store;
import com.eyuup.modal.User;
import com.eyuup.payload.dto.BranchDTO;


public class BranchMapper {

    public static BranchDTO toDTO(Branch branch){

        return BranchDTO.builder()
            .Id(branch.getId())
            .name(branch.getName())
            .address(branch.getAddress())
            .email(branch.getEmail())
            .phone(branch.getPhone())
            .workingsDays(branch.getWorkingsDays())
            .closeTime(branch.getCloseTime())
            .openTime(branch.getOpenTime())
            .createdAt(branch.getCreatedAt())
            .updatedAt(branch.getUpdatedAt())
            .storeId(branch.getStore()!=null?branch.getStore().getId():null)

        .build();
    }
    public static Branch toEntity(BranchDTO branchDto,Store store,User user){

        return Branch.builder()
            .Id(branchDto.getId())
            .name(branchDto.getName())
            .address(branchDto.getAddress())
            .email(branchDto.getEmail())
            .phone(branchDto.getPhone())
            .workingsDays(branchDto.getWorkingsDays())
            .closeTime(branchDto.getCloseTime())
            .openTime(branchDto.getOpenTime())
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .store(store)
            .manager(user)

        .build();
    }

}
