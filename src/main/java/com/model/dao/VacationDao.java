/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.dao;

import com.model.entity.Vacation;
import org.hibernate.HibernateException;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.view.util.HibernateUtil;
import java.util.List;
import org.hibernate.CacheMode;
/**
 *
 * @author eyadof
 */
public class VacationDao {
    public static boolean addVacation(Vacation v){
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            session.save(v);
            tx.commit();
            result = true;
        }
        catch(HibernateException ex){
            if (tx != null) {
                tx.rollback();
                result = false;
            }
            ex.printStackTrace();
        }
        finally{
            session.close();
        }
        return result;
    }
    
    public static List<Vacation> listDepartmentVacations(long department_id){
        List<Vacation> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            System.err.println(department_id);
            Query q = session.createQuery("FROM Vacation  Where employee.department.id = :dep_id AND status = 'Pending'");
            q.setParameter("dep_id", department_id);
            result = q.list();
            tx.commit();
        }
        catch(HibernateException ex){
            if (tx != null) 
                tx.rollback();
            ex.printStackTrace();
        }
        finally{
            session.close();
        }
        return result;
    }
    
    public static boolean dmReject(Vacation v){
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            v.setStatus(Vacation.VacationStatus.RejectedByDepartmentManager);
            session.update(v);
            tx.commit();
            result = true;
        }
        catch(HibernateException ex){
            if (tx != null) {
                tx.rollback();
                result = false;
            }
            ex.printStackTrace();
        }
        finally{
            session.close();
        }
        return result;
    }
    
    public static boolean dmAccept(Vacation v){
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            v.setStatus(Vacation.VacationStatus.AcceptedByDepartmentManager);
            session.update(v);
            tx.commit();
            result = true;
        }
        catch(HibernateException ex){
            if (tx != null) {
                tx.rollback();
                result = false;
            }
            ex.printStackTrace();
        }
        finally{
            session.close();
        }
        return result;
    }
    
    public static List<Vacation> listCompanyVacations(){
        List<Vacation> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            result = session.createQuery("FROM Vacation  Where Status = 'AcceptedByDepartmentManager'").list();
            tx.commit();
        }
        catch(HibernateException ex){
            if (tx != null) 
                tx.rollback();
            ex.printStackTrace();
        }
        finally{
            session.close();
        }
        return result;
    }
    
    public static boolean gmReject(Vacation v){
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            v.setStatus(Vacation.VacationStatus.RejectedByCompanyManager);
            session.update(v);
            tx.commit();
            result = true;
        }
        catch(HibernateException ex){
            if (tx != null) {
                tx.rollback();
                result = false;
            }
            ex.printStackTrace();
        }
        finally{
            session.close();
        }
        return result;
    }
    
    public static boolean gmAccept(Vacation v){
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);
        Transaction tx = null;
        
        try{
            tx = session.beginTransaction();
            v.setStatus(Vacation.VacationStatus.Accepted);
            session.update(v);
            tx.commit();
            result = true;
        }
        catch(HibernateException ex){
            if (tx != null) {
                tx.rollback();
                result = false;
            }
            ex.printStackTrace();
        }
        finally{
            session.close();
        }
        return result;
    }
}
