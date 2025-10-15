package com.eyuup.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eyuup.domain.CheckAuthority;
import com.eyuup.mapper.CategoryMapper;
import com.eyuup.modal.Category;
import com.eyuup.modal.Store;
import com.eyuup.modal.User;
import com.eyuup.payload.dto.CategoryDTO;
import com.eyuup.repository.CategoryRepository;
import com.eyuup.repository.StoreRepository;
import com.eyuup.service.CategoryService;
import com.eyuup.service.UserService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final StoreRepository storeRepository;
    private final UserService userService;



    @Override
    public CategoryDTO createCategory(CategoryDTO categoryDTO, User user) throws Exception {

        Store store=storeRepository.findById(categoryDTO.getStoreId()).orElseThrow(
            () -> new Exception("Store not Found")
        );

        Category category=Category.builder()
                            .name(categoryDTO.getName())
                            .store(store)
                          .build();


       CheckAuthority.isAuthorized(user, store);
    
        Category savedCategory =  categoryRepository.save(category)                ;
                          
        

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

        // check authorities
        CheckAuthority.isAuthorized(user, category.getStore());

       Category savedCategory= categoryRepository.save(category);

       return CategoryMapper.ToDTO(savedCategory);
    }



    @Override
    public void delete(Long categoryId,User user) throws Exception {

      Category category=categoryRepository.findById(categoryId).orElseThrow(
            ()-> new Exception("Category Not found")
        );

        //! check if user can delete the store
        CheckAuthority.isAuthorized(user, category.getStore());

        categoryRepository.delete(category);
    }
    
    

}
