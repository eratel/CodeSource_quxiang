package org.etoak.modules.blog.Service.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.etoak.modules.blog.mapper.MemoryMapper;
import org.etoak.modules.blog.mapper.UsersMapper;
import org.etoak.modules.blog.pojo.Memory;
import org.etoak.modules.blog.pojo.MemoryExample;
import org.etoak.modules.blog.pojo.Users;
import org.etoak.modules.blog.pojo.UsersExample;
import org.etoak.modules.blog.util.Constant;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;


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
 * @date 2017年4月24日下午2:32:42
 * @version 1.0
 */
@Service
public class ServiceMemoryImpl implements org.etoak.modules.blog.Service.ServiceMemory
{
    @Autowired
    private MemoryMapper mapper;
    @Autowired
    private UsersMapper UserMapper;
    
    @Override
    public String insertMemory(Memory m)
    {
        /*
         * int ID = IDUtils.genMemoryID(); m.setID(ID);
         */
        int key = mapper.insert(m);
        if (key >= 0)
        {
            return Constant.SUCCESS;
        }
        return Constant.ERROR;
    }
    
    /**
     * 
     * <p>
     * Title: selectAllMemory
     * </p>
     * <p>
     * Description:Atos
     * </p>
     * 
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public ArrayList<Memory> selectAllMemory(int pageNum, int pageSize, HashMap<String, String> map)
    {
        MemoryExample example = new MemoryExample();
        if (map != null)
        {
            String address = map.get("address");
            String desc = map.get("desc");
            String name = map.get("name");
            
            if (!"".equals(address) && address != null)
            {
                ArrayList<String> list = new ArrayList<String>();
                list.add(address);
                example.createCriteria().andADRESSIn(list);
            }
            
            if (!"".equals(desc) && desc != null)
            {
                ArrayList<String> list = new ArrayList<String>();
                list.add(desc);
                example.createCriteria().andDESCPTIONIn(list);
            }
            
            if (!"".equals(name) && name != null)
            {
                ArrayList<String> list = new ArrayList<String>();
                list.add(name);
                example.createCriteria().andNAMEIn(list);
            }
        }
        
        PageHelper.startPage(pageNum, pageSize);
        ArrayList<Memory> list = (ArrayList<Memory>)mapper.selectByExample(example);
        return list;
    }
    
    @Override
    public Memory selectById(int ID)
    {
        MemoryExample example = new MemoryExample();
        example.createCriteria().andIDEqualTo(ID);
        ArrayList<Memory> list = (ArrayList<Memory>)mapper.selectByExample(example);
        if (list != null)
        {
            return list.get(0);
        }
        return null;
    }
    
    @Override
    public String deleteMemoryById(int id)
    {
        mapper.deleteByPrimaryKey(id);
        return Constant.SUCCESS;
    }
    
    @Override
    public ArrayList<Memory> selectMomoryByCretail(Memory m)
    {
        MemoryExample example = new MemoryExample();
        if ("".equals(m.getADRESS()) && m.getADRESS() != null)
        {
            example.createCriteria().andADRESSEqualTo(m.getADRESS());
        }
        if ("".equals(m.getDESCPTION()) && m.getDESCPTION() != null)
        {
            example.createCriteria().andDESCPTIONEqualTo(m.getDESCPTION());
        }
        if ("".equals(m.getNAME()) && m.getNAME() != null)
        {
            example.createCriteria().andNAMEEqualTo(m.getNAME());
        }
        if ("".equals(m.getSPARE()) && m.getSPARE() != null)
        {
            example.createCriteria().andSPAREEqualTo(m.getSPARE());
        }
        ArrayList<Memory> list = (ArrayList<Memory>)mapper.selectByExample(example);
        return list;
    }
    
    /**
     * 
     * <p>
     * Title: updateMemoryByMemory
     * </p>
     * <p>
     * Description:
     * </p>
     * 
     * @param m
     * @see org.etoak.modules.blog.Service.ServiceMemory#updateMemoryByMemory(org.etoak.modules.blog.pojo.Memory)
     */
    @Override
    public void updateMemoryByMemory(Memory m)
    {
        mapper.updateByPrimaryKey(m);
    }
    
