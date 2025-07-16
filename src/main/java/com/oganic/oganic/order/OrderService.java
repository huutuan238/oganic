package com.oganic.oganic.order;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.oganic.oganic.product.Product;
import com.oganic.oganic.product.ProductRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    public OrderService(OrderRepository orderRepository, ProductRepository productRepository) {
        this.orderRepository = orderRepository;
        this.productRepository = productRepository;
    }
    
    @Transactional
    public Order addCart(CartRequest cartRequest) {
        Order order = new Order();
        order.setUserId(cartRequest.getUserId());
        order.setOrderDate(LocalDateTime.now());

        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setQuatity(cartRequest.getQuatity());
            orderDetail.setProduct(productRepository.findById(cartRequest.getProductId())
        .orElseThrow(() -> new ResourceNotFoundException("Product not found")));
        order.setOrderDetails(List.of(orderDetail));

        return orderRepository.save(order);
    }

    public List<OrderDetail> getCartItems() {
        List<Order> orders = orderRepository.findAll();
        List<OrderDetail> orderDetails = new ArrayList<>();
        for(Order order: orders) {
            orderDetails.addAll(order.getOrderDetails());
        }
        
        return orderDetails;
    }

    public String removeCartItem(Long id) {
        orderRepository.deleteById(id);
        return "ok";
    }
}
