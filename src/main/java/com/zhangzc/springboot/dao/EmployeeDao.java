package com.zhangzc.springboot.dao;

import java.util.Collection;
import com.zhangzc.springboot.entities.Employee;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

@Mapper
public interface EmployeeDao {
	@Insert("insert into employee(last_name,email,gender,birth,d_id) values(#{lastName},#{email},#{gender},#{birth},#{department.id})")
	void save(Employee employee);

	@Select("select * from employee")
	@Results(id="Employees",value = {
			@Result(column = "d_id",property = "department",
			one = @One(select = "com.zhangzc.springboot.dao.DepartmentDao.getDepartment",fetchType = FetchType.LAZY))
	})
	Collection<Employee> getAll();

	@Select("select * from employee where id=#{id}")
	@Results(id="Employee",value = {
			@Result(column = "d_id",property = "department",
					one = @One(select = "com.zhangzc.springboot.dao.DepartmentDao.getDepartment",fetchType = FetchType.LAZY))
	})
	Employee get(Integer id);
	@Delete("delete from employee where id = #{id}")
	void delete(Integer id);
	@Update("update employee set last_name=#{lastName},email=#{email},gender=#{gender},birth=#{birth},d_id=#{department.id} where id=#{id}")
	void update(Employee employee);
}
