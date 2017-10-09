package org.etoak.modules.blog.web;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.catalina.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpRequest;
import org.apache.log4j.Logger;
import org.etoak.modules.blog.Service.impl.ServiceMemoryImpl;
import org.etoak.modules.blog.pojo.Memory;
import org.etoak.modules.blog.util.Constant;
import org.etoak.modules.blog.util.PhotoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 这是后台管理系统的controller
 * 
 * <p>Title: WorkController</p>
 * <p>Description: </p>
 * 
 * @author	孙继凯
 * @date	2017年5月17日下午5:48:27
 * @version 1.0
 */
@Controller
public class WorkController
{
    private Logger logger = Logger.getLogger(WorkController.class.getSimpleName());
    
    @Autowired
    private ServiceMemoryImpl service;;
    
    // @Value("${RESOURCES.MEMORY_CURRENTROW}")
    // private Integer pageNum;
    
    @Value("${RESOURCES.MEMORY_pageSize}")
    private Integer pageSize;
    
    /**
     * 搜索所有的回忆  可以搜索条件
     * <p>Title: selectAllMemory</p>
     * <p>Description:Atos </p>
     * @param pageNum
     * @param name
     * @param address
     * @param desc
     * @return
     */
    @RequestMapping("/seletAllMemoryWork")
    @ResponseBody
    public ArrayList<Memory> selectAllMemory(@RequestParam Integer pageNum, String name, String address, String desc)
    {
        HashMap<String, String> map = new HashMap<>();
        if (!StringUtils.isEmpty(name))
        {
            map.put("name", name);
        }
        if (!StringUtils.isEmpty(desc))
        {
            map.put("desc", desc);
        }
        if (!StringUtils.isEmpty(address))
        {
            map.put("address", address);
        }
        
        ArrayList<Memory> list = service.selectAllMemory(pageNum, pageSize, map);
        return list;
    }
    
    /**
     * 删除某个Memory 对指定的Memory进行删除
     * <p>Title: deleteMemoryByID</p>
     * <p>Description:Atos </p>
     * @param id
     * @param request
     * @return
     */
    @ResponseBody
    @RequestMapping("/deleteMemoryByID")
    public String deleteMemoryByID(int id, HttpServletRequest request)
    {
        logger.debug("start deleteMemoryByID");
        Memory memory = service.selectById(id);
        String status = Constant.SUCCESS;
        
        if (memory != null)
        {
            PhotoUtil.deletePhotoByID(memory.getCOVER_ONE(), request);
            service.deleteMemoryById(id);
        }
        
        return status;
    }
    
    /**
     * 计算出回忆的数量 通过Memory的条件
     * <p>Title: selectCountEx</p>
     * <p>Description:Atos </p>
     * @param name  
     * @param address
     * @param desc
     * @return
     */
    @ResponseBody
    @RequestMapping("/selectMemoryCount")
    public String selectCountEx(String name, String address, String desc)
    {
        
        HashMap<String, String> map = new HashMap<>();
        if (!StringUtils.isBlank(name))
        {
            map.put("name", name);
        }
        if (!StringUtils.isBlank(desc))
        {
            map.put("desc", desc);
        }
        if (!StringUtils.isBlank(address))
        {
            map.put("address", address);
        }
        
        int CountM = service.SelectPageNum(map);
        Integer pagNum = CountM / pageSize;
        logger.debug("现在是第" + pagNum.toString() + "页---------------------------");
        
        return pagNum.toString();
    }
    
    @Value("${RESOURCES.ADMIN_user}")
    private String user;
    
    @Value("${RESOURCES.ADMIN_password}")
    private String Password;
    
    // 用户登录的验证
    @RequestMapping("/admin.html")
    public String showIndex(@RequestParam("email") String usr, @RequestParam("password") String pwd)
        throws Exception
    {
        
        if (StringUtils.isNotBlank(usr) && StringUtils.isNotBlank(pwd))
        {
            if (service.validateUser(usr, pwd))
            {
//                HttpSession session = request.getSession();
//                session.setAttribute("token", UUID.randomUUID());
                return "admin/admin";
            }
            else
            {
                return "admin/login";
            }
        }
        else
        {
            return "admin/login";
        }
    }
    
    
    // 用户首页
    @RequestMapping("/login.html")
    public String loginAdmin()
    {
        return "admin/login";
    }
    
    // 添加用户
    @RequestMapping("/addEmailUser.html")
    public String addUser(@RequestParam("email") String email, @RequestParam("password") String pwd,HttpServletRequest request) throws AddressException, FileNotFoundException, MessagingException, IOException
    {
        service.insertUser(email, pwd);
        String realPath = request.getSession().getServletContext().getRealPath("PC-WAP/img/send.jpg"); 
        service.sendEmail(email,realPath);
        return "admin/login";
    }
}
