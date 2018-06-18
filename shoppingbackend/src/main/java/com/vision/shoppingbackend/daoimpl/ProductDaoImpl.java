package com.vision.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vision.shoppingbackend.dao.ProductDAO;
import com.vision.shoppingbackend.dto.Product;

@Repository("productDAO")
@Transactional
public class ProductDaoImpl implements ProductDAO {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public Product get(int productid) {

		return sessionFactory.getCurrentSession()
				.get(Product.class, Integer.valueOf(productid));
	}

	@Override
	public List<Product> listofitem() {

		return sessionFactory.getCurrentSession()
				.createQuery("FROM Product", Product.class).getResultList();
	}

	@Override
	public boolean add(Product product) {

		System.out.println(product.getBrand());
		try {
			sessionFactory.getCurrentSession().persist(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(Product product) {
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Product product) {
		product.setActive(false);
		try {
			sessionFactory.getCurrentSession().update(product);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}	}

	@Override
	public List<Product> listActiveProducts() {
		String selectlistActiveProducts="FROM Product WHERE active=:active";
		
		return sessionFactory.getCurrentSession()
				.createQuery(selectlistActiveProducts,Product.class)
							.setParameter("active", true).getResultList();
	}

	@Override
	public List<Product> listActiveProductsByCategory(int categoryId) {
String selectlistActiveProductsByCategory="FROM Product WHERE active=:active and categoryId=:categoryId ";
		
		return sessionFactory.getCurrentSession()
				.createQuery(selectlistActiveProductsByCategory,Product.class)
							.setParameter("active", true).setParameter("categoryId", categoryId).getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		return sessionFactory.getCurrentSession()
				.createQuery("From Product where active=:active Order By id",Product.class)
							.setParameter("active", true)
									.setFirstResult(0).setMaxResults(count)
											.getResultList();
	}

}
