package com.mvc.service.impl;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.mvc.entity.Order;
import com.mvc.repository.IOrderRepository;
import com.mvc.service.IOrderService;
@Service
public class OrderService implements IOrderService{
	@Resource
    private IOrderRepository orderRepository;
	@Override
	public void addOrder(Order order) {
		orderRepository.addOrder(order);
	}
	@Override
	public List<Order> myOrder(String userId) {
		return orderRepository.myOrder(userId);
	}
	@Override
	public Order getOrder(long orderId) {
		return orderRepository.getOrder(orderId);
	}
	@Override
	public List<Order> photographerOrder(String photographerId) {
		return orderRepository.photographerOrder(photographerId);
	}
	@Override
	public void updateStatus(Order order) {
		orderRepository.updateStatus(order);
	}

}
