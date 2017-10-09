package org.etoak.modules.blog.pojo;

import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 
 * <p>Title: MemoryResult</p>
 * <p>Description: </p>
 * 
 * @author	孙继凯
 * @date	2017年4月25日上午10:45:52
 * @version 1.0
 */
public class MemoryResult
{
    
    // 定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();
    
    // 响应业务状态
    private Integer status;
    
    // 响应消息
    private String msg;
    
    // 响应中的数据
    private Object data;
    
    /**
     * 无参构造
     * <p>
     * Title:
     * </p>
     * <p>
     * Description:
     * </p>
     */
    public MemoryResult()
    {
        
    }
    
    public MemoryResult(Integer status, String msg, Object data)
    {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }
    
    public MemoryResult(Object data)
    {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }
    
    public static MemoryResult build(Integer status, String msg, Object data)
    {
        return new MemoryResult(status, msg, data);
    }
    
    public static MemoryResult build(Integer status, String msg)
    {
        return new MemoryResult(status, msg, null);
    }
    
    public static MemoryResult ok(Object data)
    {
        return new MemoryResult(data);
    }
    
    public static MemoryResult ok()
    {
        return new MemoryResult(null);
    }
    
    // public Boolean isOK() {
    // return this.status == 200;
    // }
    
    public Integer getStatus()
    {
        return status;
    }
    
    public void setStatus(Integer status)
    {
        this.status = status;
    }
    
    public String getMsg()
    {
        return msg;
    }
    
    public void setMsg(String msg)
    {
        this.msg = msg;
    }
    
    public Object getData()
    {
        return data;
    }
    
    public void setData(Object data)
    {
        this.data = data;
    }
    
    /**
     * 将json结果集转化为MemoryResult对象
     * 
     * @param jsonData json数据
     * @param clazz TaotaoResult中的object类型
     * @return
     */
    public static MemoryResult formatToPojo(String jsonData, Class<?> clazz)
    {
        try
        {
            if (clazz == null)
            {
                return MAPPER.readValue(jsonData, MemoryResult.class);
            }
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (clazz != null)
            {
                if (data.isObject())
                {
                    obj = MAPPER.readValue(data.traverse(), clazz);
                }
                else if (data.isTextual())
                {
                    obj = MAPPER.readValue(data.asText(), clazz);
                }
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
    /**
     * 没有object对象的转化
     * 
     * @param json
     * @return
     */
    public static MemoryResult format(String json)
    {
        try
        {
            return MAPPER.readValue(json, MemoryResult.class);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
    
    /**
     * Object是集合转化
     * 
     * @param jsonData json数据
     * @param clazz 集合中的类型
     * @return
     */
    public static MemoryResult formatToList(String jsonData, Class<?> clazz)
    {
        try
        {
            JsonNode jsonNode = MAPPER.readTree(jsonData);
            JsonNode data = jsonNode.get("data");
            Object obj = null;
            if (data.isArray() && data.size() > 0)
            {
                obj = MAPPER.readValue(data.traverse(),
                    MAPPER.getTypeFactory().constructCollectionType(List.class, clazz));
            }
            return build(jsonNode.get("status").intValue(), jsonNode.get("msg").asText(), obj);
        }
        catch (Exception e)
        {
            return null;
        }
    }
    
}
