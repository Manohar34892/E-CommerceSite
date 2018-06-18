package com.vision.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vision.shoppingbackend.dao.CartLineDao;
import com.vision.shoppingbackend.dao.ProductDAO;
import com.vision.shoppingbackend.dao.UserDao;
import com.vision.shoppingbackend.dto.Cart;
import com.vision.shoppingbackend.dto.CartLine;
import com.vision.shoppingbackend.dto.Product;
import com.vision.shoppingbackend.dto.User;

public class CartLineTestCase {

	private static AnnotationConfigApplicationContext context;

	private static CartLineDao cartLineDAO;
	private static ProductDAO productDAO;
	private static UserDao userDAO;

	private CartLine cartLine = null;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.vision.shoppingbackend");
		context.refresh();
		cartLineDAO = (CartLineDao) context.getBean("cartLineDAO");
		productDAO = (ProductDAO) context.getBean("productDAO");
		userDAO = (UserDao) context.getBean("userDAO");
	}
	
	
	@Test
	public void testAddCartLine() {
		
		// fetch the user and then cart of that user
		User user = userDAO.getByEmail("testmail1@gmail.com");	
		
		Cart cart = user.getCart();
		
		// fetch the product 
		Product product = productDAO.get(3);
		
		// Create a new CartLine
		cartLine = new CartLine();
		
		cartLine.setCartId(cart.getId());
		
		cartLine.setProduct(product);
		
		cartLine.setProductCount(1);
		
		double oldTotal = cartLine.getTotal();		
		
		cartLine.setBuyingPrice(product.getUnitprice());
		cartLine.setTotal(product.getUnitprice() * cartLine.getProductCount());
		
		cart.setCartLines(cart.getCartLines() + 1);
		
		cart.setGrandTotal(cart.getGrandTotal() + (cartLine.getTotal() - oldTotal));
		
		assertEquals("Failed to add the CartLine!",true, cartLineDAO.add(cartLine));
		assertEquals("Failed to update the cart!",true, cartLineDAO.updateCart(cart));
		
	}
	
	@Test
	public void testUpdateCartLine() {

		// fetch the user and then cart of that user
		User user = userDAO.getByEmail("testmail1@gmail.com");		
		Cart cart = user.getCart();
				
		cartLine = cartLineDAO.getByCartAndProduct(cart.getId(), 3);
		
		cartLine.setProductCount(cartLine.getProductCount() + 1);
				
		double oldTotal = cartLine.getTotal();
				
		cartLine.setTotal(cartLine.getProduct().getUnitprice() * cartLine.getProductCount());
		
		cart.setGrandTotal(cart.getGrandTotal() + (cartLine.getTotal() - oldTotal));
		
		assertEquals("Failed to update the CartLine!",true, cartLineDAO.update(cartLine));	

		
	}

}
