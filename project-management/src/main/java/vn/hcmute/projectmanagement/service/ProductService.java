package vn.hcmute.projectmanagement.service;

import org.springframework.stereotype.Service;
import vn.hcmute.projectmanagement.api.v1.dto.ProductDto;
import vn.hcmute.projectmanagement.entity.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(long id);
    List<ProductDto> getAllProduct();
    List<ProductDto> getAllProductByCategory(long id);
    Product InsertProduct(Product product);
    void DeleteProduct(long id);
}
