package org.etoak.modules.demo.service;

import java.util.Map;

import org.etoak.modules.demo.pojo.Demo;

public interface DemoService {
	public void add(Demo demo);
	
	//分页
	public Map queryMessageListByPage();
}
