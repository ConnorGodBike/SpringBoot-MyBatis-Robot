package com.springboot.robot.modules.common.service.impl;

import com.springboot.robot.common.utils.RedisUtils;
import com.springboot.robot.modules.common.dao.BaseMapper;
import com.springboot.robot.modules.common.entity.BaseBean;
import com.springboot.robot.modules.common.example.BaseExample;
import com.springboot.robot.modules.common.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author Connor
 * 逆向工程增强之通用Service实现封装
 */
@Service("baseServiceImpl")
public abstract class BaseServiceImpl<T extends BaseBean, E extends BaseExample> implements BaseService<T, E> {

    @Autowired
    protected BaseMapper<T, E> baseMapper;

    /**
     * 常用组件之redis
     */
    @Autowired
    protected RedisUtils redisUtils;

    @Override
    public int save(T t) {
        t.setCreateTime(new Date());
        t.setUpdateTime(new Date());
        return baseMapper.insertSelective(t);
    }

    @Override
    public int update(T t) {
        t.setUpdateTime(new Date());
        return baseMapper.updateByPrimaryKeySelective(t);
    }

    @Override
    public int delete(Long id) {
        T t = queryObject(id);
        if (t == null) {
            return 0;
        }
        t.setDeleteFlag(true);
        t.setUpdateTime(new Date());
        return update(t);
    }

    @Override
    public T queryObject(Long id) {
        return baseMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<T> selectByExample(E example) {
        E.BaseCriteria criteria = example.getBaseOredCriteria().get(0);
        if (criteria == null) {
            criteria = example.createCriteria();
        }
        criteria.andCreateTimeBetween(new Date(), new Date())
                .andDeleteFlagEqualTo(false)
                .andDeleteFlagEqualTo(false)
                .andDeleteFlagEqualTo(false)
                .andDeleteFlagEqualTo(false)
                .andDeleteFlagEqualTo(false);
        return baseMapper.selectByExample(example);
    }
}
