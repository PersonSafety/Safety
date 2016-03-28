package com.cn.safety.crawler.processor;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class LegaldailyProcessor implements PageProcessor {

	public static final String URL_LIST = "http://www.legaldaily.com.cn\\/legal_case(.*)";
	//内容详情页，注意正则表达式要用（）括起来。
	public static final String URL_Content = "(http://www.legaldaily.com.cn/legal_case/content(.*))";
    private Site site = Site.me().setRetryTimes(3).setSleepTime(100);

    @Override
    public void process(Page page) {
    	if(page.getUrl().regex(URL_Content).match()){
    		String title = page.getHtml().xpath("//td[@class='f22 b black']/text()").toString();
            page.putField("title", title);
            System.out.println("------"+title+"-------");
    	}else if(page.getUrl().regex(URL_LIST).match()){
    		page.addTargetRequests(page.getHtml().xpath("//div[@id=\"displaypagenum\"]").links().all());
            page.addTargetRequests(page.getHtml().links().regex(URL_Content).all());
    	}
        
    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) {
        Spider.create(new LegaldailyProcessor()).addUrl("http://www.legaldaily.com.cn/legal_case/node_33834.htm").thread(5).run();
    }
}