package com.java.service.impl;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.java.dao.BidDao;
import com.java.entity.Bid;
import com.java.service.BidService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.pipeline.ConsolePipeline;

/**
 * @author zhangti <br>
 * 中标信息的爬取
 */
@Service("bidService")
public class BidServiceImpl2 implements BidService{
	
    private Site site = Site.me().setTimeOut(8000).setRetryTimes(10).setSleepTime(0);

    @Override
    public Site getSite() {
        return site;
    }
    
    private Page page;
    
    public static Bid bid;
   
    @Override
    public void process(Page page) {
    	bid=new Bid();    	
    	page.addTargetRequests(page.getHtml().
    			links().regex("(http://www.gxzfcg.gov.cn/view/staticpags/shengji_cjgg/[a-z0-9]+.html)").all());
//    	System.out.println(page.getRequest().getUrl());
        //第1个字段 	项目标题  title
        page.putField("title", page.getHtml().xpath("[@class='reportTitle']/h1/text()").toString());
        bid.setTitle(page.getHtml().xpath("[@class='reportTitle']/h1/text()").toString());
        if (page.getResultItems().get("title")==null){
            //skip this page
            page.setSkip(true);
        }

        String text=Jsoup.parse(page.getHtml().toString()).text();        
        System.out.println(text);
        
        //第二个字段   设置日期
        {	//第一种情况
        	Matcher matcher=Pattern.compile("(发布时间：(\\d{4}-\\d\\d-\\d\\d))").matcher(text);
        if(matcher.find()){
        	String s=matcher.group(2);
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
            try {
    			Date date=(Date)sdf.parse(s.toString());
    	        bid.setDate(date);
    	        System.out.println(s);
    		} catch (ParseException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		}
     }
        
        
        
        //规则分情况
        
        {	//第一种情况
        	Matcher matcher=Pattern.compile("(成交供应商.*：(.*?) 成交供应商地址：(.*?) 成交金额：.*([\u4e00-\u9fa5]*.*[(（][￥¥][.,\\d]*[）)]))").matcher(text);
        if(matcher.find()){
//            page.putField("BidName", matcher.group(2));
        	System.out.println("我是规则1");
            bid.setBidname(matcher.group(2));
            System.out.println(matcher.group(2)); 
            bid.setBidaddress(matcher.group(3));
            System.out.println(matcher.group(3));
            bid.setBidamount(matcher.group(4));
            System.out.println(matcher.group(4));
		}
     }
        
    {	//第二种情况
        	Matcher matcher=Pattern.compile("(成交供应商名称：(.*?) 成交供应商地址：(.*) 成  交  金  额： (.*\\（¥[,.\\d]*元\\）))").matcher(text);
        if(matcher.find()){
//            page.putField("BidName", matcher.group(2));
        	System.out.println("我是规则2");
            bid.setBidname(matcher.group(2));
            System.out.println(matcher.group(2)); 
            bid.setBidaddress(matcher.group(3));
            System.out.println(matcher.group(3));
            bid.setBidamount(matcher.group(4));
            System.out.println(matcher.group(4));
		}
     }
    
    {
    	//第三种情况
    	Matcher matcher=Pattern.compile("(1.成交供应商名称：(.*?公司) 2.成交供应商地址：(.*?) 3.成  交  金  额：([\u4e00-\u9fa5]*\\（￥[,.\\d]*元\\）))").matcher(text);
        if(matcher.find()){
//            page.putField("BidName", matcher.group(2));
        	System.out.println("我是规则3");
            bid.setBidname(matcher.group(2));
            System.out.println(matcher.group(2)); 
            bid.setBidaddress(matcher.group(3));
            System.out.println(matcher.group(3));
            bid.setBidamount(matcher.group(4));
            System.out.println(matcher.group(4));
		}
    	
    }
    {
    	//第四种情况
    	Matcher matcher=Pattern.compile("(成交人\\s*：(.*?公司) 成交人地址：(.*?) 成交金额：(￥[,.\\d]*))").matcher(text);
        if(matcher.find()){
//            page.putField("BidName", matcher.group(2));
        	System.out.println("我是规则4");
            bid.setBidname(matcher.group(2));
            System.out.println(matcher.group(2)); 
            bid.setBidaddress(matcher.group(3));
            System.out.println(matcher.group(3));
            bid.setBidamount(matcher.group(4));
            System.out.println(matcher.group(4));
		}
    	
    }
    
    {	//第五种情况
    	Matcher matcher=Pattern.compile("(成交供应商.*：(.*?) 2.成交供应商地址：(.*?) 3.成交金额：.*([\u4e00-\u9fa5]*.*[(（][￥¥][.,\\d]*[）)]))").matcher(text);
    if(matcher.find()){
//        page.putField("BidName", matcher.group(2));
    	System.out.println("我是规则5");
        bid.setBidname(matcher.group(2));
        System.out.println(matcher.group(2)); 
        bid.setBidaddress(matcher.group(3));
        System.out.println(matcher.group(3));
        bid.setBidamount(matcher.group(4));
        System.out.println(matcher.group(4));
//        bid.setUrl(page.getUrl());
	}
 }
    if(page.getRequest().getUrl()!=null){
        bid.setUrl(page.getRequest().getUrl());
        System.out.println(page.getRequest().getUrl());
    }
    
    
    
        //对数据进行持久化操作
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
//    	BidDao bidDao = applicationContext.getBean(BidDao.class);
//        if(bid.getBidaddress()!=null&&bid.getBidamount()!=null&&bid.getBidname()!=null){
//        	//插入采集的数据
//        	bidDao.insert(bid);
//        	
//        	//根据id删除数据
////        	for(int i=1;i<41;i++){
////            	bidDao.deleteByPrimaryKey(i);
////        	}       	
//        }

        
    }
   
    @Override
    public void crawl() {
    	//都加入任务,处理分页情况
    	StringBuffer urls=new StringBuffer();
//    	urls.append("http://www.gxzfcg.gov.cn/CmsNewsController/getCmsNewsList/channelCode-shengji_cjgg/param_bulletin/20/page_"+",");
    	for(int i=1;i<4;i++){
    		urls.append("http://www.gxzfcg.gov.cn/CmsNewsController/getCmsNewsList/channelCode-shengji_cjgg/param_bulletin/20/page_"+i+".html"+",");
    	}

    	String[] t=urls.toString().split(",");
    	//下次任务把http://www.mlr.gov.cn/tdsc/land/crgg/gyyd/index_1.htm....都加入任务

    	Spider.create( new BidServiceImpl2())
		    	.addUrl(t)
		        .addPipeline(new ConsolePipeline())
		        .thread(1).run();
    	
        
    }

    public static void main(String[] args) {
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    	BidDao bidDao = applicationContext.getBean(BidDao.class);
        BidService bidService = applicationContext.getBean(BidService.class);
        bidService.crawl();
    }
}
