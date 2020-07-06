package utils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;

/**
 * @author zzy
 * @date 2020/7/2 22:09
 */
public class JsonUtils {

    private static final ObjectMapper MAPPER = new ObjectMapper();


    /**
     * 对象转换为json
     *
     * @param date
     * @return
     */
    public static String object2Json(Object date) {
        try {
            String string = MAPPER.writeValueAsString(date);
            return string;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json对象转换为对象
     * @param jsonData
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T> T json2Pojo(String jsonData, Class<T> beanClass) {
        try {
            T t = MAPPER.readValue(jsonData, beanClass);
            return t;
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将json对象转换为list
     * @param jsonData
     * @param beanClass
     * @param <T>
     * @return
     */
    public static <T>List<T> json2List(String jsonData , Class<T> beanClass){
        JavaType javaType = MAPPER.getTypeFactory().constructParametricType(List.class, beanClass);
        try {
            List<T> value = MAPPER.readValue(jsonData, javaType);
            return value;
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
