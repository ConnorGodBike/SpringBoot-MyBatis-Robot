package com.springboot.robot.common.utils;

/**
 * 字符串处理工具
 * create By Connor 2018-07-11
 *
 */
public class VersionUtils {

    public static void main(String[] args) {
        System.out.println(VersionUtils.compareVersion("18.23.3.4.1", "18.22"));
    }

    public static int compareVersion(String v1, String v2) {
        if (v1.equals(v2)) {
            return 0;
        }
        int i = 0, j = 0, x = 0, y = 0;
        int v1Len = v1.length();
        int v2Len = v2.length();
        char c;
        do {
            while (i < v1Len) {//计算出V1中的点之前的数字
                c = v1.charAt(i++);
                if (c >= '0' && c <= '9') {
                    x = x * 10 + (c - '0');//c-‘0’表示两者的ASCLL差值
                } else if (c == '.') {
                    break;//结束
                } else {
                    //无效的字符
                }
            }
            while (j < v2Len) {//计算出V2中的点之前的数字
                c = v2.charAt(j++);
                if (c >= '0' && c <= '9') {
                    y = y * 10 + (c - '0');
                } else if (c == '.') {
                    break;//结束
                } else {
                    //无效的字符
                }
            }
            if (x < y) {
                return -1;
            } else if (x > y) {
                return 1;
            } else {
                x = 0;
                y = 0;
                continue;
            }

        } while ((i < v1Len) || (j < v2Len));
        return 0;
    }
}
