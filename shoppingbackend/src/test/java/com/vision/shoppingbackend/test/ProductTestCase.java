package com.vision.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vision.shoppingbackend.dao.ProductDAO;
import com.vision.shoppingbackend.dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context = null;

	
	private static ProductDAO productDao;

	private Product product;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.vision.shoppingbackend");
		context.refresh();
		productDao=(ProductDAO) context.getBean("productDao");
	}
	
	@Test
	public void testProductCurd() {
		
		product=new Product();
		product.setName("iPhone 5s");
		product.setBrand("Apple");
		product.setDescription("This is small description about Apple mobile");
		product.setUnitprice(25000);
		product.setActive(true);
		product.setCategoryid(3);
		product.setSupplierid(3);
		product.setQuantity(0);
		
		assertEquals("Product inserted Successfuly ", true, productDao.add(product));
		
		product=new Product();
		product.setName("SamSang S3");
		product.setBrand("SamSang");
		product.setDescription("This is small description about SamSang mobile");
		product.setUnitprice(23000);
		product.setActive(true);
		product.setCategoryid(3);
		product.setSupplierid(3);
		product.setQuantity(5);
		assertEquals("Product inserted Successfuly ", true, productDao.add(product));
		
		product=new Product();
		product.setName("SamSang S7");
		product.setBrand("SamSang");
		product.setDescription("This is small description about SamSang mobile");
		product.setUnitprice(23000);
		product.setActive(true);
		product.setCategoryid(3);
		product.setSupplierid(2);
		product.setQuantity(4);
		assertEquals("Product inserted Successfuly ", true, productDao.add(product));
		
		product=new Product();
		product.setName("Asus Vivo Notebook");
		product.setBrand("Asus");
		product.setDescription("This is small description about Asus laptop");
		product.setUnitprice(27800);
		product.setActive(true);
		product.setCategoryid(4);
		product.setSupplierid(2);
		product.setQuantity(4);
		assertEquals("Product inserted Successfuly ", true, productDao.add(product));
		
		product= productDao.get(2);
		product.setUnitprice(28000);
		product.setPurchases(5);
		product.setViews(150);
		assertEquals("Product inserted Successfuly ", true, productDao.update(product));
		
		assertEquals("Product inserted Successfuly ", true, productDao.delete(product));
		
		assertEquals("Product inserted Successfuly ", 4, productDao.listofitem().size());
		
		assertEquals("Product inserted Successfuly ", 3, productDao.listActiveProducts().size());
		assertEquals("Product inserted Successfuly ", 2, productDao.listActiveProductsByCategory(3).size());
		assertEquals("Product inserted Successfuly ", 3, productDao.getLatestActiveProducts(4).size());
		
	}
}
