package com.eyuup.service.impl;

import java.util.List;
import java.util.Objects;

import org.springframework.stereotype.Service;

import com.eyuup.exceptions.ProductException;
import com.eyuup.exceptions.StoreException;
import com.eyuup.mapper.ProductMapper;
import com.eyuup.modal.Product;
import com.eyuup.modal.Store;
import com.eyuup.modal.User;
import com.eyuup.payload.dto.ProductDTO;
import com.eyuup.repository.ProductRepository;
import com.eyuup.repository.StoreRepository;
import com.eyuup.service.ProductService;

import lombok.RequiredArgsConstructor;



@Service
@RequiredArgsConstructor 
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;


    @Override
    public ProductDTO createProduct(ProductDTO productDTO, User user) {

      

        Store store=storeRepository.findById(
          productDTO.getStoreId()
        ).orElseThrow(
         () -> new StoreException("store not found")
        );
        if (!Objects.equals(user.getId(), store.getStoreAdmin().getId())) {
            throw new ProductException("You can only create a product if you are the store manager");
        }
        
        Product product=ProductMapper.ToEntity(productDTO, store);
        Product savedProduct=productRepository.save(product);


        return ProductMapper.ToDTO(savedProduct);
        


    }



    @Override
    public ProductDTO udpateProduct(Long ProductId, ProductDTO productDTO, User user) {


      //check if store is existing 
      //to update the product must user be the owner of the store 
      Store store=storeRepository.findById(
          productDTO.getStoreId()
        ).orElseThrow(
         () -> new StoreException("store not found")
        );
        if (!Objects.equals(user.getId(), store.getStoreAdmin().getId())) {
            throw new ProductException("You can only create a product if you are the store manager");
        }
      //end 

        Product product=productRepository.findById(ProductId).orElseThrow(
            ()->  new ProductException("product not found")
        );
                product.setName(productDTO.getName());
                product.setSku(productDTO.getSku());
                product.setDescription(productDTO.getDescription());
                product.setMrp(productDTO.getMrp());
                product.setSellingPrice(productDTO.getSellingPrice());
                product.setBrand(productDTO.getBrand());
                product.setImage(productDTO.getImage());
                // product.setStore(user.getStore());


        Product savedProduct=productRepository.save(product);

        return ProductMapper.ToDTO(savedProduct);
    }



    @Override
    public void deleteProduct(Long ProductId, User user) {
      Product product=productRepository.findById(ProductId).orElseThrow(
        ()->new ProductException("product not  found")
      );

      productRepository.delete(product);


    }



    @Override
    public List<ProductDTO> getProductByStoreId(Long storeId,User user) {
      //check if store is existing 
      //to update the product must user be the owner of the store 
      Store store=storeRepository.findById(
          storeId
        ).orElseThrow(
         () -> new StoreException("store not found")
        );
        if (!Objects.equals(user.getId(), store.getStoreAdmin().getId())) {
            throw new ProductException("You can only create a product if you are the store manager");
        }
      //end   





              List<Product> products=productRepository.findByStoreId(storeId);
              return products.stream()
                              .map(ProductMapper::ToDTO)
                              .toList();
    }


    
    @Override
    public List<ProductDTO> searchByKeyword(Long storeId, String keyword) {
            List<Product> products=productRepository.searchByKeyword(storeId, keyword);

            return products.stream()
                        .map(ProductMapper::ToDTO)
                        .toList();
    }

}
