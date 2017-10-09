package org.etoak.modules.blog.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.etoak.modules.blog.pojo.Memory;
import org.springframework.stereotype.Service;

/**
 * 
 * <p>
 * Title: ServiceMemory
 * </p>
 * <p>
 * Description:
 * </p>
 * 
 * @author 孙继凯
 * @date 2017年5月11日下午5:06:50
 * @version 1.0
 */
@Service
public interface ServiceMemory
{
    String insertMemory(Memory m);
    //根据id 查询memory
    Memory selectById(int ID);
    
    ArrayList<Memory> selectAllMemory(int row, int size, HashMap<String, String> map);
    
    // 后台管理系统用于删除
    String deleteMemoryById(int id);
    
    // 后台管理系统的条件查询
    ArrayList<Memory> selectMomoryByCretail(Memory m);
    
    // 更新一个Memory
    void updateMemoryByMemory(Memory m);
    
    // 查询总页数 根据properties 文件的设置就行配置
    Integer SelectPageNum(HashMap<String, String> map);
    
    //发送邮件
    boolean sendEmail(String email,String url) throws AddressException, MessagingException, FileNotFoundException, IOException;

    //登录判断用户是否存在
    boolean validateUser(String user,String pwd);
    
    //注册用户
    boolean insertUser(String name,String pwd);
}
