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

import com.eyuup.modal.User;
import com.eyuup.payload.dto.CategoryDTO;
import com.eyuup.payload.response.ApiResponse;
import com.eyuup.service.CategoryService;
import com.eyuup.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

        private final CategoryService categoryService;
        private final UserService userService;



        @PostMapping
        public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO,
                                                                   @RequestHeader("Authorization") String jwt        ) throws Exception
        {
                User user=userService.getUserFromJwtToken(jwt);
                
                return ResponseEntity.ok(categoryService.createCategory(categoryDTO, user));
        }


        
        @GetMapping("/store/{storeId}")
        public ResponseEntity<List<CategoryDTO>> getCategoryByStoreId(@PathVariable("storeId") Long storeId,
                                                                   @RequestHeader("Authorization") String jwt        )
        {
                return ResponseEntity.ok(categoryService.getCategoryByStoreId(storeId));
        }


        @PutMapping("/{categoryId}")
        public ResponseEntity<CategoryDTO> updateCategory(@RequestBody CategoryDTO categoryDTO,@PathVariable("categoryId") Long categoryId,
                                                                   @RequestHeader("Authorization") String jwt        ) throws Exception
        {
            User user=userService.getUserFromJwtToken(jwt);
                return ResponseEntity.ok(categoryService.update(categoryId,categoryDTO,user));
        }

        
        @DeleteMapping("/{categoryId}")
        public ResponseEntity<ApiResponse> deleteMapping(@PathVariable("categoryId") Long categoryId,
                                                                   @RequestHeader("Authorization") String jwt        ) throws Exception
        {
            User user=userService.getUserFromJwtToken(jwt);

            categoryService.delete(categoryId,user);

            ApiResponse apiResponse=new ApiResponse();

            apiResponse.setMessage("category deleted succesfuly");

            return ResponseEntity.ok(apiResponse);
        }

        
}
