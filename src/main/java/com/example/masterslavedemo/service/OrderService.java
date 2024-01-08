package com.example.masterslavedemo.service;

import com.example.masterslavedemo.entity.Order;
import com.example.masterslavedemo.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class OrderService implements OrderServiceInterface {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }


    @Transactional("slaveTransactionManager")
    public Order getOrderById(Long orderId) {
        Optional<Order> optionalOrder = orderRepository.findById(orderId);
        return optionalOrder.orElse(null);
    }

    @Transactional("masterTransactionManager")
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}
