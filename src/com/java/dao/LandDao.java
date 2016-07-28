package com.java.dao;

import org.springframework.stereotype.Repository;

import com.java.entity.Land;


public interface LandDao {
    int deleteByPrimaryKey(Integer id);

    int insert(Land record);

    int insertSelective(Land record);

    Land selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Land record);

    int updateByPrimaryKey(Land record);
}