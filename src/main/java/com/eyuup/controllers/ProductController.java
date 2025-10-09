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
import org.springframework.web.bind.annotation.RestController;

import com.eyuup.payload.dto.ProductDTO;
import com.eyuup.service.ProductService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {
        private final ProductService  productService;

        @PostMapping
        public ResponseEntity<ProductDTO> createProduct(@RequestBody ProductDTO productDTO , 
                                                                        @RequestHeader String jwt)
                {
                    return ResponseEntity.ok(
                        productService.createProduct(productDTO, null)
                    );
                }

        @PutMapping("/{id}")
        public ResponseEntity<ProductDTO> updateProduct(@PathVariable Long id ,@RequestBody ProductDTO productDTO , 
                                                                @RequestHeader String jwt)
        {
            return ResponseEntity.ok(
                productService.udpateProduct(id,productDTO, null)
            );
        } 


        @DeleteMapping("/{id}")
        public void deleteProduct(@PathVariable Long id, 
                                                                @RequestHeader String jwt)
        {
           productService.deleteProduct(id, null);
        } 




        @GetMapping("/store/{storeId}")
        public ResponseEntity<List<ProductDTO>> getProductByStoreId(@PathVariable Long storeId, 
                                                                @RequestHeader String jwt)
        {
            List<ProductDTO> productDTOs=productService.getProductByStoreId(storeId);

            return ResponseEntity.ok( productDTOs);
        } 
        @GetMapping("/{storeId}")
        public ResponseEntity<List<ProductDTO>> getProductByStoreId(@PathVariable Long storeId,@Param(value = "query") String keyword, 
                                                                @RequestHeader String jwt)
        {
            List<ProductDTO> productDTOs=productService.searchByKeyword(storeId,keyword);

            return ResponseEntity.ok(productDTOs);
        } 
}
