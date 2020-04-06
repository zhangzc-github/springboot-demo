package com.zhangzc.springboot.service;

import com.zhangzc.springboot.dao.EmployeeMapper;
import com.zhangzc.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public Employee getEmpById(int id) {
        return employeeMapper.selectOne(id);
    }

    public Collection<Employee> getEmpAll() {
        return employeeMapper.selectAll();
    }

    public void saveEmp(Employee employee) {
        employeeMapper.insert(employee);
    }

    public void updateEmp(Employee employee) {
        employeeMapper.update(employee);
    }

    public void deleteEmp(int id) {
        employeeMapper.delete(id);
    }
}
