package com.eyuup.controllers;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eyuup.domain.StoreStatus;
import com.eyuup.modal.Store;
import com.eyuup.modal.User;
import com.eyuup.payload.dto.StoreDTO;
import com.eyuup.payload.response.ApiResponse;
import com.eyuup.service.StoreService;
import com.eyuup.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;
    private final UserService userService;


    
    @PostMapping
    public ResponseEntity<StoreDTO> createStore(@RequestBody StoreDTO storeDTO,
                                            @RequestHeader("Authorization") String jwt){

         User user=userService.getUserFromJwtToken(jwt);

         return ResponseEntity.ok(storeService.createStore(storeDTO, user));

    }



    @GetMapping("/{id}")
    public ResponseEntity<StoreDTO> getStoreById(

        @PathVariable Long  id,
        @RequestHeader("Authorization") String jwt){


         return ResponseEntity.ok(storeService.getStoreById(id));

    }



    @GetMapping
    public ResponseEntity<List<StoreDTO>> getAllStores(){

         return ResponseEntity.ok(storeService.getAllStores());

    }



    @GetMapping("/admin")
    public ResponseEntity<Store> getStoreByAdmin(){
            
        return ResponseEntity.ok(storeService.getStoreByAdmin());
                                                                    

    }


    @PutMapping("/{id}")
    public ResponseEntity<StoreDTO> updateStore(
        @RequestBody StoreDTO storeDTO,
        @PathVariable Long id ){
            
        return ResponseEntity.ok(storeService.updateStore(id,storeDTO));
                                                                    

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteStore(@PathVariable Long id){

        ApiResponse apiResponse=new ApiResponse();
         storeService.deleteStore(id);

         apiResponse.setMessage("store deleted successfuly");

         return ResponseEntity.ok(apiResponse);
                                                                    

    }


    @GetMapping("/employee")
    public ResponseEntity<StoreDTO> getStoreByEmployee(){
            
        return ResponseEntity.ok(storeService.getStoreByEmployee());
                                                                    

    }



    @PutMapping("/{id}/moderate")
    public ResponseEntity<StoreDTO> moderateStore(
        @RequestBody StoreStatus status,
        @PathVariable Long id ){
            
        return ResponseEntity.ok(storeService.moderateStore(id,status));
                                                                    

    }



    






}
