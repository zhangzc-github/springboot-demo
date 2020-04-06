package com.zhangzc.springboot.dao;

import com.zhangzc.springboot.entities.Employee;
import org.apache.ibatis.annotations.Mapper;

import java.util.Collection;

@Mapper
public interface EmployeeMapper {
    void insert(Employee employee);
    void delete(int id);
    void update(Employee employee);
    Employee selectOne(int id);
    Collection<Employee> selectAll();

}
