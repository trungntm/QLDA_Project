package vn.hcmute.projectmanagement.service;

import org.springframework.stereotype.Service;
import vn.hcmute.projectmanagement.entity.Product;

import java.util.List;

public interface ProductService {
    Product getProductById(long id);
    List<Product> getAllProduct();
    Product InsertProduct(Product product);
    boolean DeleteProduct(long id);
}
