package vn.hcmute.projectmanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.hcmute.projectmanagement.entity.Cart;
import vn.hcmute.projectmanagement.exception.NotFoundException;
import vn.hcmute.projectmanagement.repository.CartRepository;
import vn.hcmute.projectmanagement.service.CartService;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartRepository cartRepository;

    @Override
    public Cart findCartById(Long id) {
        Optional<Cart> cart = cartRepository.findCartById(id);

        if(!cart.isPresent())
            throw new NotFoundException("Cart Not Fount!");

        return cart.get();
    }
}
