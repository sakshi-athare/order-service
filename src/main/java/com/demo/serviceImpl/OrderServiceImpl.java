package com.demo.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import com.demo.dto.ProductResponse;
import com.demo.dto.UserResponse;
import com.demo.entity.Order;
import com.demo.exception.ProductNotFoundException;
import com.demo.exception.UserNotFoundException;
import com.demo.exception.UserServiceDownException;
import com.demo.repository.OrderRepository;
import com.demo.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public Order placeOrder(Order order) {

		UserResponse user = null;

		// 1. Call User Service

		try {
			user = restTemplate.getForObject("http://localhost:8081/user?id=" + order.getUserId(), UserResponse.class);

		} catch (ResourceAccessException e) {

			throw new UserServiceDownException("user service down!!");
		}

		if (user == null) {
			throw new UserNotFoundException("User not found with id " + order.getUserId());
		}

		// 2. Call Product Service
		ProductResponse product = null;
		try {
			product = restTemplate.getForObject("http://localhost:8082/product?id=" + order.getProductId(),
					ProductResponse.class);
		} catch (ResourceAccessException e) {

			throw new UserServiceDownException("user service down!!");
		}

		if (product == null) {
			throw new ProductNotFoundException("Product not found with id " + order.getProductId());
		}

		if (order.getOrderQty() > product.getQuantity()) {
			throw new RuntimeException("Insufficient product quantity");
		}

		// 3. Calculate total amount
		double discountedPrice = product.getPrice() - (product.getPrice() * product.getDiscount() / 100);

		double totalAmount = discountedPrice * order.getOrderQty();
		order.setTotalAmount(totalAmount);

		
		return orderRepository.save(order);
	}

}
