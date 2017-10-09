package org.etoak.modules.blog.Service.impl;

import org.etoak.modules.blog.Service.ServiceTree;
import org.etoak.modules.blog.mapper.TreeMapper;
import org.etoak.modules.blog.pojo.Tree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>Title: ServiceImpl</p>
 * <p>Description: </p>
 * <p>Company: www.itcast.com</p> 
 * @author	孙继凯
 * @date	2017年4月13日下午9:26:45
 * @version 1.0
 */
@Service
public class ServiceTreeImpl implements ServiceTree
{
    @Autowired
    private TreeMapper treeMapper;
    
    @Override
    public void insert(Tree tree)
    {
        Tree tree2 = treeMapper.selectByPrimaryKey(1);
    }
    
}
