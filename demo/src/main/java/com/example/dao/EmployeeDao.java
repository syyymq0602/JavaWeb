package com.example.dao;

import com.example.pojo.Department;
import com.example.pojo.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class EmployeeDao {

    private static Map<Integer, Employee> employees=null;
    @Autowired
    private DepartmentDao departmentDao;

    static {
        employees = new HashMap<Integer,Employee>();

        employees.put(101,new Employee(1001,"aa","aa@163.com",1,new Department(101,"教学部")));
        employees.put(102,new Employee(1002,"bb","bb@163.com",0,new Department(102,"市场部")));
        employees.put(103,new Employee(1003,"cc","cc@163.com",0,new Department(104,"运营部")));
        employees.put(104,new Employee(1004,"dd","dd@163.com",1,new Department(105,"后勤部")));
        employees.put(105,new Employee(1005,"ee","ee@163.com",1,new Department(101,"教学部")));
    }

    private static Integer initId = 1006;

    public void save(Employee employee){
        if (employee.getId() == null){
            employee.setId(initId++);
        }
        employee.setDepartment(departmentDao.getDepartmentById(employee.getDepartment().getId()));

        employees.put(employee.getId(),employee);
    }

    public Collection<Employee> getAll(){
        return employees.values();
    }

    public Employee getEmployeeById(Integer id){
        return employees.get(id);
    }

    public void delete(Integer id){
        employees.remove(id);
    }
}
