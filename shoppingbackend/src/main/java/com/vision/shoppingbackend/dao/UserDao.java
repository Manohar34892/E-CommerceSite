
package com.vision.shoppingbackend.dao;

import java.util.List;

import com.vision.shoppingbackend.dto.Address;
import com.vision.shoppingbackend.dto.User;

public interface UserDao {

	//user related details
	User getByEmail(String email);
	User getId(int id);
	
	boolean addUser(User user);
	
	//adding and updating Address 
	Address getAddress(int addressId);
	
	boolean addAddress(Address address);
	boolean updateAddress(Address address);
	Address getBillingAddress(int userId);
	List<Address> listShippingAddresses(int userId);
	
}
