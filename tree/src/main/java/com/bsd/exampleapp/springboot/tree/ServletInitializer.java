package com.bsd.exampleapp.springboot.tree;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * Servlet init update to allow run on external web container (ex. tomcat).
 * 
 * @author JS 2018-10-14
 *
 */
public class ServletInitializer extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(TreeApplication.class);
	}

}
