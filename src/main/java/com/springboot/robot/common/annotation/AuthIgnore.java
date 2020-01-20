package com.springboot.robot.common.annotation;

import org.springframework.core.annotation.Order;

import java.lang.annotation.*;

/**
 * api接口，忽略Token验证 当接口不需要验证token时使用该注解
 * 如果是sys模块，还需要在ShiroConfig中配置不验证（因为如果直接把sys的实现改的和其他模块一样，到时候权限验证的注解需要自己实现）
 * @author Connor
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Order(2)
public @interface AuthIgnore {

}
