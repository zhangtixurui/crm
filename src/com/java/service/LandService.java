package com.java.service;

import com.java.entity.Land;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;



public interface LandService extends PageProcessor {

	  public void crawl();
	  public Site getSite();
	  public void process(Page page);
}
