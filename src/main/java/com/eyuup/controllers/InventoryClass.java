package com.eyuup.controllers;

import java.nio.file.AccessDeniedException;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyuup.modal.User;
import com.eyuup.payload.dto.InventoryDTO;
import com.eyuup.payload.response.ApiResponse;
import com.eyuup.service.InventoryService;
import com.eyuup.service.UserService;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequiredArgsConstructor
@RequestMapping("api/inventories")
public class InventoryClass {
    private final InventoryService inventoryService;
    private final UserService userService;


    @PostMapping
    public ResponseEntity<InventoryDTO> createInventory(@RequestBody InventoryDTO inventoryDTO,
                                                            @RequestHeader("Authorization") String jwt) throws AccessDeniedException, Exception {
       User user = userService.getUserFromJwtToken(jwt);

       return ResponseEntity.ok(inventoryService.createInventory(inventoryDTO, user));
       

    }


    @PutMapping("/{id}")
    public ResponseEntity<InventoryDTO> updateInventory(@RequestBody InventoryDTO inventoryDTO,
                                                            @PathVariable("id") Long id ,
                                                            @RequestHeader("Authorization") String jwt) throws AccessDeniedException, Exception {
       User user = userService.getUserFromJwtToken(jwt);

       return ResponseEntity.ok(inventoryService.updateInventory(id, inventoryDTO, user));
       

    }


    @DeleteMapping("/{id}")
    public ApiResponse deleteInventory(@PathVariable("id") Long id ,
                                                            @RequestHeader("Authorization") String jwt) throws AccessDeniedException, Exception {
      User user = userService.getUserFromJwtToken(jwt);

      inventoryService.delete(id, user);

     ApiResponse apiResponse= new ApiResponse();
            apiResponse.setMessage("inventory deleted successfuly");

       return apiResponse;
    }


    @GetMapping("/{id}")
    public ResponseEntity<InventoryDTO> getInventoriesById(@PathVariable("id") Long id ) throws Exception{
            return ResponseEntity.ok(inventoryService.getInventoryById(id));
    }
    

    @GetMapping("/product/{id}")
    public ResponseEntity<List<InventoryDTO>> getInventoriesByProductId(@PathVariable("id") Long id ) throws Exception{
            return ResponseEntity.ok(inventoryService.getInventoryByProductId(id));
    }


    @GetMapping("/branch/{id}")
    public ResponseEntity<List<InventoryDTO>> getInventoriesByBranchId(@PathVariable("id") Long id ) throws Exception{
            return ResponseEntity.ok(inventoryService.getInventoryByBranchId(id));
    }

    



    
}
