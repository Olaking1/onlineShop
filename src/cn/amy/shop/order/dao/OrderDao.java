package cn.amy.shop.order.dao;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.amy.shop.order.vo.Order;

public class OrderDao  extends HibernateDaoSupport{

	public void saveOrder(Order order) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(order);
	}

}
