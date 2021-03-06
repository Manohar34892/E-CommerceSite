package com.vision.shoppingbackend.dao;

import java.util.List;

import com.vision.shoppingbackend.dto.Product;

public interface ProductDAO {

	Product get(int productid);

	List<Product> listofitem();

	boolean add(Product product);

	boolean update(Product product);

	boolean delete(Product product);

	// business methods
	List<Product> listActiveProducts();

	List<Product> listActiveProductsByCategory(int categoryId);

	List<Product> getLatestActiveProducts(int count);
}
