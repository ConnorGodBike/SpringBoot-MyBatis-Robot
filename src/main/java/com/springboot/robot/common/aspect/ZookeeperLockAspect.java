package com.springboot.robot.common.aspect;

import com.springboot.robot.common.annotation.ZookeeperLock;
import com.springboot.robot.common.exception.InvalidParameterException;
import com.springboot.robot.common.exception.UnknowException;
import com.springboot.robot.common.zookeeper.CuratorClientUtils;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @author Connor
 * 分布式锁注解的实现
 */
@Aspect
@Component
public class ZookeeperLockAspect {

    @Around(value = "@annotation(com.springboot.robot.common.annotation.ZookeeperLock)")
    public Object processAuthority(ProceedingJoinPoint joinPoint) throws Throwable {
        Object[] params = joinPoint.getArgs();
        if (params.length == 0) {
            return joinPoint.proceed();
        }
        //获取方法，此处可将signature强转为MethodSignature
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ZookeeperLock zookeeperLockNo = method.getAnnotation(ZookeeperLock.class);
        Object obj = params[zookeeperLockNo.suffixIndex()];
        InterProcessMutex lock;
        // 如果没有录入字段名按照下标取就好了
        if (zookeeperLockNo.fieldName().equals("")) {
            lock = lock(zookeeperLockNo.lockPathPrefix() + "_" + obj);
        } else {
            try {
                // 如果当前类取不到取父类取
                Field field = obj.getClass().getDeclaredField(zookeeperLockNo.fieldName());
                field.setAccessible(true);
                lock = lock(zookeeperLockNo.lockPathPrefix() + "_" + field.get(obj));
            } catch (NoSuchFieldException e) {
                // 如果父类也没有 抛出异常
                Class superObj = obj.getClass().getSuperclass();
                if (superObj != null) {
                    try {
                        Field superField = superObj.getDeclaredField(zookeeperLockNo.fieldName());
                        superField.setAccessible(true);
                        lock = lock(zookeeperLockNo.lockPathPrefix() + "_" + superField.get(obj));
                    } catch (NoSuchFieldException e1) {
                        throw new InvalidParameterException("找不到字段名：" + zookeeperLockNo.fieldName());
                    }
                } else {
                    throw new InvalidParameterException("找不到字段名：" + zookeeperLockNo.fieldName());
                }
            }
        }
        try {
            return joinPoint.proceed(); //调用目标方法
        } catch (Exception e) {
            throw e;
        } finally {
            release(lock);
        }
    }

    private InterProcessMutex lock(String path) {
        CuratorFramework client = CuratorClientUtils.getInstance();
        InterProcessMutex lock = new InterProcessMutex(client, "/" + path);
        try {
            lock.acquire(3, TimeUnit.SECONDS);
            return lock;
        } catch (Exception e) {
            throw new UnknowException("服务器繁忙");
        }
    }

    private void release(InterProcessMutex lock) {
        try {
            lock.release();
        } catch (Exception e) {
            throw new UnknowException("服务器繁忙");
        }
    }
}
