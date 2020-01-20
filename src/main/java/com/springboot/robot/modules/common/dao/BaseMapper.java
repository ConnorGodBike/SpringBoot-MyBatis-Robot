package com.springboot.robot.modules.common.dao;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author Connor
 * 逆向工程增强之通用mapper
 */
@Repository
public interface BaseMapper<T, E> {
    int countByExample(E example);

    int deleteByExample(E example);

    int deleteByPrimaryKey(Long id);

    int insert(T record);

    int insertSelective(T record);

    List<T> selectByExample(E example);

    T selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") T record, @Param("example") E example);

    int updateByExample(@Param("record") T record, @Param("example") E example);

    int updateByPrimaryKeySelective(T record);

    int updateByPrimaryKey(T record);
}