    @Override
    public Integer SelectPageNum(HashMap<String, String> map)
    {
        MemoryExample example = new MemoryExample();
        
        if (map != null)
        {
            String address = map.get("address");
            String desc = map.get("desc");
            String name = map.get("name");
            
            if (!"".equals(address) && address != null)
            {
                ArrayList<String> list = new ArrayList<String>();
                list.add(address);
                example.createCriteria().andADRESSIn(list);
            }
            
            if (!"".equals(desc) && desc != null)
            {
                ArrayList<String> list = new ArrayList<String>();
                list.add(desc);
                example.createCriteria().andDESCPTIONIn(list);
            }
            
            if (!"".equals(name) && name != null)
            {
                ArrayList<String> list = new ArrayList<String>();
                list.add(name);
                example.createCriteria().andNAMEIn(list);
            }
            
            int count = mapper.countByExample(example);
            return count;
        }
        return null;
    }

    @Test
    @Override
    public boolean sendEmail(String email,String url) throws AddressException, MessagingException, FileNotFoundException, IOException
    {
        Properties props = new Properties();//环境变量设置。发送邮件时才需要
        props.setProperty("mail.transport.protocol", "smtp");//发送使用的协议
        props.setProperty("mail.host", "smtp.163.com");//发送服务器的主机地址
        props.setProperty("mail.smtp.auth", "true");//请求身份验证
        props.setProperty("mail.debug", "true");//调试模式
        Session session = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(session);//代表一封邮件
        
        message.setFrom(new InternetAddress("m15098180539@163.com"));//设置发件人
        message.setRecipients(Message.RecipientType.TO, email);//设置收件人
        message.setSubject("来自去向往网的问候--");//设置主题
        
        
        MimeBodyPart textPart = new MimeBodyPart();
        textPart.setContent("亲爱的       "+email+":<br/>"
        		+ "<b>欢迎您注册去向网 , 关注实时去向, 享受一级游玩乐趣, 与同道中人分享自己的乐趣。</b>"
        		+ "<br/>","text/html;charset=UTF-8");
        
        MimeBodyPart imagePart = new MimeBodyPart();
        //把磁盘上的文件读到邮件中来：借助JAF框架
        DataHandler dh = new DataHandler(new FileDataSource(url));
        //通过jaf读取到的文件，不需要设置其MIME类型，JAF框架会自动探测到
        imagePart.setDataHandler(dh);
        imagePart.setContentID("mm");
        
        //描述二者的关系
        MimeMultipart mmpart = new MimeMultipart();
        mmpart.addBodyPart(textPart);
        mmpart.addBodyPart(imagePart);
        mmpart.setSubType("related");//说明两部分是有关系的
        
        message.setContent(mmpart);
        message.saveChanges();
        //将邮件写出到本地硬盘中
        //message.writeTo(new FileOutputStream("C:/Users/Lenovo/Desktop/2.eml"));
        Transport ts = session.getTransport();//得到火箭
        ts.connect("m15098180539@163.com","sunjikai.123");//连接
        ts.sendMessage(message, message.getAllRecipients());
        
        ts.close();
        return false;
    }
//    public static void main(String[] args) throws AddressException, FileNotFoundException, MessagingException, IOException
//    {
//        ServiceMemoryImpl serviceMemoryImpl = new ServiceMemoryImpl();
//        serviceMemoryImpl.sendEmail("huangyue19880830@163.com","C:/Users/Administrator/Desktop/send.jpg");
//    }

    @Override
    public boolean validateUser(String user, String pwd)
    {
        UsersExample usersExample = new UsersExample();
        usersExample.createCriteria().andUSERSEqualTo(user);
        usersExample.createCriteria().andPASSWORDEqualTo(pwd);
        List<Users> selectByExample = UserMapper.selectByExample(usersExample);
        boolean empty = selectByExample.isEmpty();
        return !empty;
    }

    @Override
    public boolean insertUser(String name, String pwd)
    {
        try
        {
            Users users = new Users();
            users.setNAME(name);
            users.setPASSWORD(pwd);
            users.setUSERS(name);
            UserMapper.insertSelective(users);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return true;
    }
}
