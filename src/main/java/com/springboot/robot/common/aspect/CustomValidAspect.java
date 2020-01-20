package com.springboot.robot.common.aspect;

import com.springboot.robot.common.utils.ValidatorUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

/**
 * @author Connor
 * 全局的RestController的参数验证封装
 * 只要在RestController的所有方法中有使用了@RequestBody注解的参数都会被验证
 * 无需再单独写@Valid
 */
@Aspect
@Component
public class CustomValidAspect {
    @Before("@within(org.springframework.web.bind.annotation.RestController)")
    public void doBefore(JoinPoint joinPoint) {
        Object[] params = joinPoint.getArgs();
        if (params.length == 0) {
            return;
        }
        //获取方法，此处可将signature强转为MethodSignature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        //参数注解，1维是参数，2维是注解
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < annotations.length; i++) {
            Object param = params[i];
            Annotation[] paramAnn = annotations[i];
            //参数为空，直接下一个参数
            if (param == null || paramAnn.length == 0) {
                continue;
            }
            for (Annotation annotation : paramAnn) {
                //这里判断当前注解是否为RequestBody.class
                if (annotation.annotationType().equals(RequestBody.class)) {
                    //校验该参数，验证一次退出该注解
                    ValidatorUtils.validateEntity(param);
                    break;
                }
            }
        }
    }
}
