package com.vision.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vision.shoppingbackend.dao.UserDao;
import com.vision.shoppingbackend.dto.Address;
import com.vision.shoppingbackend.dto.User;

@Repository("userDAO")
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory sessionFactory;
	
	
	

	@Override
	public User getId(int id) {
		
		return sessionFactory.getCurrentSession().get(User.class, id);

	}
	
	@Override
	public User getByEmail(String email) {
		String selectQuery = "FROM User WHERE email = :email";
		
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,User.class)
						.setParameter("email",email).getSingleResult();
		
							
	}

	@Override
	public boolean addUser(User user) {
		try {
			sessionFactory.getCurrentSession().persist(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Address getAddress(int addressId) {
		
		return sessionFactory.getCurrentSession().get(Address.class, addressId);
	}

	@Override
	public boolean addAddress(Address address) {
		try {
			sessionFactory.getCurrentSession().persist(address);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateAddress(Address address) {
		try {
			sessionFactory.getCurrentSession().update(address);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Address getBillingAddress(int userId) {
		String selectQuery = "FROM Address WHERE userid = :userId AND billing = :isBilling";
		try{
		return sessionFactory
				.getCurrentSession()
					.createQuery(selectQuery,Address.class)
						.setParameter("userId", userId)
						.setParameter("isBilling", true)
						.getSingleResult();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
	@SuppressWarnings({ "unchecked", "deprecation" })
	@Override
	public List<Address> listShippingAddresses(int userId) {
		
		return sessionFactory.getCurrentSession().createCriteria(Address.class).
				add(Restrictions.eq("userId", userId)).add(Restrictions.eq("shipping", true)).list();
		
				
	}

}
