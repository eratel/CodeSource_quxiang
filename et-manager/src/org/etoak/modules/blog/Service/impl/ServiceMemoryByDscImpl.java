package org.etoak.modules.blog.Service.impl;

import java.util.List;

import org.etoak.modules.blog.Service.ServiceMemoryByDsc;
import org.etoak.modules.blog.mapper.DetailedByMemoryMapper;
import org.etoak.modules.blog.mapper.MemoryMapper;
import org.etoak.modules.blog.pojo.DetailedByMemory;
import org.etoak.modules.blog.pojo.DetailedByMemoryExample;
import org.etoak.modules.blog.pojo.Memory;
import org.etoak.modules.blog.pojo.MemoryExample;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class ServiceMemoryByDscImpl implements ServiceMemoryByDsc
{
    @Autowired
    private MemoryMapper mapper;
    
    @Override
    public List<Memory> selectByID(Integer id)
    {
        MemoryExample example = new MemoryExample();
        example.createCriteria().andIDEqualTo(id);
        List<Memory> selectByMid = mapper.selectByExample(example);
        return selectByMid;
    }
    
}
