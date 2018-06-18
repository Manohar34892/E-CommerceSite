package com.vision.shoppingbackend.dao;

import java.util.List;

import com.vision.shoppingbackend.dto.Category;

public interface CategoryDAO {

	
	Category get(int id);
	List<Category> listofitem();
	boolean add(Category category);
	boolean update(Category category);
	boolean delete(Category category);
}
