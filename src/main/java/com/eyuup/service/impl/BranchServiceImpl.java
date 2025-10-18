package com.eyuup.service.impl;

import org.springframework.stereotype.Service;

import com.eyuup.mapper.BranchMapper;
import com.eyuup.modal.Branch;
import com.eyuup.modal.Store;
import com.eyuup.modal.User;
import com.eyuup.payload.dto.BranchDTO;
import com.eyuup.repository.BranchRepository;
import com.eyuup.repository.StoreRepository;
import com.eyuup.service.BranchService;

import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepository branchRepository;
    private final StoreRepository storeRepository;


    @Override
    public BranchDTO createBranch(BranchDTO branchDTO,Long storeId,User user) throws Exception {
            Store store=storeRepository.findById(storeId).orElseThrow(
                ()->new Exception("store not found ")
            );
            
            Branch savedBranch=BranchMapper.toEntity(branchDTO, store, user);

            return BranchMapper.toDTO(savedBranch, store, user);
    }

    @Override
    public BranchDTO getBranchById(Long branchId,Long storeId,User user) throws Exception {


            Branch branch=branchRepository.findById(branchId).orElseThrow(
                ()->new Exception("branch not found ")
            );

           return BranchMapper.toDTO(branch,branch.getStore(),user) ;
    }

    @Override
    public BranchDTO getBranchByStoreId(Long storeId,User user) throws Exception {

        Branch branch=branchRepository.findyStoreId(storeId);
        if (branch==null) {
            throw new Exception("branch not found");
        }

        return BranchMapper.toDTO(branch, branch.getStore(), user);


    }

    @Override
    public BranchDTO updateBranch(Long branchId,BranchDTO branchDTO,User user) throws Exception {
            Branch branch=branchRepository.findById(branchId).orElseThrow(
                ()->new Exception("branch not found ")
            );
                branch.setName(branchDTO.getName());
                branch.setAddress(branchDTO.getAddress());
                branch.setEmail(branchDTO.getEmail());
                branch.setPhone(branchDTO.getPhone());
                branch.setCloseTime(branchDTO.getCloseTime());
                branch.setOpenTime(branchDTO.getCloseTime());
                branch.setWorkingsDays(branchDTO.getWorkingsDays());

             Branch savedBranch=branchRepository.save(branch);


            return BranchMapper.toDTO(savedBranch, savedBranch.getStore(), user);
    }

    @Override
    public void deleteBranch(Long branchId) throws Exception {
             Branch branch=branchRepository.findById(branchId).orElseThrow(
                ()->new Exception("branch not found ")
            );
           branchRepository.delete(branch); 
    }

}
