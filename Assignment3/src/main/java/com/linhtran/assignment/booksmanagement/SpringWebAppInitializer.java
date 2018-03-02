package com.linhtran.assignment.booksmanagement;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import com.linhtran.assignment.booksmanagement.configure.RootConfig;
import com.linhtran.assignment.booksmanagement.configure.WebConfig;

public class SpringWebAppInitializer 
          extends AbstractAnnotationConfigDispatcherServletInitializer {

	@Override
	protected Class<?>[] getRootConfigClasses() {
		return new Class<?>[] { RootConfig.class };
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {
		return new Class<?>[] { WebConfig.class };
	}

	@Override
	protected String[] getServletMappings() {
		return new String[] {"/"};
	}
	

}
