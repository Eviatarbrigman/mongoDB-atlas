package com.eviatar.mongoDB_atlas.service;

import com.eviatar.mongoDB_atlas.model.Order;
import com.eviatar.mongoDB_atlas.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public Order createOneOrder(Order order) {
        order.setOrderId(UUID.randomUUID().toString().split("-")[0]);
        return orderRepository.save(order);
    }

    public List<Order> createOrders(List<Order> orders) {
        for (Order order : orders) {
            order.setOrderId(UUID.randomUUID().toString().split("-")[0]);
        }
        return orderRepository.saveAll(orders);
    }

    public Order getOrderById(String id) {
        return orderRepository.findById(id).orElseThrow();
    }

    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    public List<Order> findByProductId(int ProductId) {
        return orderRepository.findByProductId(ProductId);
    }

    public List<Order> getOrdersByProductName(String productName) {
        return orderRepository.getOrdersByProductName(productName);
    }


    public Order updateOrder(Order order) {
        Order orderFromDB = orderRepository.findById(order.getOrderId()).orElseThrow();
        orderFromDB.setProductName(order.getProductName());
        orderFromDB.setProductId(order.getProductId());
        orderFromDB.setCustomerId(order.getCustomerId());
        orderFromDB.setOrderId(order.getOrderId());
        return orderRepository.save(orderFromDB);
    }
      //****for orders with nested customer
//    public Order updateOrder(Order order) {
//        Order orderFromDB = orderRepository.findById(order.getOrderId()).orElseThrow();
//        orderFromDB.setProductName(order.getProductName());
//        orderFromDB.setProductId(order.getProductId());
//        orderFromDB.setCustomer(order.getCustomer());
//        orderFromDB.setOrderId(order.getOrderId());
//        return orderRepository.save(orderFromDB);
//    }

    public String deleteOrder(String orderId) {
        orderRepository.deleteById(orderId);
        return "order  " + orderId + " deleted";
    }

}

