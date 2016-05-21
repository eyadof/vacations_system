/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view.bean;

/**
 *
 * @author eyadof
 */
import com.model.entity.Employee;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class AppRouter {
    public String goTo(Employee emp, String section) {
        switch (section) {
            case "employees-dashboard":
                if (emp.getRole() == Employee.UserRoles.Administrator) {
                    return "e-dashboard.xhtml";
                }
            case "departments-dashboard":
                if (emp.getRole() == Employee.UserRoles.Administrator) {
                    return "d-dashboard.xhtml";
                }
            case "vacations-dashboard":
                if (emp.getRole() == Employee.UserRoles.Administrator) {
                    return "v-dashboard.xhtml";
                }
            case "department-vacations":
                if (emp.getRole() == Employee.UserRoles.DepartmentManager) {
                    return "d-vacations.xhtml";
                }
            case "company-vacations":
                if (emp.getRole() == Employee.UserRoles.CompanyManager) {
                    return "c-vacations.xhtml";
                }
            case "personal-vacations":
                return "e-vacations.xhtml";
            default:
                return "login.xhtml";
        }
    }
}
