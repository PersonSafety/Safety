package com.cn.safety.crawler;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import us.codecraft.webmagic.Spider;

import com.cn.safety.crawler.pipeline.CrawlNewsPipeline;
import com.cn.safety.crawler.processor.NewsProcessor;
import com.cn.safety.crawler.processor.XinhuanetProcessor;
import com.cn.safety.dao.ICrawlNewsDao;
import com.cn.safety.service.IUserService;
import com.cn.safety.service.impl.UserServiceImpl;

@Service
public class NewsCrawler {

	@Resource
	private CrawlNewsPipeline crawlNewsPipeline;

	@Resource
	private ICrawlNewsDao crawlNewsDao;
	/*
	 * 抓取的入口方法
	 */
	//@Scheduled(cron = "0 51 13 * * ?")
	public void crawl() {
		System.out.println("开始抓取。。。");
		// 一定要new一个processor，因为spring注入是单例
		List<String> list = crawlNewsDao.selectSeedUrls();
		for(String url : list){
			Spider.create(new NewsProcessor())
			.addUrl(url)
			.addPipeline(crawlNewsPipeline)
			.thread(5)
			.run();
			System.out.println("-------------------------------"
					+ "---------------------------------------------"
					+ "-------------------------------"
					+ "------------------------------"
					+ "----------------------------------------------");
		}
	}

	public static void main(String[] args) {
		/*
		@SuppressWarnings("resource")
		ApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"classpath:spring-mvc.xml");
		BeanFactory factory = (BeanFactory) applicationContext;
		final XinhuanetProcessor myCrawler = (XinhuanetProcessor) factory
				.getBean("xinhuanetProcessor");
		myCrawler.crawl();
		*/
		NewsCrawler c = new NewsCrawler();
		c.crawl();
	}
}
