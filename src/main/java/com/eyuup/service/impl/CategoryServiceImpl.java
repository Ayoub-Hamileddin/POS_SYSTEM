package com.eyuup.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eyuup.mapper.CategoryMapper;
import com.eyuup.modal.Category;
import com.eyuup.modal.User;
import com.eyuup.payload.dto.CategoryDTO;
import com.eyuup.repository.CategoryRepository;
import com.eyuup.service.CategoryService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;



    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO, User user) {

        Category category=CategoryMapper.ToEntity(categoryDTO, user);

        Category savedCategory=categoryRepository.save(category);

        return CategoryMapper.ToDTO(savedCategory);
    }

    @Override
    public List<CategoryDTO> getCategoryByStoreId(Long storeId) {
        List<Category> categorysOfStore=categoryRepository.findByStoreId(storeId);
        return  categorysOfStore.stream()
                    .map(CategoryMapper::ToDTO)
                    .toList();
    }

    @Override
    public CategoryDTO update(Long categoryId,CategoryDTO categoryDTO, User user) throws Exception {

        Category category=categoryRepository.findById(categoryId).orElseThrow(
            ()-> new Exception("Category Not found")
        );


        category.setName(categoryDTO.getName());
        category.setStore(user.getStore());


       Category savedCategory= categoryRepository.save(category);
       
       return CategoryMapper.ToDTO(savedCategory);
    }

    @Override
    public void delete(Long categoryId) throws Exception {

      Category category=categoryRepository.findById(categoryId).orElseThrow(
            ()-> new Exception("Category Not found")
        );

        categoryRepository.delete(category);
    }



}
