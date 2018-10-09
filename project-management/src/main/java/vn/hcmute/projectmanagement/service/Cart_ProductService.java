package vn.hcmute.projectmanagement.service;

import vn.hcmute.projectmanagement.entity.Cart;
import vn.hcmute.projectmanagement.entity.Cart_Product;
import vn.hcmute.projectmanagement.entity.Cart_Product_Id;

import java.util.List;

public interface Cart_ProductService {
    List<Cart_Product> retrieveAllCartProduct(Long id);
    Cart_Product addProductInCart(Cart_Product cartProduct);
    void deleteProductInCart(Cart_Product cart_product);
    boolean deleteAllProductInCart(Long cartId);
}
