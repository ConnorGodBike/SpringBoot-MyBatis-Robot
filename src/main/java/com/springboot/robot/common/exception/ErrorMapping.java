package com.springboot.robot.common.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MissingServletRequestParameterException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Connor
 * 自定义异常集合
 */
public class ErrorMapping {
    private static Map<Class, KV> HOLDER = new HashMap<Class, KV>();

    private static class KV {
        public Integer code;
        public String message;

        KV(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    static {
        //客户端异常
        HOLDER.put(InvalidPasswordException.class, new KV(4000, "密码不合法"));
        HOLDER.put(MobileNotExistsException.class, new KV(4001, "手机号码不存在"));
        HOLDER.put(RedundantUserException.class, new KV(4002, "用户冗余"));
        HOLDER.put(UserAlreadyExistsException.class, new KV(4003, "用户已存在"));
        HOLDER.put(ConstraintViolationException.class, new KV(4004, "客户端输入参数异常"));

        HOLDER.put(InvalidFormatException.class, new KV(4005, "格式不正确"));
        HOLDER.put(HttpMessageNotReadableException.class, new KV(4006, "HTTP消息异常"));
        HOLDER.put(MissingServletRequestParameterException.class, new KV(4007, "Servlet请求参数缺失"));
        HOLDER.put(ResourceNotExistsException.class, new KV(4008, "资源不存在"));

        HOLDER.put(RepetitiveOperationException.class, new KV(4009, "重复操作异常"));
        HOLDER.put(InvalidParameterException.class, new KV(4010, "参数异常"));

        HOLDER.put(UnauthorizedException.class, new KV(4011, "未授权"));

        HOLDER.put(RelevanceResourceException.class, new KV(4012, "有关联资源未删除"));
        HOLDER.put(ResourceStatusException.class, new KV(4013, "资源状态异常"));

        HOLDER.put(StorageException.class, new KV(4013, "文件保存异常"));
        HOLDER.put(StorageFileNotFoundException.class, new KV(4013, "存储文件未发现"));

        HOLDER.put(MobileNumberException.class, new KV(4014, "手机号码异常"));
        HOLDER.put(SMSSendException.class, new KV(4015, "短信发送异常"));
        HOLDER.put(SaveResourceException.class, new KV(4016, "保存资源失败"));

        HOLDER.put(ResourceInsufficientException.class, new KV(4017, "资源不足"));

        HOLDER.put(ResourceTypeException.class, new KV(4018, "资源类型不合法"));


        //服务器异常
        HOLDER.put(UserRegisterException.class, new KV(5000, "用户注册异常"));
        HOLDER.put(ServiceException.class, new KV(5001, "服务器内部异常"));
        HOLDER.put(SystemException.class, new KV(5002, "系统异常"));

        //未知异常 9999
        HOLDER.put(UnknowException.class, new KV(9999, "未知异常"));

    }

    public static Integer getCode(Class<? extends SystemException> clazz) {
        return HOLDER.get(clazz).code;
    }

    public static Integer getConstraintCode(Class<? extends ValidationException> clazz) {
        return HOLDER.get(clazz).code;
    }

    public static String getDefaultMessage(Class<? extends SystemException> clazz) {
        return HOLDER.get(clazz).message;
    }
}
