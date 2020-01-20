package com.springboot.robot.common.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.LoggerFactory;

/**
 * 创 建 人：Connor
 * 创建时间：2017/10/25 16:51
 * 说    明：
 */
public class LogUtil {
    public static final String PRINT_TYPE_INPUT = "INPUT";
    public static final String PRINT_TYPE_OUTPUT = "OUTPUT";

//    private static final String PRINT_PREFIX_INPUT = ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>【INPUT】>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>";
//    private static final String PRINT_PREFIX_OUTPUT ="<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<【OUTPUT】<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<";
//    private static final String PRINT_PREFIX_REQUEST_URL ="=================================================【访问接口】==========================================================";


    private static final String PRINT_PREFIX_INPUT = ">>>>【INPUT】>>>>";
    private static final String PRINT_PREFIX_OUTPUT ="<<<<【OUTPUT】<<<<";
    private static final String PRINT_PREFIX_REQUEST_URL ="====【访问接口】====";

    public static void printParameters(Class c , Object object, String type) {
        String prefix = "";
        if (type.equals(PRINT_TYPE_INPUT)) {
            prefix = PRINT_PREFIX_INPUT;
        }
        if (type.equals(PRINT_TYPE_OUTPUT)) {
            prefix = PRINT_PREFIX_OUTPUT;
        }
        if (object == null) {
//            String url = HttpContextUtils.getHttpServletRequest().getRequestURL().toString();
//            LoggerFactory.getLogger(c).info(PRINT_PREFIX_REQUEST_URL);
//            LoggerFactory.getLogger(c).info("  " + url + "  ");
//            LoggerFactory.getLogger(c).info(PRINT_PREFIX_REQUEST_URL);
            LoggerFactory.getLogger(c).info(prefix);
            LoggerFactory.getLogger(c).info(object+"");
            LoggerFactory.getLogger(c).info(prefix);
            return;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
//            String url = HttpContextUtils.getHttpServletRequest().getRequestURL().toString();
//            LoggerFactory.getLogger(c).info(PRINT_PREFIX_REQUEST_URL);
//            LoggerFactory.getLogger(c).info("  " + url + "  ");
//            LoggerFactory.getLogger(c).info(PRINT_PREFIX_REQUEST_URL);
            LoggerFactory.getLogger(c).info(prefix);
            LoggerFactory.getLogger(c).info(mapper.writeValueAsString(object));
            LoggerFactory.getLogger(c).info(prefix);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    public static void debug(Class cls , Object object) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            LoggerFactory.getLogger(cls).info("------------------------------------------------------------------------------------------------------------");
            LoggerFactory.getLogger(cls).info(mapper.writeValueAsString(object));
            LoggerFactory.getLogger(cls).info("------------------------------------------------------------------------------------------------------------");
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}
