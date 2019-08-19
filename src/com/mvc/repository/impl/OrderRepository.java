package com.mvc.repository.impl;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.core.repository.impl.BaseRepository;
import com.mvc.entity.Order;
import com.mvc.repository.IOrderRepository;

@Repository
public class OrderRepository extends BaseRepository<Order> implements IOrderRepository{
	@Override
	public void addOrder(Order order) {
		add(order);
	}
	@Override
	public List<Order> myOrder(String userId) {
		StringBuffer hql=new StringBuffer();
		hql.append("from Order where userId='");
		hql.append(userId);
		hql.append("'");
		return find(hql.toString());
	}
	@Override
	public Order getOrder(long orderId) {
		return get(Order.class, orderId);
	}
	@Override
	public List<Order> photographerOrder(String photographerId) {
		StringBuffer hql=new StringBuffer();
		hql.append("from Order where photographerId='");
		hql.append(photographerId);
		hql.append("'");
		return find(hql.toString());
	}
	@Override
	public void updateStatus(Order order) {
		update(order);
	}
}
