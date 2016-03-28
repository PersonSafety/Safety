package com.cn.safety.service.impl;

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

}
