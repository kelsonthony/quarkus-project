package com.kelsonthony.domain.service;

import com.kelsonthony.api.dto.ProductDTO;
import com.kelsonthony.domain.entity.ProductEntity;
import com.kelsonthony.domain.repository.ProductRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ProductService {
    @Inject
    private ProductRepository productRepository;

    public List<ProductDTO> getAllProducts() {
        List<ProductDTO> products = new ArrayList<>();
        productRepository.findAll().stream().forEach(product -> {
            products.add(mapProductEntityToDTO(product));
        });
        return products;
    }

    public void createNewProduct(ProductDTO product) {
        productRepository.persist(mapProductDTOToEntity(product));
    }

    public void changeProduct(Long id, ProductDTO productDTO) {
        ProductEntity productEntity = productRepository.findById(id);

        productEntity.setName(productDTO.getName());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setCategory(productDTO.getCategory());
        productEntity.setPrice(productDTO.getPrice());
        productEntity.setModel(productDTO.getModel());

        productRepository.persist(productEntity);
    }

    public ProductDTO getProductById(Long id) {
        return mapProductEntityToDTO(productRepository.findById(id));
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    private ProductDTO mapProductEntityToDTO(ProductEntity productEntity) {
        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(productEntity.getId());
        productDTO.setName(productEntity.getName());
        productDTO.setDescription(productEntity.getDescription());
        productDTO.setCategory(productEntity.getCategory());
        productDTO.setModel(productEntity.getModel());
        productDTO.setPrice(productEntity.getPrice());

        return productDTO;
    }

    private ProductEntity mapProductDTOToEntity(ProductDTO productDTO) {
        ProductEntity productEntity = new ProductEntity();
        productEntity.setId(productDTO.getId());
        productEntity.setName(productDTO.getName());
        productEntity.setDescription(productDTO.getDescription());
        productEntity.setCategory(productDTO.getCategory());
        productEntity.setModel(productDTO.getModel());
        productEntity.setPrice(productDTO.getPrice());

        return productEntity;
    }


}
