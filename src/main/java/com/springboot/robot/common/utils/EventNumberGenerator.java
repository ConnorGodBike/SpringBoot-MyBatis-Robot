package com.springboot.robot.common.utils;

import org.apache.commons.lang.StringUtils;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Connor 2019年11月20日
 * 事件编号生成工具类，支持多线程并发
 */
public class EventNumberGenerator {

    private static String defaultNo = "2017111600522";
    private static String lastNo = defaultNo;

    private volatile static EventNumberGenerator primaryGenerater = null;

    private EventNumberGenerator() {
    }

    public synchronized String getLastNo(String num) {

        synchronized (lastNo) {
            return lastNo = num;
        }
    }

    /**
     * 取得PrimaryGenerater的单例实现
     *
     * @return
     */
    public static EventNumberGenerator getInstance() {
        if (primaryGenerater == null) {
            synchronized (EventNumberGenerator.class) {
                if (primaryGenerater == null) {
                    primaryGenerater = new EventNumberGenerator();
                }
            }
        }
        return primaryGenerater;
    }


    /**
     * 获取下一个编号
     *
     * @param maxNo
     * @return
     */
    public synchronized String nextNo(String maxNo) {
        synchronized (lastNo) {
            String number = null;
            Date currentTime = new Date();
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            int currentDateStr = Integer.parseInt(formatter.format(currentTime));

            //        System.out.println("currentDateStr:" + currentDateStr);

            if (StringUtils.isBlank(maxNo) || maxNo.length() < 13) {
                maxNo = currentDateStr + "00000";
            }
            int dateNumber = Integer.parseInt(maxNo.substring(0, 8));
            int lastNumber = Integer.parseInt(maxNo.substring(8, maxNo.length()));

            //        Date currentTime = new Date();
            //        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
            //        int currentDateStr =  Integer.parseInt(formatter.format(currentTime));

            if (dateNumber != currentDateStr) {
                lastNumber = 0;
            } else {
                lastNumber = lastNumber + 1;
            }

            DecimalFormat df = new DecimalFormat("00000");
            number = formatter.format(currentTime) + df.format(lastNumber);
            lastNo = number;
            return number;
        }
    }

    public static void main(String[] args) {
        EventNumberGenerator eventNumberGenerator = EventNumberGenerator.getInstance();


        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 20) {
                    System.out.println("thread 1 :" + getInstance().nextNo(eventNumberGenerator.getLastNo("2017112100002")));
                    i++;
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (i < 20) {
                    System.out.println("thread 2 :" + getInstance().nextNo(eventNumberGenerator.getLastNo("2017112100002")));
                    i++;
                    try {
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }


}
