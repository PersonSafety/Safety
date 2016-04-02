package com.cn.safety.utils;
/**
 * 常量
 * @author tech
 *
 */
public class Constant {

	public static final String df1= "yyyy-MM-dd HH:mm:ss";
	public static final String URL_legaldaily_seed = "http://www.legaldaily.com.cn/legal_case/node_33834.htm";//种子url
	public static final String URL_legaldaily_list = "http://www.legaldaily.com.cn\\/legal_case(.*)";//包括新闻list的url
	public static final String URL_legaldaily_content = "(http://www.legaldaily.com.cn/legal_case/content(.*))";//内容详情页，注意正则表达式要用（）括起来。

	public static final String URL_xinhuanet_seed = "http://www.xinhuanet.com/legal/index.htm";
	public static final String URL_xinhuanet_list = "(http://www.xinhuanet.com/legal/\\w+.htm)";
	public static final String URL_xinhuanet_content = "(http://news.xinhuanet.com/legal/\\d+-\\d+/(.*))";
	
	public static final String URL_people_seed = "http://legal.people.com.cn/";
	public static final String URL_people_list = "(http://legal.people.com.cn/(.*))";
	public static final String URL_people_content = "(http://legal.people.com.cn/n1/\\d+/\\d+/(.*))";
}
