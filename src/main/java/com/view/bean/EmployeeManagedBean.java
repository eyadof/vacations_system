/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view.bean;

import com.model.dao.DepartmentDao;
import com.model.entity.Employee;
import com.model.dao.EmployeeDao;
import com.model.entity.Department;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


/**
 *
 * @author eyadof
 */
@ManagedBean
@SessionScoped
public class EmployeeManagedBean implements Serializable{
    
    private Employee employee;
    private List<Employee> employeeList;
    private Employee currentUser;
    private long departmentId;
    /**
     * Creates a new instance of EmployeeManagedBean
     */
    public EmployeeManagedBean() {
        employee = new Employee();
        employeeList= new ArrayList<Employee>();
    }

    @PostConstruct
    public void init(){
        employeeList = EmployeeDao.list();
    }
          
    public void addEmploye() {
        Department dep = DepartmentDao.getById(departmentId);
        employee.setDepartment(dep);
        boolean res = EmployeeDao.addEmploye(employee);
        if (!res) {
        } else {
            employeeList = new ArrayList<Employee>();
            employeeList = EmployeeDao.list();
            resetEmployee();
        }
    }
    
    public void deleteEmployee(Employee emp){
        emp.setDepartment(null);
        EmployeeDao.deleteEmploye(emp);
        employeeList = EmployeeDao.list();
    }
    
    public void updateEmployee(Employee emp){
        Department dep = DepartmentDao.getById(departmentId);
        emp.setDepartment(dep);
        System.err.println(departmentId);
        EmployeeDao.updateEmploye(emp);
        employeeList = EmployeeDao.list();
    }
    
    public String login(String email, String password){
        resetEmployee();
        currentUser = EmployeeDao.login(email, password);
        if(currentUser == null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "wrong username/password!"));
            return "login.xhtml";
        }
        else
            switch(currentUser.getRole()){
                    case Normal:
                        return "xhtml/e-vacations.xhtml";
                    case DepartmentManager:
                        return "xhtml/d-vacations.xhtml";
                    case CompanyManager:
                        return "xhtml/c-vacations.xhtml";
                    case Administrator:
                        return "xhtml/e-dashboard.xhtml";
                    default:
                        return "login.xhtml";
            }
    }
    
    public String logout(){
        currentUser = null;
        return "login.xhtml";
    }
    
    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    public Employee getCurrentUser() {
        return currentUser;
    }
    
    public Employee.UserRoles[] getRoles(){
        return Employee.UserRoles.values();
    }

    public long getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(long departmentId) {
        this.departmentId = departmentId;
    }

    private void resetEmployee(){
        employee.setName(null);
        employee.setEmail(null);
        employee.setPassword(null);
        employee.setAddress(null);
        employee.setRole(null);
        employee.setSalary(0);
        employee.setDepartment(null);
        this.departmentId = 0;
    }
}
