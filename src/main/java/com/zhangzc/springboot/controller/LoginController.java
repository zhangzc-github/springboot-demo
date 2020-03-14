package com.zhangzc.springboot.controller;

import com.zhangzc.springboot.dao.EmployeeDao;
import com.zhangzc.springboot.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private EmployeeDao employeeDao;

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
}
