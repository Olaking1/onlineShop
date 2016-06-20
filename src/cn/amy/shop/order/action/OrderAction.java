package cn.amy.shop.order.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import cn.amy.shop.cart.vo.Cart;
import cn.amy.shop.cart.vo.CartItem;
import cn.amy.shop.order.service.OrderService;
import cn.amy.shop.order.vo.Order;
import cn.amy.shop.order.vo.OrderItem;
import cn.amy.shop.user.vo.User;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class OrderAction extends ActionSupport implements ModelDriven<Order>{

	private Order order = new Order();
	private OrderService orderService; 
	
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}
	
	public String saveOrder(){
		order.setOrdertime(new Date());
		order.setState(1);//1��ʾδ����   2�Ѿ��������û�з���   3.�Ѿ�����������û��ȷ���ջ�  4.�������
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			this.addActionError("�ף�����û�й��������ѡ��Ʒ��");
			return "msg";
		}
		order.setTotal(cart.getTotal());

		for(CartItem cartItem : cart.getCartItems()){
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}
		
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser == null){
			this.addActionError("����δ��½�����ȵ�½��");
			return "login";
		}
		
		order.setUser(existUser);
		
		orderService.saveOrder(order);
		
		return "saveSuccess";
	}

}
