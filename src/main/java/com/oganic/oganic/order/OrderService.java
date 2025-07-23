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

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final CartReponsitory cartReponsitory;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository,
            CartReponsitory cartReponsitory) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
        this.cartReponsitory = cartReponsitory;
    }

    @Transactional
    public Order checkout(CartRequest cartRequest) {
        Order order = new Order();
        order.setUserId(cartRequest.getUserId());
        order.setOrderDate(LocalDateTime.now());
        List<Cart> carts = cartReponsitory.findAll();

        List<OrderDetail> details = carts.stream().map(item -> {
            OrderDetail detail = new OrderDetail();
            detail.setProduct(item.getProduct());
            detail.setQuatity(item.getQuatity());
            detail.setOrder(order);
            return detail;
        }).collect(Collectors.toList());
        order.setOrderDetails(details);
        cartReponsitory.deleteAll(carts);

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
