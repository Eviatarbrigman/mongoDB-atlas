package com.eviatar.mongoDB_atlas.controller;

import com.eviatar.mongoDB_atlas.model.Customer;
import com.eviatar.mongoDB_atlas.model.Order;
import com.eviatar.mongoDB_atlas.service.CustomerService;
import com.eviatar.mongoDB_atlas.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private CustomerService customerService;


    @PostMapping("/order")
    public ResponseEntity<Order> createOneOrder(@RequestBody Order order) {
        Order orderToSave = new Order();
        orderToSave.setProductName(order.getProductName());
        orderToSave.setCustomerId(order.getCustomerId());
        orderToSave.setProductId(order.getProductId());
        return new ResponseEntity<>(orderService.createOneOrder(orderToSave), HttpStatus.OK);
    }
    //****for orders with nested customer
//    @PostMapping("/order")
//    public ResponseEntity<Order> createOneOrder(@RequestBody Order order) {
//        Order orderToSave = new Order();
//        orderToSave.setProductName(order.getProductName());
//        orderToSave.setCustomer(order.getCustomer());
//        orderToSave.setProductId(order.getProductId());
//
//        if (customerService.findById(order.getCustomer().getId()).isEmpty()) {
//            Customer customer = new Customer(order.getCustomer().getId(),
//                    order.getCustomer().getFullName(),
//                    order.getCustomer().getEmail(),
//                    order.getCustomer().getPhoneNumber());
//            customerService.createCustomer(customer);
//        }
//        return new ResponseEntity<>(orderService.createOneOrder(orderToSave), HttpStatus.OK);
//    }

    @PostMapping("/orders")
    public ResponseEntity<List<Order>> createOrders (@RequestBody List<Order> orders) {
        for (Order order : orders) {

            Order orderToSave = new Order();
            orderToSave.setProductName(order.getProductName());
            orderToSave.setCustomerId(order.getCustomerId());
            orderToSave.setProductId(order.getProductId());
        }
        return new ResponseEntity<>(orderService.createOrders(orders), HttpStatus.OK);
    }
    //****for orders with nested customer
//    @PostMapping("/orders")
//    public ResponseEntity<List<Order>> createOrders(@RequestBody List<Order> orders) {
//        for (Order order : orders) {
//
//            Order orderToSave = new Order();
//            orderToSave.setProductName(order.getProductName());
//            orderToSave.setCustomer(order.getCustomer());
//            orderToSave.setProductId(order.getProductId());
//
//            if (customerService.findById(order.getCustomer().getId()).isEmpty()) {
//                Customer customer = new Customer(order.getCustomer().getId(),
//                        order.getCustomer().getFullName(),
//                        order.getCustomer().getEmail(),
//                        order.getCustomer().getPhoneNumber());
//                customerService.createCustomer(customer);
//            }
//        }
//        return new ResponseEntity<>(orderService.createOrders(orders), HttpStatus.OK);
//    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable String orderId) {
        return new ResponseEntity<>(orderService.getOrderById(orderId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Order>> getTasks() {
        return new ResponseEntity<>(orderService.getAllOrders(), HttpStatus.OK);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Order>> getOrderByProductId(@PathVariable Integer productId) {
        return new ResponseEntity<>(orderService.findByProductId(productId), HttpStatus.OK);
    }

    @GetMapping("/product/{productName}")
    public ResponseEntity<List<Order>> getOrdersByProductName(@PathVariable String productName) {
        return new ResponseEntity<>(orderService.getOrdersByProductName(productName), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Order> updateOrder(@RequestBody Order order) {
        return new ResponseEntity<>(orderService.updateOrder(order), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOrder(@RequestBody String orderId) {
        return new ResponseEntity<>(orderService.deleteOrder(orderId), HttpStatus.OK);
    }

}
