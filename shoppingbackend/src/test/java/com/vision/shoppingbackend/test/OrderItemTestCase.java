package com.vision.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vision.shoppingbackend.dao.CartLineDao;
import com.vision.shoppingbackend.dao.ProductDAO;
import com.vision.shoppingbackend.dao.UserDao;
import com.vision.shoppingbackend.dto.OrderDetail;
import com.vision.shoppingbackend.dto.OrderItem;
import com.vision.shoppingbackend.dto.Product;
import com.vision.shoppingbackend.dto.User;

public class OrderItemTestCase {

	private static UserDao userDAO;
	private static ProductDAO productDAO;
	private static CartLineDao cartLineDAO;
	
	private static AnnotationConfigApplicationContext context;
	 
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
	public void OrderdetailsTest() {
		
		User user=userDAO.getByEmail("testmail1@gmail.com");
				Product product=productDAO.get(3);
				
		OrderItem item=new OrderItem();
		item.setProduct(product);
		item.setBuyingPrice(product.getUnitprice());
		item.setProductCount(2);
		item.setTotal(product.getUnitprice()*item.getProductCount());
		
		
		OrderDetail orderDetail= new OrderDetail();
		orderDetail.setUser(user);
		orderDetail.setBilling(userDAO.getAddress(1));
		orderDetail.setShipping(userDAO.getAddress(2));
		orderDetail.setOrderTotal(item.getTotal());
		List<OrderItem> l= new ArrayList<>();
			l.add(item)	;
				
		item.setOrderDetail(orderDetail);
		orderDetail.setOrderItems(l);
		//orderDetail.setOrderItems(item);
		assertEquals("Successfuly odred item ",true,cartLineDAO.addOrderdetails(orderDetail));
				
	}
	
}
