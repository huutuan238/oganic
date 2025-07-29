package com.oganic.oganic.order;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.oganic.oganic.cart.Cart;
import com.oganic.oganic.cart.CartReponsitory;
import com.oganic.oganic.cart.CartRequest;
import com.oganic.oganic.product.ProductRepository;
import com.oganic.oganic.product.ProductService;
import com.oganic.oganic.user.User;
import com.oganic.oganic.user.UserRepository;
import com.oganic.oganic.user.UserService;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartReponsitory cartReponsitory;
    private final ProductService productService;
    private final UserRepository userRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository,
            CartReponsitory cartReponsitory, ProductService productService, UserRepository userRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.cartReponsitory = cartReponsitory;
        this.productService = productService;
        this.userRepository = userRepository;
    }

    @Transactional
    public Order checkout(CartRequest cartRequest) {
        Order order = new Order();
        order.setUserId(cartRequest.getUserId());
        order.setOrderDate(LocalDateTime.now());
        List<Cart> carts = cartReponsitory.findAll();

        List<OrderDetail> details = carts.stream().map(item -> {
            // update stock product
            productService.updateStock(item.getProduct().getId(), item.getQuatity());
            OrderDetail detail = new OrderDetail();
            detail.setProduct(item.getProduct());
            detail.setQuatity(item.getQuatity());
            detail.setOrder(order);
            return detail;
        }).collect(Collectors.toList());
        order.setOrderDetails(details);
        User user = userRepository.findById(cartRequest.getUserId()).orElseThrow();
        cartReponsitory.deleteByUser(user);
        return orderRepository.save(order);
    }

    public List<OrderDetail> getCartItems() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDetail> orderDetails = new ArrayList<>();
        for (Order order : orders) {
            orderDetails.addAll(order.getOrderDetails());
        }

        return orderDetails;
    }

    public String removeCartItem(Long id) {
        orderRepository.deleteById(id);
        return "ok";
    }
}
