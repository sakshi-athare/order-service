package com.demo.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.demo.entity.Order;
import com.demo.service.OrderService;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // Place Order
    @PostMapping
    public ResponseEntity<Order> placeOrder(@RequestBody Order order) {
        Order savedOrder = orderService.placeOrder(order);
        return new ResponseEntity<>(savedOrder, HttpStatus.CREATED);
    }

//    // Get Order by ID
//    @GetMapping("/{id}")
//    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
//        Optional<Order> order = orderService.getOrderById(id);
//
//        return order
//                .map(ResponseEntity::ok)
//                .orElseGet(() -> ResponseEntity.notFound().build());
//    }
}
