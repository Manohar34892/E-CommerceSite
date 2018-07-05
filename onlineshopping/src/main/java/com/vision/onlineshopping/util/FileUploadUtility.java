package com.vision.onlineshopping.util;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtility {

	private FileUploadUtility(){
		throw new IllegalStateException("Utility class");
	}
	private static final String ABS_PATH = "F:\\E-Commerce\\E-CommerceSite\\onlineshopping\\src\\main\\webapp\\assest\\images\\";

	private static String REAL_PATH = "";
	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtility.class);
	

	public static void uploadFile(HttpServletRequest request, MultipartFile file, String code) {

		REAL_PATH = request.getSession().getServletContext().getRealPath("/assest/images");
		logger.info(REAL_PATH);
		// to make sure all file are exist are not
		// please create directories
		if (!new File(ABS_PATH).exists()) {
			new File(ABS_PATH).mkdirs();
		}
		// to make sure all file are exist are not
		// please create directories
		if (!new File(REAL_PATH).exists()) {
			new File(REAL_PATH).mkdirs();
		}

		try {
			file.transferTo(new File(REAL_PATH + code + ".jpg"));
			
			file.transferTo(new File(ABS_PATH + code + ".jpg"));
			logger.info("successfully ");
		} catch (IOException e) {
			((ServletContext) logger).log("context", e);
		}
	}

}
