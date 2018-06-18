package com.vision.shoppingbackend.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.vision.shoppingbackend.dao.CategoryDAO;
import com.vision.shoppingbackend.dto.Category;

@Repository("categoryDao")
@Transactional
public class CategoryDaoImpl implements CategoryDAO {

	@Autowired
	private SessionFactory sessionFactory;

	/*
	 * Fetch a single Category from database
	 */
	@Override
	public Category get(int id) {

		return sessionFactory.getCurrentSession().get(Category.class, Integer.valueOf(id));
	}

	/*
	 * Fetch a List of Category from database
	 */
	@Override
	public List<Category> listofitem() {
		String selectActiveCategory = "From Category Where active=:active";
		return sessionFactory.getCurrentSession().
				createQuery(selectActiveCategory,Category.class)
							.setParameter("active", true).getResultList();
	}

	/*
	 * Add Category to the datebase
	 */

	@Override
	public boolean add(Category category) {

		try {
			sessionFactory.getCurrentSession().persist(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	/*
	 * update the Category to the database
	 */

	@Override
	public boolean update(Category category) {
		try {
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Category category) {
		try {
			category.setActive(false);
			sessionFactory.getCurrentSession().update(category);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}
