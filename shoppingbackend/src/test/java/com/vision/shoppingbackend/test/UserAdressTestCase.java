package com.vision.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vision.shoppingbackend.dao.UserDao;
import com.vision.shoppingbackend.dto.Address;

public class UserAdressTestCase {

	
	private static AnnotationConfigApplicationContext context = null;
	private static UserDao userDao;

	private static Address address;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.vision.shoppingbackend");
		context.refresh();
		userDao = context.getBean(UserDao.class, "userDAO");

	}
	
	@Test
	public void adressTestCase() {
		
		address= new Address();
		address.setAddressLineOne("14 Kphb");
		address.setBilling(true);
		address.setCity("hyd");
		address.setShipping(true);
		address.setUserid(1);
		assertEquals("successfully added", true,userDao.addAddress(address));
		
		address= new Address();
		address.setAddressLineOne("14 Kphb");
		address.setBilling(false);
		address.setCity("bangolre");
		address.setShipping(true);
		address.setUserid(1);
		assertEquals("successfully added", true,userDao.addAddress(address));
		
		address= new Address();
		address.setAddressLineOne("14 Kphbrr");
		address.setBilling(false);
		address.setCity("hydrabad");
		address.setShipping(true);
		address.setUserid(2);
		assertEquals("successfully added", true,userDao.addAddress(address));
		
		
		address.setPostalCode("524689");
		assertEquals("successfully added", true,userDao.updateAddress(address));
		
		assertEquals("successfully fetch address", "hydrabad",userDao.getAddress(3).getCity());
		
		assertEquals("successfully fetch address", "hyd",userDao.getBillingAddress(1).getCity());
		
	//	assertEquals("successfully fetch address", 2,userDao.listShippingAddresses(1).size());
		
	}

}
