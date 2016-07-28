package com.java.service;

import com.java.entity.Land;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;



public interface LandService extends PageProcessor {
	
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(Land record);
//
//    int insertSelective(Land record);
//
//    Land selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(Land record);
//
//    int updateByPrimaryKey(Land record);
//	  public void process(Page page);
	  public void crawl();
	  public Site getSite();
	  public void process(Page page);
}
