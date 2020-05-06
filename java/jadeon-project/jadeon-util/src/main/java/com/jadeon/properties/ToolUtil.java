package com.jadeon.propertites;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class ToolUtil {

    private static Properties p = new Properties();
    private static String f;

    public ToolUtil(String file){
        f = file;
        try {
            p.load(ToolUtil.class.getClassLoader().getResourceAsStream(f));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 根据key得到value的值
     */
    public String getValue(String key)
    {
        try {
            return new String(p.getProperty(key).getBytes("ISO8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            return "";
        }
    }

}
