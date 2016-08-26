package com.java.dao.impl;

import org.springframework.stereotype.Repository;

import com.java.dao.BidDao;
import com.java.entity.Bid;


@Repository("bidDao")
public class BidDaoImpl implements BidDao{

	@Override
	public int deleteByPrimaryKey(Integer id) {
		
		return this.deleteByPrimaryKey(id);
	}

	@Override
	public int insert(Bid record) {
		
		return this.insert(record);
	}

	@Override
	public int insertSelective(Bid record) {
		
		return this.insertSelective(record);
	}

	@Override
	public Bid selectByPrimaryKey(Integer id) {
		
		return this.selectByPrimaryKey(id);
	}

	@Override
	public int updateByPrimaryKeySelective(Bid record) {
		
		return this.updateByPrimaryKey(record);
	}

	@Override
	public int updateByPrimaryKey(Bid record) {
		
		return this.updateByPrimaryKey(record);
	}
	

	
}
