package com.java.service;

import java.util.List;
import java.util.Map;

import com.java.entity.User;

/**
 * UserService�ӿ�
 * @author Administrator
 *
 */
public interface UserService {
	
	/**
	 * �û���¼
	 * @param user
	 * @return
	 */
	public User login(User user);
	/**
	 * �û���ѯ
	 * @param map
	 * @return
	 */
	public List<User> find(Map<String, Object> map);
	
	/**
	 * ��ȡ�ܼ�¼��
	 * @param map
	 * @return
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
