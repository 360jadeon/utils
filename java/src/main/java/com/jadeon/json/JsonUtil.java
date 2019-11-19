package com.jadeon.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class JsonUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(JsonUtil.class);

    //定义JsonMapper对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    /**
     * 讲对象转换为对象
     * @param obj
     * @return
     */
    public static String ObjectToString(Object obj) {
        try {
            return MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            LOGGER.error(e.toString());
        }
        return null;
    }

    public static <T>T jsonToPojo(String jsonData, Class<T> beanType){
        try{
            return MAPPER.readValue(jsonData, beanType);
        }
        catch(Exception e){
            LOGGER.error(e.toString());
        }
        return null;
    }

    /**
     * 将json数据转换为pojo对象list
     * @param <T>
     * @return
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType){
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanType);
        try{
            return MAPPER.readValue(jsonData, javaType);
        }
        catch(Exception e){
            LOGGER.error(e.toString());
        }
        return null;
    }

}

