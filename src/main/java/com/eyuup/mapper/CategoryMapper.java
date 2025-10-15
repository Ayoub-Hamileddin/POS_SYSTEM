package com.eyuup.mapper;



import com.eyuup.modal.Category;
import com.eyuup.payload.dto.CategoryDTO;

public class CategoryMapper {

    public static CategoryDTO ToDTO (Category category){
        return CategoryDTO.builder()
            .id(category.getId())
            .name(category.getName())
            .storeId(category.getStore()!=null ? category.getStore().getId():null)
            
        .build();
    }

}
