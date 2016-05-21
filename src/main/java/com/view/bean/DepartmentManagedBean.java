/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view.bean;

import com.model.entity.Department;
import com.model.dao.DepartmentDao;
import com.model.dao.EmployeeDao;
import com.model.entity.Employee;
import java.io.Serializable;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class DepartmentManagedBean implements Serializable {

    private Department department;
    private List<Department> departmentList;
    private long managerId;

    public DepartmentManagedBean() {
        department = new Department();
        departmentList = new ArrayList();
    }

    @PostConstruct
    public void init() {
        departmentList = DepartmentDao.list();
    }

    public void addDepartment() {
        Employee manager = EmployeeDao.getById(managerId);
        manager.setDepartment(department);
        manager.setRole(Employee.UserRoles.DepartmentManager);
        department.setManager(manager);
        boolean res = DepartmentDao.addDepartment(department);
        if (!res) {
            System.err.println("****************ERROR************");
        } else {
            departmentList = DepartmentDao.list();
            managerId = 0;
        }
    }

    public void updateDepartment(Department dep) {
        dep.getManager().setRole(Employee.UserRoles.Normal);
        EmployeeDao.updateEmploye(dep.getManager());
        Employee manager = EmployeeDao.getById(managerId);
        manager.setDepartment(dep);
        manager.setRole(Employee.UserRoles.DepartmentManager);
        dep.setManager(manager);
        boolean res = DepartmentDao.updateDepartment(dep);
        if (!res) {
            System.err.println("****************ERROR************");
        } else {
            departmentList = DepartmentDao.list();
            managerId = 0;
        }
    }

    public void deleteDepartment(Department dep) {
        dep.setManager(null);
        boolean res = DepartmentDao.deleteDepartment(dep);
        if (!res) {
            System.err.println("****************ERROR************");
        } else {
            departmentList = DepartmentDao.list();
        }
    }

    public List<Department> getDepartments() {
        return DepartmentDao.list();
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public List<Department> getDepartmentList() {
        return departmentList;
    }

    public void setDepartmentList(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    public long getManagerId() {
        return managerId;
    }

    public void setManagerId(long managerId) {
        this.managerId = managerId;
    }
}
