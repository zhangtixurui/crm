package com.java.dao.impl;

import org.springframework.stereotype.Repository;

import com.java.dao.LandDao;
import com.java.entity.Land;

@Repository("landDao")
public class LandDaoImpl implements LandDao{
	
	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return this.deleteByPrimaryKey(id);
	}


	@Override
	public int insert(Land record) {

		return this.insert(record);
	}


	@Override
	public int insertSelective(Land record) {

		return this.insertSelective(record);
	}


	@Override
	public Land selectByPrimaryKey(Integer id) {

		return this.selectByPrimaryKey(id);
	}


	@Override
	public int updateByPrimaryKeySelective(Land record) {

		return this.updateByPrimaryKeySelective(record);
	}


	@Override
	public int updateByPrimaryKey(Land record) {

		return this.updateByPrimaryKey(record);
	}
	
}
