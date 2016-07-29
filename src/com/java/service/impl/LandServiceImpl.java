package com.java.service.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.annotation.Resource;

import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.java.dao.LandDao;
import com.java.dao.impl.LandDaoImpl;
import com.java.entity.Land;
import com.java.service.LandService;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.pipeline.ConsolePipeline;







/**
 * @author code4crafter@gmail.com <br>
 * @since 0.3.2
 */


@Service("landService")
public class LandServiceImpl implements LandService{
	

	@Resource
	private LandDao landDao;

	@Resource
	private LandService landService;

    private Site site = Site.me().setRetryTimes(3).setSleepTime(0);
    @Override
    public Site getSite() {
        return site;
    }
    
    public static Land land;


    
    @Override
    public void process(Page page) {
    	land=new Land();
     page.addTargetRequests(page.getHtml().links().regex("(http://www\\.mlr\\.gov\\.cn/tdsc/land/crgg/gyyd/.*\\.htm)").all());
        //第1个字段 	项目标题1  ItemTitleFirst
        page.putField("itemTitleFirst", page.getHtml().xpath("[@class='zw_title']/text()").toString());
        land.setItemtitlefirst(page.getHtml().xpath("[@class='zw_title']/text()").toString());
        if (page.getResultItems().get("itemTitleFirst")==null){
            //skip this page
            page.setSkip(true);
        }

        String text=Jsoup.parse(page.getHtml().toString()).text();
        
//        System.out.println(text);
        //第2个字段    
        Matcher matcher=Pattern.compile("(宗地编号：  ([\\(\\)\\.A-Z—\\d-\u4e00-\u9fa5]*) 宗地总面积)").matcher(text);
        if(matcher.find()){
            page.putField("parcelNumber", matcher.group(2));
            land.setParcelnumber(matcher.group(2));
            System.out.println(page.getHtml().xpath("[@class='zw_title']/text()").toString());
            System.out.println(matcher.group(2));  
		}


        //第3个字段 	宗地总面积（平方米）	TotalParcelArea
        Matcher matcher1=Pattern.compile("(宗地总面积：  ([\\d.]*平方米) 宗地坐落)").matcher(text);
        if(matcher1.find()){
            page.putField("totalParcelArea", matcher1.group(2));
            land.setTotalparcelarea(matcher1.group(2));
		}
      //第4个字段 	宗地坐落/土地座落	LandLocated
        Matcher matcher2=Pattern.compile("(宗2地坐落：  ([\u4e00-\u9fa5]*) 出让年限)").matcher(text);
        if(matcher2.find()){
            page.putField("landLocated", matcher2.group(2));
            land.setLandlocated(matcher2.group(2));
		}
        //土地年限（转让年限/出让年限）	LandLife
        Matcher matcher3=Pattern.compile("(出让年限：  (\\d*年) 容积率)").matcher(text);
        if(matcher3.find()){
            page.putField("landLife", matcher3.group(2));
            land.setLandlife(matcher3.group(2));
		}
        //容积率		VolumeRate
        Matcher matcher4=Pattern.compile("(容积率：  ([\\d.\u4e00-\u9fa5]*) 建筑密度)").matcher(text);
        if(matcher4.find()){
            page.putField("volumeRate", matcher4.group(2));
            land.setVolumerate(matcher4.group(2));
		}
        //建筑密度		BuildingDensity
        Matcher matcher5=Pattern.compile("(建筑密度.*： ([\\d\u4e00-\u9fa5]*)  绿化率)").matcher(text);
        if(matcher5.find()){
            page.putField("buildingDensity", matcher5.group(2));
            land.setBuildingdensity(matcher5.group(2));
		}
        //土地用途明细	LandUseDetails
        Matcher matcher6=Pattern.compile("(土地用途明细： ([\u4e00-\u9fa5]*) 投资强度)").matcher(text);
        if(matcher6.find()){
            page.putField("landUseDetails", matcher6.group(2));
            land.setLandusedetails(matcher6.group(2));
		}
      //投资强度		InvestmentIntensity
        Matcher matcher7=Pattern.compile("(投资强度：  (万元/公顷) 保证金)").matcher(text);
        if(matcher7.find()){
            page.putField("investmentIntensity", matcher7.group(2));
            land.setInvestmentintensity(matcher7.group(2));
		}
        //保证金		SecurityDeposit
        Matcher matcher8=Pattern.compile("(保证金：  ([.\\d]*万元) 估价报告备案号)").matcher(text);
        if(matcher8.find()){
            page.putField("securityDeposit", matcher8.group(2));
            land.setSecuritydeposit(matcher8.group(2));
		}
        //估价报告备案号		ValuationReportRecordNumber
        Matcher matcher9=Pattern.compile("(估价报告备案号([\\dA-Z]*) 起始价)").matcher(text);
        if(matcher9.find()){
            page.putField("valuationReportRecordNumber", matcher9.group(2));
            land.setValuationreportrecordnumber(matcher9.group(2));
		}
        //起始价		StartingPrice
        Matcher matcher10=Pattern.compile("(起始价：  ([\\d]*万元) 加价幅度)").matcher(text);
        if(matcher10.find()){
            page.putField("startingPrice", matcher10.group(2));
            land.setStartingprice(matcher10.group(2));
		}
        //加价幅度		BidIncrement
        Matcher matcher11=Pattern.compile("(加价幅度：  ([\\d]*万元) 挂牌开始时间)").matcher(text);
        if(matcher11.find()){
            page.putField("bidIncrement", matcher11.group(2));
            land.setBidincrement(matcher11.group(2));
		}
        //挂牌开始时间	ListedStartTime 挂牌截止时间	DeadlineListing
        Matcher matcher12=Pattern.compile("(挂牌开始时间：  (\\d{4}年\\d\\d月\\d\\d日\\d\\d时\\d\\d分) 挂牌截止时间：  (\\d{4}年\\d\\d月\\d\\d日\\d\\d时\\d\\d分))").matcher(text);
        if(matcher12.find()){
            page.putField("listedStartTime", matcher12.group(2));
            land.setListedstarttime(matcher12.group(2));
            page.putField("deadlineListing", matcher12.group(3));
            land.setDeadlinelisting(matcher12.group(3));
		}
        //联系电话		Phone  联系人		Contacts
        Matcher matcher13=Pattern.compile("(联 系 人：([、\u4e00-\u9fa5]*)     联系电话：(\\d{4}-\\d{7}))").matcher(text);
        if(matcher13.find()){
            page.putField("contacts", matcher13.group(2));
            land.setPhone(matcher13.group(2));
            page.putField("phone", matcher13.group(3));
            land.setContacts(matcher13.group(3));
		}
//        if(land!=null){
//        	page.putField("land", land);
//        }
        
//        ResultItems resultItems=page.getResultItems();
//        for (Map.Entry<String, Object> entry : resultItems.getAll().entrySet()) {
//            System.out.println(entry.getKey() + ":\t" + entry.getValue());
//        }
        
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    	LandDao landDao = applicationContext.getBean(LandDao.class);
        if(land!=null){
        	landDao.insert(land);
        }
//        this.in();
      //把数据插入数据库


//        page.putField("name", page.getHtml().xpath("//h1[@class='entry-title public']/strong/a/text()").toString());
        
//        page.putField("readme", page.getHtml().xpath("//div[@id='readme']/tidyText()"));
    }
   
    @Override
    public void crawl() {
    	Spider.create( new LandServiceImpl())
		    	.addUrl("http://www.mlr.gov.cn/tdsc/land/crgg/gyyd/")
		        .addPipeline(new ConsolePipeline())
		        .thread(1).run();
        
    }

    public static void main(String[] args) {
    	ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:/applicationContext*.xml");
    	LandDao landDao = applicationContext.getBean(LandDao.class);
        LandService landService = applicationContext.getBean(LandService.class);
        landService.crawl();
    }
}
