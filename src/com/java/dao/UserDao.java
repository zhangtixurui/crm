package com.java.dao;

import java.util.List;
import java.util.Map;

import com.java.entity.User;

/**
 * �û�DAO�ӿ�
 * 
 * @author Administrator
 * 
 */
public interface UserDao {
	
	/**
	 * �û���¼
	 * @param user
	 * @return
	 */
	public User login(User user);
	
	/**
	 * �û���ѯ
	 * @param map
	 * @return�û�����
	 */
	public List<User> find(Map<String, Object> map);
	
	/**
	 * ��ȡ�ܼ�¼��
	 * @param map
	 * @return��ȡ��total��
	 */
	public Long getTotal(Map<String, Object> map);
	
	/**
	 * �޸��û�
	 * @param user
	 * @returnӰ��ļ�¼��
	 */
	public int update(User user);
	
	/**
	 * ����û�
	 * @param user
	 * @returnӰ��ļ�¼��
	 */
	public int add(User user);
	
	/**
	 * ɾ���û�
	 * @param id
	 * @return
	 */
	public int delete(Integer id);
	
	
}
