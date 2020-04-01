package com.zhangzc.springboot.dao;

import java.util.Collection;
import com.zhangzc.springboot.entities.Department;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;


@Mapper
public interface DepartmentDao {

	@Select("select * from department order by id desc;")
	Collection<Department> getDepartments();
	@Select("select * from department where id=#{id}")
	Department getDepartment(Integer id);
	
}
