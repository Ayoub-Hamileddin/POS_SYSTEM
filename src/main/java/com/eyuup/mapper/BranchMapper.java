package com.eyuup.mapper;

import com.eyuup.modal.Branch;
import com.eyuup.modal.Store;
import com.eyuup.modal.User;
import com.eyuup.payload.dto.BranchDTO;


public class BranchMapper {

    public static BranchDTO toDTO(Branch branch,Store store,User user){

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
            .store(StoreMapper.toDTO(store))
            .manager(UserMapper.ToDTO(user))

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
            .createdAt(branchDto.getCreatedAt())
            .updatedAt(branchDto.getUpdatedAt())
            .store(store)
            .manager(user)

        .build();
    }

}
