package com.demo.SpringDemo;

import org.springframework.beans.factory.BeanFactory;
//import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
  public static void main(String[] args) {
	
//	  BeanFactory factory = new XmlBeanFactory(new FileSystemResource("spring.xml"));
	  BeanFactory factory = new ClassPathXmlApplicationContext("spring.xml");
	  Alien alien = (Alien)factory.getBean("alien");
	  alien.code();
	  
	  System.out.println(alien.getAge());
  }
}
