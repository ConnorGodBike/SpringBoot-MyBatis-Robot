package com.springboot.robot.common.annotation;

import java.lang.annotation.*;

/**
 *
 * 创 建 人：Connor
 * 创建时间：2017/10/25 16:34
 * 说    明：系统日志注解
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

	String value() default "";
}
