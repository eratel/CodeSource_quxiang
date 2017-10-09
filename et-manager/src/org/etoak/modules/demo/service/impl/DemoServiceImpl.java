package org.etoak.modules.demo.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.etoak.modules.demo.mapper.DemoMapper;
import org.etoak.modules.demo.pojo.Demo;
import org.etoak.modules.demo.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class DemoServiceImpl implements DemoService {

	@Autowired
	private DemoMapper demoMapper;
	
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void add(Demo demo) {
		demoMapper.add(demo);
	}
	
	//分页
	public Map queryMessageListByPage(){
		
		PageHelper.startPage(1, 10);//第一个参数表示当前页，第二个参数表示每页记录数
		
		List<Demo> list = demoMapper.queryMessageListByPage();
		
		//获取集合中信息
		PageInfo<Demo> pageInfo = new PageInfo<Demo>(list);
		
		Map<String,Object> result = new HashMap<String, Object>();
		result.put("rows", list);
		result.put("total", pageInfo.getTotal());
		
		
		return result;
	}

}
