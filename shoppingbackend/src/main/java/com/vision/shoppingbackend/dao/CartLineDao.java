package com.vision.shoppingbackend.dao;

import java.util.List;

import com.vision.shoppingbackend.dto.Cart;
import com.vision.shoppingbackend.dto.CartLine;
import com.vision.shoppingbackend.dto.OrderDetail;

public interface CartLineDao {

	
	
	public boolean add(CartLine cartLine);
	public boolean remove(CartLine cartLine);
	public boolean update(CartLine cartLine);
	
	public List<CartLine> list(int cartId);
	public List<CartLine> listAvailable(int cartId);
	
	public CartLine get(int cartId);
	
	//fetch the cARTLINE based on the cartid and productid
	public CartLine getByCartAndProduct(int cartId,int productid);
	
	//updating the cart
	public boolean updateCart(Cart cart );
	
	//adding the order detail to the cart
	public boolean addOrderdetails(OrderDetail orderDetail);

}
