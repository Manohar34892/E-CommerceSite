package com.vision.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vision.shoppingbackend.dao.UserDao;
import com.vision.shoppingbackend.dto.Cart;
import com.vision.shoppingbackend.dto.User;

public class UserTestCase {

	private static AnnotationConfigApplicationContext context = null;
	private static UserDao userDao;

	private static User user;
	private static Cart cart;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.vision.shoppingbackend");
		context.refresh();
		userDao = context.getBean(UserDao.class, "userDAO");
		
	}

	@Test
	public void userTestCase() {
		
		user= new User();
		user.setFirstName("firstname1");
		user.setLastName("lastName1");
		user.setContactNumber("9492680288");
		user.setPassword("aaaaa");
		user.setEmail("testmail1@gmail.com");
		
		cart= new Cart();
		cart.setCartLines(1);
		cart.setGrandTotal(10000);
		cart.setUser(user);
		user.setCart(cart);
		
		assertEquals("Successfully add a user",true, userDao.addUser(user));
		user= new User();
		user.setFirstName("firstname2");
		user.setLastName("lastName2");
		user.setContactNumber("9492680287");
		user.setPassword("aaaaan");
		user.setEmail("testmail12@gmail.com");
		
		cart= new Cart();
		cart.setCartLines(2);
		cart.setGrandTotal(25000);
		cart.setUser(user);
		user.setCart(cart);

		assertEquals("Successfully add a user",true, userDao.addUser(user));
		
		
		user= new User();
		user.setFirstName("firstname3");
		user.setLastName("lastName3");
		user.setContactNumber("9492680286");
		user.setPassword("aaaaalm");
		user.setEmail("testmail3@gmail.com");
		assertEquals("Successfully add a user",true, userDao.addUser(user));
		
		assertEquals("Successfully fetch the user","firstname3", userDao.getId(3).getFirstName());
		
		String  name=userDao.getByEmail("testmail1@gmail.com").getFirstName();
		System.out.println(name);
		assertEquals("Successfully fetch the user","firstname1",userDao.getByEmail("testmail1@gmail.com").getFirstName());

		
	}
}
