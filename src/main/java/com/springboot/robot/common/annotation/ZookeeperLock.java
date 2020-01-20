package com.springboot.robot.common.annotation;

import java.lang.annotation.*;

/**
 * @author Connor
 * Zookeeper分布式事务锁注解实现
 * lockPathPrefix 为锁的前缀，用于区分不同的锁
 * suffixIndex 为参数所在位置，拼接在前缀后面，
 * 如果目的不是让参数的tostring作为锁的一部分那么需要指定fieldName字段，
 * 会根据反射去取到fieldName这个字段的值已用于锁的后缀
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ZookeeperLock {

    /**
     * 注解的前缀 必填
     *
     * @return
     */
    String lockPathPrefix();

    /**
     * 后缀所在参数的位置 默认取第0个
     *
     * @return
     */
    int suffixIndex() default 0;

    /**
     * 字段名
     * @return
     */
    String fieldName() default "";
}
