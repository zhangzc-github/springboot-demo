package com.zhangzc.springboot.controller;

import com.zhangzc.springboot.dao.DepartmentDao;
import com.zhangzc.springboot.dao.DepartmentMapper;
import com.zhangzc.springboot.entities.Department;
import com.zhangzc.springboot.entities.Employee;
import com.zhangzc.springboot.service.DepartmentService;
import com.zhangzc.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
public class HelloController {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    DepartmentMapper departmentMapper;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("/hello")
    @ResponseBody
    public String hello() {
        return "Hello World!";
    }

    @RequestMapping("/query/depts")
    @ResponseBody
    public Collection<Department> query(){
        //List<Map<String, Object>> maps = jdbcTemplate.queryForList("select * from department;");
        Collection<Department> list = departmentService.getDepts();
        return list;
    }
    @RequestMapping("/query/depts/{id}")
    @ResponseBody
    public Department getDepartmentById(@PathVariable("id") int id){
        //return departmentMapper.findById(id);
        return departmentService.getDeptById(id);
    }
    @RequestMapping("/query/emp/{id}")
    @ResponseBody
    public Employee getEmpById(@PathVariable("id") int id) {
        return employeeService.getEmpById(id);
    }

    @RequestMapping("/query/emps")
    @ResponseBody
    public Collection<Employee> getEmps() {
        return employeeService.getEmpAll();
    }
}
