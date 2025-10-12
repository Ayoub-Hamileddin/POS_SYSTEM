package com.eyuup.controllers;

import java.util.List;

import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eyuup.modal.User;
import com.eyuup.payload.dto.ProductDTO;
import com.eyuup.payload.response.ApiResponse;
import com.eyuup.service.ProductService;
import com.eyuup.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

        private final ProductService  productService;
        private final UserService userService;



        @PostMapping
        public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO , 
                                                                        @RequestHeader("Authorization") String jwt)
                {
                    User user=userService.getUserFromJwtToken(jwt);
                    return ResponseEntity.ok(
                        productService.createProduct(productDTO, user)
                    );
                }



        @PutMapping("/{id}")
        public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id ,@RequestBody ProductDTO productDTO , 
                                                                @RequestHeader("Authorization") String jwt)
        {
            User user=userService.getUserFromJwtToken(jwt);
            return ResponseEntity.ok(
                productService.udpateProduct(id,productDTO, user)
            );
        } 




        @DeleteMapping("/{id}")
        public ResponseEntity<ApiResponse> deleteProduct(@PathVariable Long id, 
                                                                @RequestHeader("Authorization") String jwt)
        {
           productService.deleteProduct(id, null);

           ApiResponse apiResponse=new ApiResponse();

           apiResponse.setMessage("product with id: "+ id +" deleted successfuly");

           return ResponseEntity.ok(apiResponse);
        } 




        @GetMapping("/store/{storeId}")
        public ResponseEntity<List<ProductDTO>> getProductByStoreId(@PathVariable Long storeId, 
                                                                @RequestHeader("Authorization")  String jwt)
        {
            User user=userService.getUserFromJwtToken(jwt);
            List<ProductDTO> productDTOs=productService.getProductByStoreId(storeId,user);

            return ResponseEntity.ok( productDTOs);
        } 



        @GetMapping("/search")
        public ResponseEntity<List<ProductDTO>> getProductByKeyword(@RequestParam(value = "storeId") Long storeId,@RequestParam(value = "query") String keyword, 
                                                                @RequestHeader("Authorization") String jwt)
        {
            List<ProductDTO> productDTOs=productService.searchByKeyword(storeId,keyword);

            return ResponseEntity.ok(productDTOs);
        } 
}
