package com.springboot.robot.common.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.springboot.robot.common.utils.IPUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;


/**
 * @author Connor
 * 系统日志打印，切面处理类
 */
@Aspect
@Component
public class WebLogAspect {

    private static String LoggerNameInput = "客户端请求 >>>>>>>>>>>>>>>>>>>>>>>>>>";
    private static String LoggerNameOutput = "服务器响应 <<<<<<<<<<<<<<<<<<<<<<<<<<";
    private static final String PRINT_PREFIX_INPUT = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【INPUT】>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
    private static final String PRINT_PREFIX_OUTPUT = "<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<【OUTPUT】<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";

    @Pointcut("@annotation(com.springboot.robot.common.annotation.WebLog)")
    public void logPointCut() {

    }

    @Before("logPointCut()")
    public void before(JoinPoint point) throws Throwable {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = attributes.getRequest();

        ObjectMapper mapper = new ObjectMapper();
        LoggerFactory.getLogger(LoggerNameInput).info(PRINT_PREFIX_INPUT);
        LoggerFactory.getLogger(LoggerNameInput).info("【URL】" + request.getRequestURL() + "【END】");
        LoggerFactory.getLogger(LoggerNameInput).info("【HTTP_HEADERS】" + getHeaderMsg(request.getHeaderNames()) + "【END】");
        LoggerFactory.getLogger(LoggerNameInput).info("【HTTP_TOKEN】" + request.getHeader("token") + "【END】");
        LoggerFactory.getLogger(LoggerNameInput).info("【HTTP_METHOD】" + request.getMethod() + "【END】");
        LoggerFactory.getLogger(LoggerNameInput).info("【HTTP_COOKIES】" + ((request.getCookies() == null) ? "null" : mapper.writeValueAsString(request.getCookies())) + "【END】");
        LoggerFactory.getLogger(LoggerNameInput).info("【HTTP_AUTH_TYPE】" + request.getAuthType() + "【END】");
        LoggerFactory.getLogger(LoggerNameInput).info("【HTTP_PATH_INFO】" + request.getPathInfo() + "【END】");
        LoggerFactory.getLogger(LoggerNameInput).info("【HTTP_CONTENT_TYPE】" + request.getContentType() + "【END】");
        LoggerFactory.getLogger(LoggerNameInput).info("【IP】" + IPUtils.getIpAddr(request) + "【END】");
        LoggerFactory.getLogger(LoggerNameInput).info("【CLASS_METHOD】" + point.getSignature().getDeclaringTypeName() + "-" + point.getSignature().getName() + "【END】");
        Object[] args = point.getArgs();
        if (args != null && args.length > 0) {
            if (args[0] instanceof HttpServletRequest) {
                LoggerFactory.getLogger(LoggerNameInput).info("【ARGS】HttpServletRequest【END】");
            } else {
                LoggerFactory.getLogger(LoggerNameInput).info("【ARGS】" + new Gson().toJson(args[0]) + "【END】");
            }
        } else {
            LoggerFactory.getLogger(LoggerNameInput).info("【ARGS】NO ARGS【END】");
        }
//        String regex="[A-Fa-f0-9]{32}";
//        if (!token.matches(regex) && token != null && !token.equals("")) {
//            throw new InvalidParameterException("请勿尝试日志伪造，你的信息已经被记录");
//        }
        LoggerFactory.getLogger(LoggerNameInput).info(PRINT_PREFIX_INPUT);
    }

    @AfterReturning(returning = "ret", pointcut = "logPointCut()")
    public void after(Object ret) throws Throwable {
        LoggerFactory.getLogger(LoggerNameOutput).info(PRINT_PREFIX_OUTPUT);
        if (ret != null) {
            ObjectMapper mapper = new ObjectMapper();
            LoggerFactory.getLogger(LoggerNameOutput).info("【RESPONSE】" + mapper.writeValueAsString(ret));
        } else {
            LoggerFactory.getLogger(LoggerNameOutput).info("【RESPONSE】" + ret);
        }
        LoggerFactory.getLogger(LoggerNameOutput).info(PRINT_PREFIX_OUTPUT);
    }

    private String getHeaderMsg(Enumeration<String> headers) {
        if (headers == null) {
            return null;
        }
        List<String> headerList = new ArrayList<>();
        while (headers.hasMoreElements()) {
            headerList.add(headers.nextElement());
        }
        String[] ss = new String[headerList.size()];
        ss = headerList.toArray(ss);
        return Arrays.toString(ss);
    }
}
