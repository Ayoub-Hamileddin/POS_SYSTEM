package com.eyuup.service;

import com.eyuup.modal.User;
import com.eyuup.payload.dto.BranchDTO;

public interface BranchService {

    
    BranchDTO createBranch(BranchDTO branchDTO,Long storeId,User user) throws Exception;
    
    BranchDTO getBranchById(Long BranchId,Long storeId,User user) throws Exception;

    BranchDTO getBranchByStoreId(Long storeId,User user) throws Exception;
    
    BranchDTO updateBranch(Long branchId,BranchDTO branchDTO,User user) throws Exception;

    void deleteBranch(Long branchId) throws Exception;


}
