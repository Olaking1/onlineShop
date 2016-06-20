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
		order.setState(1);//1表示未付款   2已经付款，但是没有发货   3.已经发货，但是没有确认收货  4.交易完成
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			this.addActionError("亲，您还没有购物，请先挑选商品！");
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
			this.addActionError("您还未登陆，请先登陆！");
			return "login";
		}
		
		order.setUser(existUser);
		
		orderService.saveOrder(order);
		
		return "saveSuccess";
	}

}
