package vn.hcmute.projectmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmute.projectmanagement.entity.Cart;
import vn.hcmute.projectmanagement.entity.Cart_Product;
import vn.hcmute.projectmanagement.entity.Cart_Product_Id;
import vn.hcmute.projectmanagement.entity.Person;
import vn.hcmute.projectmanagement.exception.NotFoundException;
import vn.hcmute.projectmanagement.repository.CartProductRepository;
import vn.hcmute.projectmanagement.repository.PersonRepository;
import vn.hcmute.projectmanagement.service.Cart_ProductService;

import java.util.List;
import java.util.Optional;

@Service
public class Cart_ProductServiceImpl implements Cart_ProductService {
    @Autowired
    private CartProductRepository cartProductRepository;

    @Override
    public List<Cart_Product> retrieveAllCartProduct(Long cartId) {
        List<Cart_Product> cart_products = cartProductRepository.findById_CartId(cartId);
        if(cart_products.isEmpty())
            throw new NotFoundException("Not Found Product in Your Cart");
        return cart_products;
    }

    @Override
    public Cart_Product addProductInCart(Cart_Product cartProduct) {
        return cartProductRepository.save(cartProduct);
    }

    @Override
    public void deleteProductInCart(Cart_Product cartProduct) {
        cartProductRepository.delete(cartProduct);
    }

    @Override
    public boolean deleteAllProductInCart(Long cartId) {
        List<Cart_Product> cart_products = cartProductRepository.findById_CartId(cartId);

        if(cart_products.isEmpty())
            throw new NotFoundException("Not Found Product in Your Cart");

        for (Cart_Product cart_product:cart_products) {
            deleteProductInCart(cart_product);
        }
        return true;
    }
}
