package com.eyuup.mapper;



import com.eyuup.modal.Category;
import com.eyuup.modal.User;
import com.eyuup.payload.dto.CategoryDTO;

public class CategoryMapper {
    public static CategoryDTO ToDTO (Category category){
        return CategoryDTO.builder()
        
            .id(category.getId())
            .name(category.getName())
            .store(StoreMapper.toDTO(category.getStore()))
        
        .build();
    }

    public static Category ToEntity(CategoryDTO categoryDTO,User user){
    return Category.builder()
        
            .id(categoryDTO.getId())
            .name(categoryDTO.getName())
            .store(StoreMapper.toEntity(categoryDTO.getStore(),user.getStore().getStoreAdmin()))
        
        .build();
    }
}
