package com.cn.safety.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.safety.dao.ICrawlNewsDao;
import com.cn.safety.pojo.CrawlNews;
import com.cn.safety.service.ICrawlNewsService;
@Service("crawlNewsService")
public class CrawlNewsServiceImpl  implements ICrawlNewsService{

	@Resource
	private ICrawlNewsDao crawlNewsDao;
	@Override
	public int insert(CrawlNews news) {
		// TODO Auto-generated method stub
		int i = crawlNewsDao.insert(news);
		return i;
	}
	@Override
	public List<Map<String, Object>> getNews(String region, Integer page) {
		List<Map<String,Object>> contacts = this.crawlNewsDao.getNews(region,page);
		return contacts;
	}

}
