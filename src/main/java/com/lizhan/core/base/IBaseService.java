package com.lizhan.core.base;

import java.util.List;

public interface IBaseService<K, T, E> {

    long countByExample(E example);

    int deleteByExample(E example);

    int deleteByPrimaryKey(K key);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExample(E example);

    T selectByPrimaryKey(K key);

    int updateByExampleSelective(T record, E example);

    int updateByExample(T record, E example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);

}
