package com.cn.safety.crawler;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import us.codecraft.webmagic.Spider;

import com.cn.safety.crawler.pipeline.CrawlNewsPipeline;
import com.cn.safety.crawler.processor.LegaldailyProcessor;
import com.cn.safety.crawler.processor.XinhuanetProcessor;
import com.cn.safety.service.IUserService;
import com.cn.safety.service.impl.UserServiceImpl;

@Service
public class NewsCrawler {

	@Resource
	private CrawlNewsPipeline crawlNewsPipeline;

	/*
	 * 抓取的入口方法
	 */
	@Scheduled(cron = "0 39 * * * ?")
	public void crawl(String URL_Seed) {
		// TODO 取出所有url的list，for循环Spider.create
		// 一定要new一个processor，因为spring注入是单例
		Spider.create(new LegaldailyProcessor())
				.addUrl(URL_Seed)
				.addPipeline(crawlNewsPipeline)
				.thread(5)
				.run();
	}

	public static void main(String[] args) {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring-mvc.xml");
		BeanFactory factory = (BeanFactory) applicationContext;
		final XinhuanetProcessor myCrawler = (XinhuanetProcessor) factory
				.getBean("xinhuanetProcessor");
		myCrawler.crawl();
	}
}
