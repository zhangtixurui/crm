package com.java.test;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.java.entity.User;
//import com.alibaba.fastjson.JSON;
import com.java.service.impl.UserServiceImpl;


@RunWith(SpringJUnit4ClassRunner.class)		//表示继承了SpringJUnit4ClassRunner类
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})

public class TestMyBatis1 {
	private static Logger logger = Logger.getLogger(TestMyBatis.class);
//	private ApplicationContext ac = null;
	@Resource
	private UserServiceImpl userServiceImpl;
	public void setUserServiceImpl(UserServiceImpl userServiceImpl) {
		this.userServiceImpl = userServiceImpl;
	}

//	private User user=null;
	
	

//	@Before
//	public void before() {
//		ac = new ClassPathXmlApplicationContext("applicationContext.xml");
//		userService = (IUserService) ac.getBean("userService");
//	}

	@Test
	public void test2() {
		User user=new User();
		
		user.setEmail("479842334@qq.com");
		user.setPassword("dgae6");
		user.setPhone("6654956669");
		user.setRoleName("系统管理员");
		
		user.setUserName("zzzq");
		userServiceImpl.add(user);
		// System.out.println(user.getUserName());
		// logger.info("值："+user.getUserName());
//		logger.info(JSON.toJSONString());
	}
}

