package com.cn.safety.crawler;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.cn.safety.crawler.processor.LegaldailyProcessor;
import com.cn.safety.crawler.processor.XinhuanetProcessor;
import com.cn.safety.service.impl.UserServiceImpl;

public class NewsCrawler {

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring-mvc.xml");
		BeanFactory factory = (BeanFactory) applicationContext;
		final XinhuanetProcessor myCrawler = (XinhuanetProcessor) factory.getBean("xinhuanetProcessor");
		myCrawler.crawl();
	}
}
