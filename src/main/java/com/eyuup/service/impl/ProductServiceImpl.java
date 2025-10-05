package com.eyuup.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.eyuup.mapper.ProductMapper;
import com.eyuup.modal.Product;
import com.eyuup.modal.Store;
import com.eyuup.modal.User;
import com.eyuup.payload.dto.ProductDTO;
import com.eyuup.repository.ProductRepository;
import com.eyuup.service.ProductService;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor 
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;


    @Override
    public ProductDTO createProduct(ProductDTO productDTO, User user) {
        Store store =user.getStore();
        
        Product product=ProductMapper.ToEntity(productDTO, store);

        productRepository.save(product);
        
        return ProductMapper.ToDTO(product);
    }

    @Override
    public ProductDTO udpateProduct(Long ProductId, ProductDTO productDTO, User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'udpateProduct'");
    }

    @Override
    public void deleteProduct(Long ProductId, User user) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'deleteProduct'");
    }

    @Override
    public List<ProductDTO> getProductByStoreId(Long storeId) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getProductByStoreId'");
    }

    @Override
    public List<ProductDTO> searchByKeyword(Long storeId, String keyword) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'searchByKeyword'");
    }

}
