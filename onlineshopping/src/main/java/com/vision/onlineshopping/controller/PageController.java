package com.vision.onlineshopping.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vision.onlineshopping.exception.ProductNotFoundException;
import com.vision.shoppingbackend.dao.CategoryDAO;
import com.vision.shoppingbackend.dao.ProductDAO;
import com.vision.shoppingbackend.dto.Category;
import com.vision.shoppingbackend.dto.Product;

@Controller
public class PageController {

	private static final Logger logger = LoggerFactory.getLogger(PageController.class);
	@Autowired
	private CategoryDAO categoryDao;

	@Autowired
	private ProductDAO productDao;

	@SuppressWarnings("all")
	@RequestMapping(value = { "/", "/index", "/home" })
	public ModelAndView index() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickHome", true);
		mv.addObject("categories", categoryDao.listofitem());
		mv.addObject("title", "Home");
		logger.debug("debug is started");

		return mv;

	}

	@RequestMapping(value = "/about")
	public ModelAndView about() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickAbout", true);
		mv.addObject("title", "About");
		logger.debug("debug is started");

		return mv;

	}

	@RequestMapping(value = "/contact")
	public ModelAndView contact() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickContact", true);
		mv.addObject("title", "Contact");
		logger.debug("debug is started");

		return mv;

	}

	@RequestMapping(value = "/login")
	public ModelAndView login(@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout) {
		ModelAndView mv = new ModelAndView("login");
		if (error != null) {
			mv.addObject("message", "Invalid User name and Password");
		}
		
		if (logout != null) {
			mv.addObject("logout", "User Logout Successfully");
		}
		mv.addObject("title", "login");
		logger.debug("debug is started");

		return mv;

	}
	/*
	 * Method to load category
	 */

	@RequestMapping(value = "/show/all/products")
	public ModelAndView showAllProducts() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickAllProducts", true);
		mv.addObject("categories", categoryDao.listofitem());
		mv.addObject("title", "Products");

		return mv;

	}

	@RequestMapping(value = "show/category/{id}/products")
	public ModelAndView showcategoryProduct(@PathVariable int id) {

		ModelAndView mv = new ModelAndView("page");
		Category category = null;
		category = categoryDao.get(id);

		mv.addObject("title", category.getName());

		// passing the list of categories
		mv.addObject("categories", categoryDao.listofitem());

		// passing single category
		mv.addObject("category", category);
		mv.addObject("userClickCategoryProducts", "true");

		logger.debug("debug is started");

		return mv;
	}

	/*
	 * Show The Single Product
	 */
	@RequestMapping(value = "show/{id}/product")
	public ModelAndView singleProduct(@PathVariable int id) throws ProductNotFoundException {

		ModelAndView mv = new ModelAndView("page");

		Product product = productDao.get(id);
		if (product == null)
			throw new ProductNotFoundException();
		product.setViews(product.getViews() + 1);
		productDao.update(product);

		mv.addObject("title", product.getName());
		mv.addObject("product", product);
		mv.addObject("userClickShowProduct", true);

		logger.debug("debug is started");

		return mv;
	}

	@SuppressWarnings("all")
	@RequestMapping(value = "/register")
	public ModelAndView register() {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickAbout", true);
		mv.addObject("title", "About");
		logger.debug("debug is started");

		return mv;

	}

	@RequestMapping(value = "/access-denied")
	public ModelAndView accessdenined() {
		ModelAndView mv = new ModelAndView("error");

		mv.addObject("title", "error 404- access-denied");

		mv.addObject("errortitle", "error 404- access-denied");
		logger.debug("debug is started");

		return mv;

	}

	@RequestMapping(value="/perform-logout")
	public String lagout(HttpServletRequest request,HttpServletResponse response) {
		
		Authentication authentication=SecurityContextHolder.getContext().getAuthentication();
		if(authentication!=null) {
			new SecurityContextLogoutHandler().logout(request, response, authentication);
		}
		return "redirect:/login?logout";
	}
	
}
