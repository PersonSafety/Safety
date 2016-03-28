package com.cn.safety.dao;

import com.cn.safety.pojo.CrawlNews;

/**
 * 抓取到的新闻
 * @author Tech
 *
 */
public interface ICrawlNewsDao {

	int insert(CrawlNews record);
}
