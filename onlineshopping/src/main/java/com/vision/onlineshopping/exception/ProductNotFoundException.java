package com.vision.onlineshopping.exception;

import java.io.Serializable;

public class ProductNotFoundException extends Exception implements Serializable {

	/**
	 * Product not Found Exception class
	 */
	private static final long serialVersionUID = 1L;

	private final String  message;

	public ProductNotFoundException() {
		this("Something wrong Please try again");
	}

	public ProductNotFoundException(String message) {
		this.message = System.currentTimeMillis() + " : " + message;
	}

	public String getMessage() {
		return message;
	}
}
