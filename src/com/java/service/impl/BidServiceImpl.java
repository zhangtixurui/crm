package com.java.service.impl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.java.dao.BidDao;
import com.java.dao.LandDao;
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
//@Service("bidService")
public class BidServiceImpl implements BidService{
	
    private Site site = Site.me().setRetryTimes(5).setSleepTime(0);

    @Override
    public Site getSite() {
        return site;
    }
    
    private Page page;
    
    public static Bid bid;
   
    @Override
    public void process(Page page) {
    	bid=new Bid();
    	
    	page.addTargetRequests(page.getHtml().links().regex("(http://www.gpcgd.com/gpcgd/portal/portal-news!detailNews?portalNews.id=\\d{5})").all());
        //第1个字段 	项目标题1  ItemTitleFirst
        page.putField("title", page.getHtml().xpath("[@class='pub_title']/text()").toString());
        bid.setTitle(page.getHtml().xpath("[@class='pub_title']/text()").toString());

        if (page.getResultItems().get("title")==null){
            //skip this page
            page.setSkip(true);
        }
//        bid.setUrl(page.getUrl());

        String text=Jsoup.parse(page.getHtml().toString()).text();
        
//        System.out.println(text);
        //第2个字段    中标金额
        Matcher matcher=Pattern.compile("(采购项目预算金额（元）：([0-9,]*))").matcher(text);
        if(matcher.find()){
            page.putField("parcelNumber", matcher.group(2));
            bid.setBidamount(matcher.group(2));
            System.out.println(matcher.group(2));  
		}
        System.out.println(page.getHtml().xpath("//div[@id=generalArticleEditForm00080101_bidOrgDetailTDP]/p/u/text()").toString());
        System.out.println(page.getHtml().xpath("//div[@id='generalArticleEditForm00080101_bidOrgDetailTDP']/p/u/text()").toString());
        System.out.println(page.getHtml().xpath("//div[@id='generalArticleEditForm00080101_bidOrgDetailTDP']/p/u/text()").toString());

        String t=page.getHtml().xpath("//div[@id='generalArticleEditForm00080101_bidOrgDetailTDP']/p/u/text()").toString();
//        bid.setBidname(page.getHtml().xpath("//div[@id='generalArticleEditForm00080101_bidOrgDetailTDP']/p/u/text()").toString());
//        bid.setBidaddress(page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
//      page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
      
//      page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));               
        //对数据进行持久化操作
//        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
//    	BidDao bidDao = applicationContext.getBean(BidDao.class);
//        if(bid.getTitle()!=null){
//        	bidDao.insert(bid);
//        }

    }
   
    @Override
    public void crawl() {
    	//都加入任务,处理分页情况
    	StringBuffer urls=new StringBuffer();
//    	urls.append("http://www.mlr.gov.cn/tdsc/land/crgg/gyyd/"+",");
    	for(int i=1;i<4;i++){
    		urls.append("http://www.gpcgd.com/gpcgd/portal/portal-news!listAll?searchType.keywords=&searchType.numberor=&searchType.classify=40012&searchType.newlyDay=5&pageNum="+i+",");
    	}

    	String[] t=urls.toString().split(",");
    	//下次任务把http://www.mlr.gov.cn/tdsc/land/crgg/gyyd/index_1.htm....都加入任务

    	Spider.create( new BidServiceImpl())
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
