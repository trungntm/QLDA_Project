package vn.hcmute.projectmanagement.service;

import vn.hcmute.projectmanagement.entity.Cart;

import java.util.Optional;

public interface CartService {
    Cart findCartById(Long id);
}
