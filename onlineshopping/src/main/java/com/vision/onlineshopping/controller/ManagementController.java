package com.vision.onlineshopping.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.vision.onlineshopping.util.FileUploadUtility;
import com.vision.onlineshopping.validator.ProductValidator;
import com.vision.shoppingbackend.dao.CategoryDAO;
import com.vision.shoppingbackend.dao.ProductDAO;
import com.vision.shoppingbackend.dto.Category;
import com.vision.shoppingbackend.dto.Product;

@Controller
@RequestMapping(value = "/manage/products")
public class ManagementController {

	private static final Logger logger = LoggerFactory.getLogger(ManagementController.class);
	@Autowired
	private CategoryDAO categoryDao;

	@Autowired
	private ProductDAO productDao;

	@GetMapping
	public ModelAndView showManageProducts(@RequestParam(name = "operation", required = false) String operation) {
		ModelAndView mv = new ModelAndView("page");
		mv.addObject("userClickManageProduct", true);
		mv.addObject("title", "Manage Products");
		Product nProduct = new Product();
		nProduct.setActive(true);
		nProduct.setSupplierid(1);

		if (operation != null) {
			if (operation.equals("product")) {
				mv.addObject("message", "Product submited database Successfully");
			}
		}
		mv.addObject("product", nProduct);
		return mv;

	}

	// submission Product to data base
	@PostMapping
	public String handleProductSubmission(@Valid @ModelAttribute("product") Product mProduct, BindingResult result,
			Model model, HttpServletRequest request) {

		new ProductValidator().validate(mProduct, result);

		if (result.hasErrors()) {
			model.addAttribute("userClickManageProduct", true);
			model.addAttribute("title", "Manage Products");
			return "page";
		}

		productDao.add(mProduct);

		logger.info("file name" + mProduct.getFile().toString() + " " + mProduct.getCode());

		if (!mProduct.getFile().getOriginalFilename().equals("")) {

			logger.info("file name" + mProduct.getFile().toString() + " " + mProduct.getCode());
			FileUploadUtility.uploadFile(request, mProduct.getFile(), mProduct.getCode());

		}

		logger.info(mProduct.toString());

		return "redirect:/manage/products?operation=product";
	}

	// return category id
	@ModelAttribute("categories")
	List<Category> getCategories() {

		return categoryDao.listofitem();
	}

}
