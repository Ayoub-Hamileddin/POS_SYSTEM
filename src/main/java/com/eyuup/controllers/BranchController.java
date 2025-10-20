package com.eyuup.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyuup.modal.User;
import com.eyuup.payload.dto.BranchDTO;
import com.eyuup.payload.response.ApiResponse;
import com.eyuup.service.BranchService;
import com.eyuup.service.UserService;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/branches")
public class BranchController {

    private final BranchService branchService;
    private final UserService userService;


    @PostMapping
    public ResponseEntity<BranchDTO> createBranch(@RequestBody BranchDTO branchDTO,
                                                        @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(branchService.createBranch(branchDTO, user));
    }


    @GetMapping("/{branchId}")
    public ResponseEntity<BranchDTO> getBranchById(@PathVariable("branchId") Long  branchId,
                                                        @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(branchService.getBranchById(branchId));
    }



    @GetMapping("store/{storeId}")
    public ResponseEntity<List<BranchDTO>> getBranchBystoreId(@PathVariable("storeId") Long  storeId,
                                                        @RequestHeader("Authorization") String jwt) throws Exception {
        return ResponseEntity.ok(branchService.getBranchByStoreId(storeId));
    }



    @PutMapping("/{branchId}")
    public ResponseEntity<BranchDTO> updateBranch(@PathVariable("branchId") Long  branchId,
                                                        @RequestBody BranchDTO branchDTO,
                                                        @RequestHeader("Authorization") String jwt) throws Exception {
        User user=userService.getUserFromJwtToken(jwt);
        return ResponseEntity.ok(branchService.updateBranch(branchId,branchDTO,user));
    }

    
    @DeleteMapping("/{branchId}")
    public ApiResponse deleteBranch(@PathVariable("branchId") Long  branchId) throws Exception {
        ApiResponse apiResponse=new ApiResponse();
                branchService.deleteBranch(branchId);
                apiResponse.setMessage("the branch with the id: "+ branchId + "deleted");
        return apiResponse;
    }
    

}
