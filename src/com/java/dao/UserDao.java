package com.java.dao;

import java.util.List;
import java.util.Map;

import com.java.entity.User;

/**
 * 用户DAO接口
 * 
 * @author Administrator
 * 
 */
public interface UserDao {
	
	/**
	 * 用户登录
	 * @param user
	 * @return
	 */
	public User login(User user);
	
	/**
	 * 用户查询
	 * @param map
	 * @return用户集合
	 */
	public List<User> find(Map<String, Object> map);
	
	/**
	 * 获取总记录数
	 * @param map
	 * @return获取的total数
	 */
	public Long getTotal(Map<String, Object> map);
	
	/**
	 * 修改用户
	 * @param user
	 * @return影响的记录数
	 */
	public int update(User user);
	
	/**
	 * 添加用户
	 * @param user
	 * @return影响的记录数
	 */
	public int add(User user);
	
	/**
	 * 删除用户
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	
}
