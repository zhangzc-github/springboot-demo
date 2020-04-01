package com.zhangzc.springboot.controller;

import com.zhangzc.springboot.dao.DepartmentDao;
import com.zhangzc.springboot.dao.EmployeeDao;
import com.zhangzc.springboot.entities.Department;
import com.zhangzc.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private EmployeeDao employeeDao;
    @Autowired
    private DepartmentDao departmentDao;

    @RequestMapping("/user/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Map<String, Object> map,
                        HttpSession session) {
        if (!StringUtils.isEmpty(username) && "123".equals(password)) {
            session.setAttribute("user", username);
            return "redirect:/main";
        }
        map.put("msg", "登录失败!");
        return "index";
    }

    @GetMapping("/emps")
    public String getEmps(Model model) {
        Collection<Employee> emps = employeeDao.getAll();
        model.addAttribute("emps",emps);
        return "list";
    }

    @GetMapping("/emp")
    public String toAddEmpPage(Model model) {
        Collection<Department> depts = departmentDao.getDepartments();
        model.addAttribute("depts", depts);
        return "add";
    }

    @PostMapping("/emp")
    public String addEmp(Employee employee) {
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    @GetMapping("/emp/{id}")
    public String getEmp(@PathVariable("id") Integer id,
                         Model model) {
        Employee emp = employeeDao.get(id);
        Collection<Department> depts = departmentDao.getDepartments();
        model.addAttribute("depts", depts);
        model.addAttribute("emp", emp);
        return "add";
    }
    @PutMapping("/emp")
    public String editEmp(Employee employee) {
        employeeDao.update(employee);
        return "redirect:/emps";
    }

    @DeleteMapping("/emp/{id}")
    public String deleteEmp(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }
}
