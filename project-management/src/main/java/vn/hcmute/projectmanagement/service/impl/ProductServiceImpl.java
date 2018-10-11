package vn.hcmute.projectmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmute.projectmanagement.api.v1.dto.ProductDto;
import vn.hcmute.projectmanagement.api.v1.mapper.ProductMapper;
import vn.hcmute.projectmanagement.entity.Product;
import vn.hcmute.projectmanagement.exception.DeleteException;
import vn.hcmute.projectmanagement.exception.Error404;
import vn.hcmute.projectmanagement.repository.CategoryRepository;
import vn.hcmute.projectmanagement.repository.ProductRepository;
import vn.hcmute.projectmanagement.service.ProductService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    ProductMapper productMapper;


    @Override
    public Product getProductById(long id) {
        Optional<Product> product = productRepository.findById(id);
        if(!product.isPresent()){
            throw new Error404("Product Not Found");
        }
        return product.get();
    }

    @Override
    public List<ProductDto> getAllProduct() {
        List<Product> lstProduct = productRepository.findAll();
        return lstProduct.stream().map(productMapper::productToProductDto).collect(Collectors.toList());
    }

    @Override
    public Product InsertProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public void DeleteProduct(long id) {
        Optional<Product> optionalProduct = productRepository.findById(id);
        if(optionalProduct.isPresent()){
            Product product = optionalProduct.get();
            product.setStatus("false");
            productRepository.save(product);
        }
    }

    @Override
    public List<ProductDto> getAllProductByCategory(long id){
        Set<Product> lstProduct = categoryRepository.findCategoriesById(id).get().getProducts();
        return lstProduct.stream().map(productMapper::productToProductDto).collect(Collectors.toList());
    }
}
