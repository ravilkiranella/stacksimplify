package com.stacksimplify.restservicesdemo;

import java.util.Locale;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

@SpringBootApplication
public class SpringbootBuildingblocksdemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBuildingblocksdemoApplication.class, args);
	}

	@Bean
	public LocaleResolver localeResolver() {
		AcceptHeaderLocaleResolver resolver = new AcceptHeaderLocaleResolver();
		resolver.setDefaultLocale(Locale.US);
		return resolver;
	}
	
	@Bean
	public ResourceBundleMessageSource messageSource() {
		ResourceBundleMessageSource msgSource =  new ResourceBundleMessageSource();
		msgSource.setBasename("messages");
		return msgSource;
		
	}
}
