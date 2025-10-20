package com.eyuup.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eyuup.domain.CheckAuthority;
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
    public BranchDTO createBranch(BranchDTO branchDTO,User user) throws Exception {

            Store store=storeRepository.findById(branchDTO.getStoreId()).orElseThrow(
                ()->new Exception("store not found ")
            );

                CheckAuthority.isAuthorized(user, store);

            Branch branch = BranchMapper.toEntity(branchDTO, store, user);

            Branch savedBranch=branchRepository.save(branch);
            
            return BranchMapper.toDTO(savedBranch, store, user);
    }

    @Override
    public BranchDTO getBranchById(Long branchId) throws Exception {


            Branch branch=branchRepository.findById(branchId).orElseThrow(
                ()->new Exception("branch not found ")
            );

           return BranchMapper.toDTO(branch,branch.getStore(),branch.getManager()) ;
    }

    @Override
    public List<BranchDTO> getBranchByStoreId(Long storeId) throws Exception {

       List <Branch> branches=branchRepository.findByStoreId(storeId);
        if (branches==null) {
            throw new Exception("branch not found");
        }

        return branches.stream()
               .map(branch -> BranchMapper.toDTO(branch, branch.getStore(),branch.getManager()))
               .toList();
                     


    }

    @Override
    public BranchDTO updateBranch(Long branchId,BranchDTO branchDTO,User user) throws Exception {
            Branch branch=branchRepository.findById(branchId).orElseThrow(
                ()->new Exception("branch not found ")
            );
            
            CheckAuthority.isAuthorized(user, branch.getStore());  

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
