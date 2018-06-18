package com.vision.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vision.shoppingbackend.dao.CartLineDao;
import com.vision.shoppingbackend.dto.Cart;
import com.vision.shoppingbackend.dto.CartLine;
import com.vision.shoppingbackend.dto.OrderDetail;

@Repository("cartLineDAO")
@Transactional
public class CartLineDaoImpl implements CartLineDao {
	
	

	@Autowired
	private SessionFactory sessionFactory;
	
	// add a item  to cart 
	
	@Override
	public boolean add(CartLine cartLine) {
		
		try {
			 sessionFactory.getCurrentSession().persist(cartLine);
			 return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
	// update the item
	
	@Override
	public boolean update(CartLine cartLine) {
		try {
			 sessionFactory.getCurrentSession().update(cartLine);
			 return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}


	@Override
	public boolean remove(CartLine cartLine) {
		try {
			 sessionFactory.getCurrentSession().delete(cartLine);
			 return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	

	@Override
	public List<CartLine> list(int cartId) {
		String listCartItem = "from CartLine where cartId=:cartId";

		return sessionFactory.getCurrentSession()
				.createQuery(listCartItem, CartLine.class)
							.setParameter("cartId", cartId)
									.getResultList();
	}
	
	
	@Override
	public List<CartLine> listAvailable(int cartId) {
		String query = "FROM CartLine WHERE cartId = :cartId AND available = :available";
		return sessionFactory.getCurrentSession()
								.createQuery(query, CartLine.class)
									.setParameter("cartId", cartId)
									.setParameter("available", true)
										.getResultList();
	}

	@Override
	public CartLine get(int cartId) {
		
		return sessionFactory.getCurrentSession().get(CartLine.class, Integer.valueOf(cartId));
	}

	

	@Override
	public CartLine getByCartAndProduct(int cartId, int productid) {
		String getByCartAndProduct="from CartLine where cartId=:cartId and product.id=:productid";
		
		return sessionFactory.getCurrentSession()
				.createQuery(getByCartAndProduct,CartLine.class)
					.setParameter("cartId", cartId)
						.setParameter("productid", productid).getSingleResult();
	}

	@Override
	public boolean updateCart(Cart cart) {
	
		try {
			 sessionFactory.getCurrentSession().update(cart);
			 return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addOrderdetails(OrderDetail orderDetail) {
		try {
			 sessionFactory.getCurrentSession().persist(orderDetail);
			 return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
