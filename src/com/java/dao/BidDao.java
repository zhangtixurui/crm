package com.java.dao;

import com.java.entity.Bid;

public interface BidDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Bid record);

    int insertSelective(Bid record);

    Bid selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Bid record);

    int updateByPrimaryKey(Bid record);
}