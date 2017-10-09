package org.etoak.modules.demo;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;


public class Tests {
	public static void main(String[] args) {
		System.out.println("<<<<<start");

		Resource resources =  new ClassPathResource("spring/applicationContext.xml");
		Resource resources1 =  new ClassPathResource("jdbc.properties");
		BeanFactory factory = new XmlBeanFactory(resources);
		System.out.println(factory.getBean("sqlSessionFactory"));
		System.out.println(factory.getBean("DemoMapper"));
	}
}
