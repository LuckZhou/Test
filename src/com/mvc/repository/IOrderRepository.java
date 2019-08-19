package com.mvc.repository;

import java.util.List;

import com.mvc.entity.Order;

public interface IOrderRepository {
	public void addOrder(Order order);
	public List<Order> myOrder(String userId);
	public Order getOrder(long orderId);
	public List<Order> photographerOrder(String photographerId);
	public void updateStatus(Order order);
}
