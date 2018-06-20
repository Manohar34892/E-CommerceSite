package com.vision.onlineshopping.validator;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.vision.shoppingbackend.dto.Product;

public class ProductValidator implements Validator{

	@Override
	public boolean supports(Class<?> claxx) {
		// TODO Auto-generated method stub
		return Product.class.equals(claxx);
	}

	@Override
	public void validate(Object target, Errors error) {
		
		Product product=(Product) target;
		if(product.getFile()==null || 
				product.getFile().getOriginalFilename().equals("")) {
			error.rejectValue("file", null, "Please select the Image file to upload");
			return;
		}
		if(!(product.getFile().getContentType().equals("image/jpeg")||
				product.getFile().getContentType().equals("image/png")
				||product.getFile().getContentType().equals("image/jpeg"))){
			error.rejectValue("file", null, "Please select the Image type jpg file to upload");
			return;	
				}
	}

}
