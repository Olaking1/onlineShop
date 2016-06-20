package cn.amy.shop.order.service;

import org.springframework.transaction.annotation.Transactional;

import cn.amy.shop.order.dao.OrderDao;
import cn.amy.shop.order.vo.Order;

@Transactional
public class OrderService{
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	//保存订单的业务层
	public void saveOrder(Order order) {
		// TODO Auto-generated method stub
		orderDao.saveOrder(order);
	}
	
}
