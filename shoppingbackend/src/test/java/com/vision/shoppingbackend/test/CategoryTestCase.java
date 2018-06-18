package com.vision.shoppingbackend.test;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.vision.shoppingbackend.dao.CategoryDAO;
import com.vision.shoppingbackend.dto.Category;

public class CategoryTestCase {

	private static AnnotationConfigApplicationContext context = null;

	private static CategoryDAO categoryDAO;

	private Category category;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.vision.shoppingbackend");
		context.refresh();

		categoryDAO = (CategoryDAO) context.getBean("categoryDao");
	}

	// Add Category to the datebase

	@Test
	public void addCategory() {

		category = new Category();
		category.setName("Laptop");
		category.setDescription("This is small descririon Laptop");
		category.setImageURL("Laptop_Url");

		assertEquals("Successfully add a category in  the database", true, categoryDAO.add(category));

	}

	// Fetch a single Category from database

//	@Test
//	public void getCategory() {
//		category = categoryDAO.get(2);
//		assertEquals("Successfully fetch a single category from the database", "Mobile", category.getName());
//	}

	// update the Category to the database

//	@Test
//	public void updateCategory() {
//
//		category = categoryDAO.get(2);
//		category.setName("Refrigerator");
//		category.setActive(true);
//		category.setDescription("This is small descririon refrigerator");
//		category.setImageURL("refrigerator_url");
//		assertEquals("Successfully update a category to the database", true, categoryDAO.update(category));
//	}

	// Delete Category from database

//	@Test
//	public void deleteCategory() {
//		category = categoryDAO.get(1);
//		assertEquals("Successfully DeActive a category into the database", true, categoryDAO.delete(category));
//	}

	// Fetch List Category from database which are Active

	@Test
	public void listOfCategory() {

		assertEquals("Successfully Fetch list of category from the database", 3, categoryDAO.listofitem().size());
	}

	// aLL THE TEST CASE IN CURD OPERATION

	@Test
	public void testCurdCategory() {

		// add a category
		category = new Category();
		category.setName("TV");
		category.setDescription("This is small descririon TV");
		category.setImageURL("Tv_Url");

		assertEquals("Successfully add a category in  the database", true, categoryDAO.add(category));

		category = new Category();
		category.setName("Mobile");
		category.setDescription("This is small descririon Mobile");
		category.setImageURL("Mobile_Url");

		assertEquals("Successfully add a category in  the database", true, categoryDAO.add(category));

		// fetch a single category category = categoryDAO.get(2);
		assertEquals("Successfully fetch a single category from the database", "Mobile", category.getName());

		// updating a category category.setName("Refrigerator");
		category.setDescription("This is small descririon refrigerator");
		category.setImageURL("refrigerator_url");
		assertEquals("Successfully update a category to the database", true, categoryDAO.update(category));

		// deleting a category

		category = categoryDAO.get(2);
		assertEquals("Successfully DeActive a category into the database", true, categoryDAO.delete(category));

		assertEquals("Successfully Fetch list of category from the database", 4, categoryDAO.listofitem().size());
	}
}
