package com.vision.onlineshopping.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.vision.shoppingbackend.dao.ProductDAO;
import com.vision.shoppingbackend.dto.Product;

@Controller
@RequestMapping("/json/data")
public class JsondataController {

	private static final Logger logger=LoggerFactory.getLogger(JsondataController.class);
	@Autowired
	private ProductDAO productDAO;

	@RequestMapping(value = "/all/products")
	@ResponseBody
	public List<Product> getAllProduct() {
		logger.debug("debug is started");
		logger.info("info is started");

		return productDAO.listActiveProducts();
	}
	
	
	@RequestMapping(value = "category/{categoryId}/products")
	@ResponseBody
	public List<Product> getProductByCategory(@PathVariable int categoryId ) {
	
		logger.debug("debug is started");
		logger.info("info is started");

		return productDAO.listActiveProductsByCategory(categoryId);
	}

	
}
