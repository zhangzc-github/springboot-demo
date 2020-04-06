package com.zhangzc.springboot.dao;

import com.zhangzc.springboot.entities.Department;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

@Mapper
public interface DepartmentMapper {
    Department selectOne(int id);
    Collection<Department> selectAll();
}
