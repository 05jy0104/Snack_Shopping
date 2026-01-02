package com.snack.service.impl;

import com.snack.entity.Cart;
import com.snack.mapper.CartMapper;
import com.snack.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public List<Cart> findByUserId(Integer userId) {
        return cartMapper.findByUserId(userId);
    }

    @Override
    public void add(Cart cart) {
        Cart existCart = cartMapper.findByUserIdAndSnackId(cart.getUserId(), cart.getSnackId());
        if (existCart != null) {
            existCart.setQuantity(existCart.getQuantity() + cart.getQuantity());
            cartMapper.update(existCart);
        } else {
            cart.setCreateTime(new Date());
            cartMapper.insert(cart);
        }
    }

    @Override
    public void update(Cart cart) {
        cartMapper.update(cart);
    }

    @Override
    public void delete(Integer id) {
        cartMapper.delete(id);
    }

    @Override
    public void clear(Integer userId) {
        cartMapper.deleteByUserId(userId);
    }
}
