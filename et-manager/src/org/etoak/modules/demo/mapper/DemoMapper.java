package org.etoak.modules.demo.mapper;

import java.util.List;
import java.util.Map;

import org.etoak.modules.demo.pojo.Demo;

public interface DemoMapper {
	public void add(Demo demo);
	//分页
	public List<Demo> queryMessageListByPage();
}
