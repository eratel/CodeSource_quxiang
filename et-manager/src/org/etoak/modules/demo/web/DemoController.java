package org.etoak.modules.demo.web;

import java.util.Map;
import java.util.logging.Logger;

import org.etoak.modules.demo.pojo.Demo;
import org.etoak.modules.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "/demo")
public class DemoController
{
    
    private static final Logger LOG = Logger.getLogger(DemoController.class.getName());
    
    @Autowired
    private DemoService demoService;
    
    @RequestMapping(value = "/add")
    public void add(String name)
    {
        Demo demo = new Demo();
        demo.setName(name);
        demoService.add(demo);
    }
    
    @RequestMapping(value = "/query")
    @ResponseBody
    public Map query()
    {
        Map<String, Object> map = demoService.queryMessageListByPage();
        return map;
    }
    
}
