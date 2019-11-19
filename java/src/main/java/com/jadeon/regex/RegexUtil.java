package com.jadeon.regex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtil {

    /**
     * 过滤特殊字符
     * @param str
     * @return
     */
    public static String StringFilter(String str){
        String regEx="[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(regEx);
        return m.replaceAll(str).trim();
    }

}
