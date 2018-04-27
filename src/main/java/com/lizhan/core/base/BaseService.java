package com.lizhan.core.base;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BaseService<K, T, E> implements IBaseService<K, T, E> {
    @Autowired
    IBaseMapper<K, T, E> baseMapper;

    @Override
    public long countByExample(E example) {
        return baseMapper.countByExample(example);
    }

    @Override
    public int deleteByExample(E example) {
        return baseMapper.deleteByExample(example);
    }

    @Override
    public int deleteByPrimaryKey(K key) {
        return baseMapper.deleteByPrimaryKey(key);
    }

    @Override
    public int insert(T record) {
        return baseMapper.insert(record);
    }

    @Override
    public int insertSelective(T record) {
        return baseMapper.insertSelective(record);
    }

    @Override
    public List<T> selectByExample(E example) {
        return baseMapper.selectByExample(example);
    }

    @Override
    public T selectByPrimaryKey(K key) {
        return baseMapper.selectByPrimaryKey(key);
    }

    @Override
    public int updateByExampleSelective(T record, E example) {
        return baseMapper.updateByExampleSelective(record, example);
    }

    @Override
    public int updateByExample(T record, E example) {
        return baseMapper.updateByExample(record, example);
    }

    @Override
    public int updateByPrimaryKeySelective(T record) {
        return baseMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(T record) {
        return baseMapper.updateByPrimaryKey(record);
    }
}
