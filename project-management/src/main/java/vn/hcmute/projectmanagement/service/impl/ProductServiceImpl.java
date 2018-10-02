package vn.hcmute.projectmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmute.projectmanagement.api.v1.mapper.ProductMapper;
import vn.hcmute.projectmanagement.entity.Product;
import vn.hcmute.projectmanagement.exception.Error404;
import vn.hcmute.projectmanagement.repository.ProductRepository;
import vn.hcmute.projectmanagement.service.ProductService;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    ProductRepository productRepository;

    @Override
    public Product getProductById(long id) {
        Optional<Product> product = productRepository.findProductsById(id);
        if(!product.isPresent()){
            throw new Error404("Product Not Found");
        }
        return product.get();
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
        //return productRepository.findAll().stream().map(ProductMapper::productToProductDto).collect(Collectors.toList());
    }

    @Override
    public Product InsertProduct(Product product) {
        return productRepository.save(product);
    }

    @Override
    public boolean DeleteProduct(long id) {
        Product product = productRepository.findProductsById(id).get();
        if(product != null){
            try {
                product.setStatus("false");
                productRepository.save(product);
                return true;
            }catch (Exception e){
                return false;
            }
        }else{
            return false;
        }
    }
}
