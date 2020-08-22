package com.example.selenium.configurations;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;

@Configuration
@ComponentScan(basePackages = { "com.example.selenium.webpages" })
public class ApplicationConfigContext {
	private static final Logger LOG = LoggerFactory.getLogger(ApplicationConfigContext.class);

	public static void main(String args[]) {
		ApplicationContext applicationContext = new AnnotationConfigApplicationContext(ApplicationConfigContext.class);
		LOG.info("application started");
	}
	
	public static PropertySourcesPlaceholderConfigurer propertyConfigIn() {
        return new PropertySourcesPlaceholderConfigurer();
    }

	@PostConstruct
	public void IsApplicationLoaded() {
		LOG.info("Application config loaded!");
	}

}