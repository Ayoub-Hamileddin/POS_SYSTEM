package com.eyuup.service;

import java.util.List;

import com.eyuup.modal.User;
import com.eyuup.payload.dto.BranchDTO;

public interface BranchService {

    
    BranchDTO createBranch(BranchDTO branchDTO,User user) throws Exception;
    
    BranchDTO getBranchById(Long BranchId) throws Exception;

    List<BranchDTO> getBranchesByStoreId(Long storeId) throws Exception;
    
    BranchDTO updateBranch(Long branchId,BranchDTO branchDTO,User user) throws Exception;

    void deleteBranch(Long branchId) throws Exception;


}
