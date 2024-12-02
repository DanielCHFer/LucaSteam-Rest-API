package com.ejemplos.spring.controller.error;

import java.util.Map;

import org.springframework.boot.web.error.ErrorAttributeOptions;
import org.springframework.boot.web.servlet.error.DefaultErrorAttributes;
import org.springframework.web.context.request.WebRequest;

public class CustomDefaultError extends DefaultErrorAttributes{
	
	@Override
	public Map<String, Object> getErrorAttributes(WebRequest webRequest, ErrorAttributeOptions options) {
		
		Map<String, Object> errorAttributes = super.getErrorAttributes(webRequest, options);
		errorAttributes.remove("trace");

		
	}

}
