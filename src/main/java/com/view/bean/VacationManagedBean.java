/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.view.bean;

import com.model.entity.Vacation;
import com.model.dao.VacationDao;
import com.model.entity.Employee;
import java.io.Serializable;
import java.util.List;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.primefaces.push.EventBus;
import org.primefaces.push.EventBusFactory;

/**
 *
 * @author eyadof
 */
@ManagedBean
@SessionScoped
public class VacationManagedBean implements Serializable {

    private Vacation vacation;

    public VacationManagedBean() {
        vacation = new Vacation();
    }

    public void addVacation() {
        boolean res = VacationDao.addVacation(vacation);
        if (res) {
            //FacesContext.getCurrentInstance().;
            send();
        }
    }

    public List<Vacation> listDepartmentVacations(long department_id) {
        return VacationDao.listDepartmentVacations(department_id);
    }

    public List<Vacation> listCompanytVacations() {
        return VacationDao.listCompanyVacations();
    }

    public void dmAccept(Vacation v) {
        VacationDao.dmAccept(v);
    }

    public void dmReject(Vacation v) {
        VacationDao.dmReject(v);
    }

    public void gmAccept(Vacation v) {
        VacationDao.gmAccept(v);
    }

    public void gmReject(Vacation v) {
        VacationDao.gmReject(v);
    }

    private void send() {
        long managerId = vacation.getEmployee().getDepartment().getManager().getId();
        String message = String.format("Employee %s have just submitted a vacation for %s", vacation.getEmployee().getName(), vacation.getType().toString());
        EventBus eventBus = EventBusFactory.getDefault().eventBus();
        eventBus.publish("/notify", new FacesMessage(message));
    }

    public Vacation getVacation() {
        return vacation;
    }

    public void setVacation(Vacation vacation) {
        this.vacation = vacation;
    }

    public Vacation.VacationType[] getTypes() {
        return Vacation.VacationType.values();
    }
}
