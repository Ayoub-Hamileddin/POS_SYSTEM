package com.eyuup.mapper;

import java.time.LocalDateTime;

import com.eyuup.modal.Product;
import com.eyuup.modal.Store;
import com.eyuup.payload.dto.ProductDTO;

public class ProductMapper {
    public static ProductDTO ToDTO(Product product){
        return ProductDTO.builder()

                .id(product.getId())
                .name(product.getName())
                .sku(product.getSku())
                .description(product.getDescription())
                .mrp(product.getMrp())
                .sellingPrice(product.getSellingPrice())
                .brand(product.getBrand())
                .image(product.getImage())
                .categoryId(product.getCategory().getId())
                .storeId(product.getStore()!=null?product.getStore().getId():null)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())

            .build();
    }
    public static Product ToEntity(ProductDTO productDTO,Store store){
        return Product.builder()
                .id(productDTO.getId())
                .name(productDTO.getName())
                .sku(productDTO.getSku())
                .description(productDTO.getDescription())
                .mrp(productDTO.getMrp())
                .sellingPrice(productDTO.getSellingPrice())
                .brand(productDTO.getBrand())
                .image(productDTO.getImage())
                .store(store)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                // .category(productDTO.getCategoryId())
                
        .build();
    }
}
