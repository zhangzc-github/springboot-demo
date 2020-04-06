package com.zhangzc.springboot.service;

import com.zhangzc.springboot.dao.DepartmentMapper;
import com.zhangzc.springboot.entities.Department;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;

    @Cacheable(value = "department")
    public Department getDeptById(int id) {
        System.out.println("from db");
        return departmentMapper.selectOne(id);
    }
    @Cacheable(value = "department",key = "'list'")
    public Collection<Department> getDepts(){
        return departmentMapper.selectAll();
    }
}
