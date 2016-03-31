package com.cn.safety.crawler.processor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.cn.safety.crawler.pipeline.CrawlNewsPipeline;
import com.cn.safety.pojo.CrawlNews;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;
/**
 * 法制网
 * @author tech
 *
 */
@Service("legaldailyProcessor")
public class LegaldailyProcessor implements PageProcessor {

	@Autowired
	private CrawlNewsPipeline crawlNewsPipeline;
	public static String URL_Seed = "http://www.legaldaily.com.cn/legal_case/node_33834.htm";//种子url
	public static final String URL_LIST = "http://www.legaldaily.com.cn\\/legal_case(.*)";//包括分页的新闻列表url
	//内容详情页，注意正则表达式要用（）括起来。
	public static final String URL_Content = "(http://www.legaldaily.com.cn/legal_case/content(.*))";
	private int start = 1;//第一次进入process方法，即种子url
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
    	if(start==1){
    		URL_Seed = page.getUrl().toString();
    		start = 0;
    	}
    	switch (URL_Seed){
    	case "http://www.legaldaily.com.cn/legal_case/node_33834.htm":
    		if(page.getUrl().regex(URL_Content).match()){
    			// TODO 找出时间和图片，只抓取当天和昨天发布的新闻
        		String title = page.getHtml().xpath("//td[@class='f22 b black']/text()").toString();
        		String content = page.getHtml().xpath("//div[@id='ShowContent']/html()").toString();
        		CrawlNews news = new CrawlNews();
        		news.setUrl(page.getUrl().toString());
        		news.setTitle(title);
        		news.setContent(content);
        		news.setRegion("中国");
                page.putField("news", news);
                System.out.println("------"+title+"-------");
                System.out.println("------"+content+"-------");
        	}else if(page.getUrl().regex(URL_LIST).match()){
        		page.addTargetRequests(page.getHtml().xpath("//div[@id=\"displaypagenum\"]").links().all());
                page.addTargetRequests(page.getHtml().links().regex(URL_Content).all());
        	}
    		break;
    	case "":
    		System.out.println();
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

}














