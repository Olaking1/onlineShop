package cn.amy.shop.cart.vo;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart {
	//购物车属性
	private Map<Integer , CartItem> map = new LinkedHashMap<Integer, CartItem>(); 
	private double total;
	
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	//购物车功能
	public void addCart(CartItem cartItem){
		Integer pid = cartItem.getProduct().getPid();
		if(map.containsKey(pid)){
			CartItem _cartItem = map.get(pid);
			_cartItem.setCount(_cartItem.getCount() + cartItem.getCount());
		}else{
			map.put(pid, cartItem);
		}
		
		total += cartItem.getSubtotal();
	}
	

	public void removeCart(Integer pid){
		CartItem cartItem = map.remove(pid);
		total -= cartItem.getSubtotal();
	}
	
	public void clearCart(){
		total = 0;
		map.clear();
	}
	
	public double getTotal() {
		return total;
	}
}
