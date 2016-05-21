/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;
import static javax.persistence.EnumType.STRING;
import javax.persistence.Enumerated;
import javax.persistence.Temporal;

/**
 *
 * @author eyadof
 */
@Entity
@Table(name = "vacations")
public class Vacation implements Serializable {

    public static enum VacationType {
        Holiday,
        Mortal,
        Birth,
        Ill
    }

    public static enum VacationStatus {
        Pending,
        AcceptedByDepartmentManager,
        Accepted,
        RejectedByDepartmentManager,
        RejectedByCompanyManager
    }
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Basic(optional = false)
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date requestedAt;

    @Basic(optional = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date startsFrom;

    @Basic(optional = false)
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date endsAt;

    @Basic(optional = false)
    @Enumerated(STRING)
    private VacationStatus status;

    @Basic(optional = false)
    @Enumerated(STRING)
    private VacationType type;

    @ManyToOne(cascade = {CascadeType.REFRESH})
    private Employee employee;

    public Vacation() {
        status = VacationStatus.Pending;
        requestedAt = new Date();
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getRequestedAt() {
        return requestedAt;
    }

    public void setRequestedAt(Date requestedAt) {
        this.requestedAt = requestedAt;
    }

    public Date getStartsFrom() {
        return startsFrom;
    }

    public void setStartsFrom(Date startsFrom) {
        this.startsFrom = startsFrom;
    }

    public Date getEndsAt() {
        return endsAt;
    }

    public void setEndsAt(Date endsAt) {
        this.endsAt = endsAt;
    }

    public VacationStatus getStatus() {
        return status;
    }

    public void setStatus(VacationStatus status) {
        this.status = status;
    }

    public VacationType getType() {
        return type;
    }

    public void setType(VacationType type) {
        this.type = type;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    @Override
    public String toString() {
        return String.format("%s[id=%d]", getClass().getSimpleName(), getId());
    }
}
