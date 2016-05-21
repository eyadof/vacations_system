package com.model.dao;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import com.model.entity.Department;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.StatelessSession;
import org.hibernate.Transaction;
import com.view.util.HibernateUtil;
import java.util.List;
import org.hibernate.CacheMode;

public class DepartmentDao {

    public static boolean addDepartment(Department d) {
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.save(d);
            tx.commit();
            result = true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
                result = false;
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    public static boolean updateDepartment(Department d) {
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(d);
            tx.commit();
            result = true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
                result = false;
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    public static boolean deleteDepartment(Department d) {
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.delete(d);
            tx.commit();
            result = true;
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
                result = false;
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    public static List<Department> list() {
        List<Department> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            result = session.createQuery("from com.model.entity.Department").list();
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }

    public static Department getById(long id) {
        Department result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            result = (Department) session.get(Department.class, id);
            tx.commit();
        } catch (HibernateException ex) {
            if (tx != null) {
                tx.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }
        return result;
    }
}
