package com.jadeon.propertites;

public class ConfigUtil {

    static ToolUtil tools;

    static{
        tools = new ToolUtil("config.properties");
    }

    public static String get(String key){
        return tools.getValue(key);
    }

    public static void main(String [] args){

        System.out.println(ConfigUtil.get("name"));

    }

}
