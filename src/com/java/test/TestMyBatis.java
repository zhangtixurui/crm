package com.java.test;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.java.dao.LandDao;
import com.java.entity.Land;
import com.java.service.LandService;
//import com.alibaba.fastjson.JSON;
import com.java.service.impl.LandServiceImpl;

import us.codecraft.webmagic.ResultItems;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.pipeline.Pipeline;


@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})

public class TestMyBatis  {
	private static Logger logger = Logger.getLogger(TestMyBatis.class);
//	private ApplicationContext ac = null;
	@Resource
	private LandService landService;
//	@Resource
//	private LandDao landDao;

//	@Before
//	public void before() {
//		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		userService = (IUserService) ac.getBean("userService");
//	}

	@Test
	public void test1() {
		landService.crawl();
		// System.out.println(user.getUserName());
		// logger.info("值："+user.getUserName());
//		logger.info(JSON.toJSONString());
	}
	
//	@Override
//	public void process(ResultItems resultItems, Task task) {
//
//		Land land = (Land)resultItems.get("land");
//		if(land!=null){
//			landDao.insert(land);
//		}
//		
//	}
}

