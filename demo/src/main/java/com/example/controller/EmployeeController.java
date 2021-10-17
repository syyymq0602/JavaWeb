package com.example.controller;

import com.example.dao.EmployeeDao;
import com.example.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Collection;

@Controller
@RequestMapping("/emps")
public class EmployeeController {
    @Autowired
    private EmployeeDao employeeDao;
    @RequestMapping("/all")
    public String list(Model model){
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("emps",employees);
        return "emp/list";
    }
    @RequestMapping("add")
    public void save(){
        
    }
}
