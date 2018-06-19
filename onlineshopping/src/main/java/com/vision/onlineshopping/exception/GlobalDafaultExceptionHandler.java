package com.vision.onlineshopping.exception;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.vision.shoppingbackend.dao.CategoryDAO;

@ControllerAdvice
public class GlobalDafaultExceptionHandler {

	private final static Logger logger=LoggerFactory.getLogger(GlobalDafaultExceptionHandler.class);
	@Autowired
	private CategoryDAO categoryDao;
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView handlerNotFoundException() {
		ModelAndView mv= new ModelAndView("page");
		mv.addObject("title", "Home");
		logger.info("Logger info started");
		logger.debug("Logger info started");
		// passing the list of categories
		mv.addObject("categories",categoryDao.listofitem());
		mv.addObject("userClickHome", "true");
		return mv;
		
	}
	
	@ExceptionHandler(ProductNotFoundException.class)
	public ModelAndView handlerProductNotFoundException() {
		ModelAndView mv= new ModelAndView("error");
		mv.addObject("title", "error");
		logger.info("Logger info started");
		logger.debug("Logger info started");
		// passing the list of categories
		mv.addObject("errortitle", "Something not right");
		mv.addObject("title", "Produt UnAvailable");
		return mv;
		
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handlerException(Exception ex) {
		ModelAndView mv= new ModelAndView("error");
		mv.addObject("title", "error");
		logger.info("Logger info started");
		logger.debug("Logger info started");
		// passing the list of categories
		mv.addObject("errortitle", "Contact Administrator");
		mv.addObject("errordescription",ex.toString());
		mv.addObject("title", "Produt UnAvailable");
		return mv;
		
	}
}
