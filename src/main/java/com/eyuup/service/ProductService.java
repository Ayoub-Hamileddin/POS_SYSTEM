package com.eyuup.service;

import java.util.List;

import com.eyuup.modal.User;
import com.eyuup.payload.dto.ProductDTO;

public interface ProductService {

    ProductDTO createProduct(ProductDTO productDTO , User user);

    ProductDTO udpateProduct(Long ProductId,ProductDTO productDTO , User user);

    void deleteProduct(Long ProductId , User user);

    List<ProductDTO> getProductByStoreId(Long storeId, User user);
    
    List<ProductDTO> searchByKeyword(Long storeId,String keyword);
}
