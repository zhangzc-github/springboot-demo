package com.zhangzc.springboot.dao;

import com.zhangzc.springboot.entities.Department;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface DepartmentMapper {
    Department findById(int id);
}
