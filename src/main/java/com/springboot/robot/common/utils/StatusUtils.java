package com.springboot.robot.common.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 状态码任意组合工具类
 * create by Connor on 2018-07-11
 */
public class StatusUtils {

    /**
     * 获取状态码（2等比数列）
     *
     * @param number 状态码的和
     * @return
     */
    public String getStatusString(int number) {
        division(number, number, "");
        String result = getResult();
        if (result.endsWith(",")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    /**
     * 获取状态码（2等比数列）
     *
     * @param number 状态码的和
     * @return
     */
    public Integer[] getStatusIntegerArr(int number) {
        division(number, number, "");
        String[] strNumbers = getResult().split(",");
        List<Integer> list = new ArrayList<>();
        for (String s : strNumbers) {
            list.add(Integer.valueOf(s));
        }
        Integer[] i = new Integer[list.size()];
        return list.toArray(i);
    }

    /**
     * 获取状态码（2等比数列）
     *
     * @param number 状态码的和
     * @return
     */
    public List<Integer> getStatusIntegerList(int number) {
        division(number, number, "");
        String[] strNumbers = getResult().split(",");
        List<Integer> list = new ArrayList<>();
        for (String s : strNumbers) {
            list.add(Integer.valueOf(s));
        }
        return list;
    }

    /**
     * 获取状态码（2等比数列）
     *
     * @param number 状态码的和
     * @return
     */
    public Long[] getStatusLongArr(int number) {
        division(number, number, "");
        String[] strNumbers = getResult().split(",");
        List<Long> list = new ArrayList<>();
        for (String s : strNumbers) {
            list.add(Long.valueOf(s));
        }
        Long[] i = new Long[list.size()];
        return list.toArray(i);
    }

    private String result;

    public void setResult(String result) {
        this.result = result;
    }

    public String getResult() {
        return this.result;
    }

    private void division(int n, int m, String str) {
        if (n < 0) {
            return;
        } else if (n == 0) {
            setResult(str);
        } else {
            for (int i = 0; i < m; i++) {
                if ((int) Math.pow(2, i) > n) {
                    break;
                }
                division(n - (int) Math.pow(2, i), i, (int) Math.pow(2, i) + "," + str);
            }
        }
    }
}
