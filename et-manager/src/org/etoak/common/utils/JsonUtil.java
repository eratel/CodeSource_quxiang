package org.etoak.common.utils;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.aspectj.weaver.patterns.ThisOrTargetAnnotationPointcut;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * json处理工具类
 */
public class JsonUtil {

	private static final String DEFAULT_DATE_FORMAT="yyyy-MM-dd HH:mm:ss";
	private static final ObjectMapper mapper;

	static {
		SimpleDateFormat dateFormat = new SimpleDateFormat(DEFAULT_DATE_FORMAT);
		mapper = new ObjectMapper();
		mapper.setDateFormat(dateFormat);
	}
	
	/**
	 * 将对象转换成json字符串
	 * @param obj
	 * @return
	 */
	public static String toJson(Object obj) {
		try {
			return mapper.writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException("转换json字符失败!");
		}
	}
	
	/**
	 * 将json字符串转换成对象 object、集合，
	 * json和clazz参数必须一一对象不能有多余的属性，
	 * 否则出现异常。
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T toObject(String json,Class<T> clazz) {
		try {
			return mapper.readValue(json, clazz);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("将json字符转换为对象时失败!");
		}
	}
	/**
	 * 将json字符串转换成对象 object、集合，
	 * json和clazz参数可以出现多余的属性。
	 * @param json
	 * @param typeReference
	 * @return
	 */
	public static <T> T toObject(String json,TypeReference<T> typeReference) {
		try {
			mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
			return mapper.readValue(json, typeReference);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("将json字符转换为对象时失败!");
		}
	}
	
	/**
     * 将json数据转换成pojo对象list
     * <p>Title: jsonToList</p>
     * <p>Description: </p>
     * @param jsonData
     * @param beanType
     * @return
     */
    public static <T>List<T> jsonToList(String jsonData, Class<T> beanType) {
    	JavaType javaType = mapper.getTypeFactory().constructParametricType(List.class, beanType);
    	try {
    		List<T> list = mapper.readValue(jsonData, javaType);
    		return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
    	
    	return null;
    }
	
}
