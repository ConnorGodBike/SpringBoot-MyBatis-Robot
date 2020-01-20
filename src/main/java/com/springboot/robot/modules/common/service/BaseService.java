package com.springboot.robot.modules.common.service;

import com.springboot.robot.modules.common.entity.BaseBean;
import com.springboot.robot.modules.common.example.BaseExample;

import java.util.List;

/**
 * @author Connor
 * 逆向工程增强之通用Service封装
 */
public interface BaseService<T extends BaseBean, E extends BaseExample> {

    int save(T t);

    int update(T t);

    int delete(Long id);

    T queryObject(Long id);

    List<T> selectByExample(E example);
}
