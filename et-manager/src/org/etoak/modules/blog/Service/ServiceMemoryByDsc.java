package org.etoak.modules.blog.Service;

import java.util.List;

import org.etoak.modules.blog.pojo.DetailedByMemory;
import org.etoak.modules.blog.pojo.Memory;

public interface ServiceMemoryByDsc
{
    //根据ID 查询出详细的数据
    List<Memory> selectByID(Integer id);
}
