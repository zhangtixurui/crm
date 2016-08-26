package com.java.service;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.processor.PageProcessor;

public interface BidService extends PageProcessor {
	public void crawl();
	  public Site getSite();
	  public void process(Page page);

}
