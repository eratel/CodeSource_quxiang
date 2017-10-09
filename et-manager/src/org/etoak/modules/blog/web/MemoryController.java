package org.etoak.modules.blog.web;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import org.apache.log4j.Logger;
import org.etoak.modules.blog.Service.ServiceMemoryByDsc;
import org.etoak.modules.blog.Service.impl.ServiceMemoryByDscImpl;
import org.etoak.modules.blog.Service.impl.ServiceMemoryImpl;
import org.etoak.modules.blog.pojo.DetailedByMemory;
import org.etoak.modules.blog.pojo.Memory;
import org.etoak.modules.blog.util.Constant;
import org.etoak.modules.blog.util.PhotoUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.velocity.VelocityViewResolver;

import com.alibaba.druid.support.json.JSONUtils;
import com.fasterxml.jackson.databind.jsonFormatVisitors.JsonArrayFormatVisitor;
import com.fasterxml.jackson.databind.util.JSONPObject;

/**
 *  前台秀 前台页面的请求防止在这里
 * <p>Title: MemoryController</p>
 * <p>Description: </p>
 * 
 * @author	孙继凯
 * @date	2017年5月17日下午5:52:13
 * @version 1.0
 */
@Controller
public class MemoryController
{
    private Logger logger = Logger.getLogger(MemoryController.class.getSimpleName());
    
    /**
     * 读取配合信息 获得pageSize
     */
    @Value("${RESOURCES.MEMORY_CURRENTROW}")
    private Integer pageNum;
    
    @Value("${RESOURCES.MEMORY_pageSize}")
    private Integer pageSize;
    
    @Autowired
    private ServiceMemoryImpl service;
    
    @Autowired
    private ServiceMemoryByDsc serviceMemory;
    
    
    /**
     * 显示首页 请求 显示index页面
     * <p>Title: showIndex</p>
     * <p>Description:Atos </p>
     * @return
     */
    @RequestMapping("/")
    public String showIndex()
    {
        logger.debug("Start_showIndex");
        return "MyIndex";
    }
    
    /**
     * 添加请求 前后台都可以做添加功能
     * <p>Title: insertMemory</p>
     * <p>Description:Atos </p>
     * @param fileCover
     * @param request
     * @param memory
     * @return
     * @throws IOException
     */
    
    @RequestMapping("/insertMemory")
    public String insertMemory(@RequestParam MultipartFile fileCover, HttpServletRequest request, Memory memory)
        throws IOException
    {
        String newName = PhotoUtil.savePhoto(fileCover, request);
        memory.setCOVER_ONE(newName);
        String status = service.insertMemory(memory);
        logger.debug("是否有返回的数据" + status);
        //ModelAndView modelAndView = new ModelAndView("admin/admin");
        if (Constant.SUCCESS.equals(status))
        {
            return "redirect:/loginadmin.html";
        }
        return "redirect:/loginadmin.html";
    }
    
    @RequestMapping("loginadmin.html")
    public String closeFrom() {
    	return "admin/admin";
    }
    
    /**
     * 查询所有的memory
     * <p>Title: selectMemory</p>
     * <p>Description:Atos </p>
     * produces 解决乱码问题 让他返回是json 数据并且 编码为utf-8
     * @return
     */
    @RequestMapping(value = "/selectMemory", produces = MediaType.APPLICATION_JSON_VALUE + ";charset=utf-8")
    @ResponseBody
    public ArrayList<Memory> selectMemory()
    {
        HashMap<String, String> map = new HashMap<>();
         ArrayList<Memory> list = service.selectAllMemory(pageNum, pageSize, map);
        logger.debug("查询所有Memory" + list);
        return list;
    }
    /**
     * 跳转页面  点击某个页面 进行跳转
     * <p>Title: returnOne_content</p>
     * <p>Description:Atos </p>
     * @return
     */
    @RequestMapping("/SelectOne.html/{ID}")
    public String returnOne_content(@PathVariable("ID")Integer id,ModelMap map)
    {
        List<Memory> selectByID = serviceMemory.selectByID(id);
        if (selectByID != null && selectByID.size()>0)
        {
            Memory memory = selectByID.get(0);
            map.addAttribute(memory);
        }
        return "Thematic_Content";
    }
}
