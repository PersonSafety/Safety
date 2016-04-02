package com.cn.safety.crawler.processor;

import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.http.client.utils.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cn.safety.crawler.pipeline.CrawlNewsPipeline;
import com.cn.safety.pojo.CrawlNews;
import com.cn.safety.utils.Constant;
import com.cn.safety.utils.CrawlUtils;
import com.cn.safety.utils.DateTimeUtils;
import com.cn.safety.utils.HttpClientUtils;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
/**
 * 法制网
 * @author tech
 *
 */
public class LegaldailyProcessor implements PageProcessor {

	private static Logger logger = LoggerFactory
			.getLogger(LegaldailyProcessor.class); // 日志记录
	private String URL_Seed = "";//种子url
	private int start = 1;//第一次进入process方法，即种子url
	private static int shouye = 1;//保证只抓取首页的内容
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
    	if(start==1){
    		URL_Seed = page.getUrl().toString();
    		start = 0;
    	}
    	switch (URL_Seed){
    	case Constant.URL_legaldaily_seed:
    		legaldaily(page);
    		break;
    	case Constant.URL_xinhuanet_seed:
    		xinhuaNet(page);
    		break;
    	case Constant.URL_people_seed:
    		people(page);
    		break;
    	}
        if(page.getResultItems().get("news") == null){
        	 //设置skip之后，这个页面的结果不会被Pipeline处理
             page.setSkip(true);
        }
    }

    @Override
    public Site getSite() {
        return site;
    }
    /*
     * 法制网legaldaily.com
     */
    private static void legaldaily(Page page){
    	try{
    		if(page.getUrl().regex(Constant.URL_legaldaily_content).match()){
    			// 只抓取当天和昨天发布的新闻
        		String title = page.getHtml().xpath("//td[@class='f22 b black']/text()").toString();
        		String content = page.getHtml().xpath("//div[@id='ShowContent']/html()").toString();
        		String time = page.getHtml().regex("(\\d{4}-\\d{2}-\\d{2}\\s\\d{2}:\\d{2}:\\d{2})").all().get(0);
        		String[] format={"yyyy-MM-dd HH:mm:ss"};
        		Date date = DateUtils.parseDate(time,format);
        		int d = DateTimeUtils.daysOfTwo(date,new Date());
        		if(d<=1){
        			CrawlNews news = new CrawlNews();
            		news.setUrl(page.getUrl().toString());
            		news.setTitle(title);
            		news.setContent(content);
            		news.setPublishTime(time);
            		news.setRegion("中国");
                    page.putField("news", news);
        		}
        	}else if(page.getUrl().regex(Constant.URL_legaldaily_list).match()){
        		if(shouye==1){
        			page.addTargetRequests(page.getHtml().links().regex(Constant.URL_legaldaily_content).all());        			
        			shouye = 0;
        		}
        	}
    	}catch (Exception e){
    		logger.info("抓取出错----------"+page.getUrl().toString());
    	}
    	
    }
    /*
     * 新华网xinhuaNet
     */
    private static void xinhuaNet(Page page){
    	try{
    		if(page.getUrl().regex(Constant.URL_xinhuanet_content).match()){
        		String pre = page.getUrl().toString().substring(0, 43);
        		String title = page.getHtml().xpath("//h1[@id='title']/text()").toString();
        		String content = page.getHtml().xpath("//div[@class='article']/html()").toString();
        		List<String> imgs = page.getHtml().xpath("//div[@class='article']//img/@src").all();
        		String img = null;
        		if(!CollectionUtils.isEmpty(imgs)){
        			img = imgs.get(0);
        			img = pre + img;
        		}
        		//String img2 = page.getHtml().$("div.article img","src").all().get(0);
        		String time = page.getHtml().regex("(\\d{4}年\\d{2}月\\d{2}日\\s\\d{2}:\\d{2}:\\d{2})").all().get(0);
        		String[] format={"yyyy年MM月dd日 HH:mm:ss"};
        		Date date = DateUtils.parseDate(time,format);
        		int d = DateTimeUtils.daysOfTwo(date,new Date());
        		if(d<=1 && title != null){
        			CrawlNews news = new CrawlNews();
            		news.setUrl(page.getUrl().toString());
            		news.setTitle(title);
            		news.setContent(content);
            		news.setPublishTime(time);
            		news.setAvatar(img);
            		news.setRegion("中国");
                    page.putField("news", news);
        		}
        	}else if(page.getUrl().regex(Constant.URL_xinhuanet_list).match()){
        		if(shouye==1){
        			page.addTargetRequests(page.getHtml().links().regex(Constant.URL_xinhuanet_content).all());        			
        			shouye = 0;
        		}
        	}
    	}catch (Exception e){
    		logger.info("抓取出错----------"+page.getUrl().toString());
    	}
    }
    /*
     * http://legal.people.com.cn/
     */
    private static void people(Page page){
    	try{
    		if(page.getUrl().regex(Constant.URL_people_content).match()){
        		String pre = page.getUrl().toString().substring(0, 43);
        		String title = page.getHtml().xpath("//h1[@id='p_title']/text()").toString();
        		String content = page.getHtml().xpath("//div[@id='p_content']/html()").toString();
        		List<String> imgs = page.getHtml().xpath("//div[@id='p_content']//img/@src").all();
        		String img = null;
        		if(!CollectionUtils.isEmpty(imgs)){
        			img = imgs.get(0);
        			img = pre + img;
        		}
        		//String img2 = page.getHtml().$("div.article img","src").all().get(0);
        		String time = page.getHtml().regex("(\\d{4}年\\d{2}月\\d{2}日\\d{2}:\\d{2})").all().get(0);
        		String[] format={"yyyy年MM月dd日HH:mm"};
        		Date date = DateUtils.parseDate(time,format);
        		int d = DateTimeUtils.daysOfTwo(date,new Date());
        		if(d<=1 && title != null && CrawlUtils.checkKeywords(title)){
        			CrawlNews news = new CrawlNews();
            		news.setUrl(page.getUrl().toString());
            		news.setTitle(title);
            		news.setContent(content);
            		news.setPublishTime(time);
            		news.setAvatar(img);
            		news.setRegion("中国");
                    page.putField("news", news);
        		}
        	}else if(page.getUrl().regex(Constant.URL_people_list).match()){
        		// 为保证新闻的时效性，其实只抓取首页的新闻就行了，即最新的新闻
        		if(shouye==1){
        			page.addTargetRequests(page.getHtml().links().regex(Constant.URL_people_content).all());        			
        			shouye = 0;
        		}
        	}
    	}catch (Exception e){
    		logger.info("抓取出错----------"+page.getUrl().toString());
    		e.printStackTrace();
    	}
    }
    
    
}














