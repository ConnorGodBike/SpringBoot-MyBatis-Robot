package com.springboot.robot.common.utils;

import java.util.List;

/**
 * @author Connor
 * create at 2019-08-06
 * 集合工具类
 */
public class CollectionUtils {

    /**
     * 将集合转为字符串并用split分隔，类似于实现数据库的in
     * @param list
     * @param split
     * @return
     */
    public static String join(List<?> list, String split) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            stringBuilder.append(list.get(i).toString());
            if (i < list.size() - 1) {
                stringBuilder.append(split);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 将集合转为字符串并用split分隔，类似于实现数据库的in
     * 可能需要重写Object的toString
     * @param arr
     * @param split
     * @return
     */
    public static String join(Object[] arr, String split) {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            stringBuilder.append(arr[i].toString());
            if (i < arr.length - 1) {
                stringBuilder.append(split);
            }
        }
        return stringBuilder.toString();
    }
}
