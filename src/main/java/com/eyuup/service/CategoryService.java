package com.eyuup.service;

import java.util.List;

import com.eyuup.modal.User;
import com.eyuup.payload.dto.CategoryDTO;

public interface CategoryService {

 CategoryDTO createCategory(CategoryDTO categoryDTO, User user);

 List<CategoryDTO> getCategoryByStoreId(Long storeId);

 CategoryDTO update(Long categoryId ,CategoryDTO categoryDTO , User user) throws Exception;

 void delete(Long categoryId) throws Exception;



}
