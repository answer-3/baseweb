package com.lizhan.core.base;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface IBaseMapper<K, T, E> {
    long countByExample(E example);

    int deleteByExample(E example);

    int deleteByPrimaryKey(K key);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExample(E example);

    T selectByPrimaryKey(K key);

    int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

    int updateByExample(@Param("record") T record, @Param("example") E example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
