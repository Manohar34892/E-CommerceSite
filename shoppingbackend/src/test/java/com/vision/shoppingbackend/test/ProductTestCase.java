package com.vision.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vision.shoppingbackend.dao.ProductDAO;
import com.vision.shoppingbackend.dto.Product;

public class ProductTestCase {

	private static AnnotationConfigApplicationContext context = null;

	
	private static ProductDAO productDAO;

	private Product product;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.vision.shoppingbackend");
		context.refresh();
		productDAO=(ProductDAO) context.getBean("ProductDAO");
	}
	
	@Test
	public void testProductCurd() {
		
		product=new Product();
		product.setName("Oppo Selife S3");
		product.setBrand("OPPO");
		product.setDescription("This is small description about oppo mobile");
		product.setUnitprice(20000);
		product.setActive(true);
		product.setCategoryid(3);
		product.setSupplierid(3);
		product.setQuantity(3);
		
		assertEquals("Product inserted Successfuly ", true, productDAO.add(product));
		
		product=new Product();
		product.setName("SamSang S3");
		product.setBrand("SamSang");
		product.setDescription("This is small description about SamSang mobile");
		product.setUnitprice(23000);
		product.setActive(true);
		product.setCategoryid(3);
		product.setSupplierid(3);
		product.setQuantity(5);
		assertEquals("Product inserted Successfuly ", true, productDAO.add(product));
		
		product=new Product();
		product.setName("SamSang S7");
		product.setBrand("SamSang");
		product.setDescription("This is small description about SamSang mobile");
		product.setUnitprice(23000);
		product.setActive(true);
		product.setCategoryid(3);
		product.setSupplierid(2);
		product.setQuantity(4);
		assertEquals("Product inserted Successfuly ", true, productDAO.add(product));
		
		product=new Product();
		product.setName("Asus Vivo Notebook");
		product.setBrand("Asus");
		product.setDescription("This is small description about Asus laptop");
		product.setUnitprice(27800);
		product.setActive(true);
		product.setCategoryid(4);
		product.setSupplierid(2);
		product.setQuantity(4);
		assertEquals("Product inserted Successfuly ", true, productDAO.add(product));
		
		product= productDAO.get(2);
		product.setUnitprice(28000);
		product.setPurchases(5);
		product.setViews(150);
		assertEquals("Product inserted Successfuly ", true, productDAO.update(product));
		
		assertEquals("Product inserted Successfuly ", true, productDAO.delete(product));
		
		assertEquals("Product inserted Successfuly ", 4, productDAO.listofitem().size());
		
		assertEquals("Product inserted Successfuly ", 3, productDAO.listActiveProducts().size());
		assertEquals("Product inserted Successfuly ", 2, productDAO.listActiveProductsByCategory(3).size());
		assertEquals("Product inserted Successfuly ", 3, productDAO.getLatestActiveProducts(4).size());
		
	}
}
