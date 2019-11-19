package com.jadeon.random;

import java.util.Random;

/**
 * @ Author     ：Jadeon
 * @ Date       ：2019-03-14
 * @ Description：随机字符串生成
 */
public class RandomUtil {

    public static final String ALLCHAR = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String LETTERCHAR = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    public static final String NUMBERCHAR = "0123456789";

    /**
     * 返回一个定长的随机字符串(只包含大小写字母、数字)
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateString(int length){
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i<length; i++)
            stringBuilder.append(ALLCHAR.charAt(random.nextInt(ALLCHAR.length())));
        return stringBuilder.toString();
    }

    /**
     * 返回一个定长的随机字符串(只包含大小写字母)
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateMixString(int length){
        StringBuilder stringBuilder = new StringBuilder();
        Random random = new Random();
        for(int i = 0; i<length; i++)
            stringBuilder.append(LETTERCHAR.charAt(random.nextInt(LETTERCHAR.length())));
        return stringBuilder.toString();
    }

    /**
     * 返回一个定长的随机纯小写字母字符串(只包含小写字母)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateLowerString(int length) {
        return generateMixString(length).toLowerCase();
    }

    /**
     * 回一个定长的随机纯大写字母字符串(只包含大写字母)
     *
     * @param length 随机字符串长度
     * @return 随机字符串
     */
    public static String generateUpperString(int length) {
        return generateMixString(length).toUpperCase();
    }

}
