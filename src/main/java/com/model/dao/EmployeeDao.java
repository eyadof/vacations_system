/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.model.dao;

import com.model.entity.Employee;

import org.hibernate.HibernateException;
import org.hibernate.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;
import com.view.util.HibernateUtil;
import java.util.List;
import org.hibernate.CacheMode;

public class EmployeeDao {

    public static boolean addEmploye(Employee e) {
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.merge(e);
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

    public static boolean deleteEmploye(Employee e) {
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.delete(e);
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

    public static boolean updateEmploye(Employee e) {
        boolean result = false;
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.update(e);
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

    public static List<Employee> list() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);
        Transaction tx = null;

        List<Employee> result = null;
        try {
            tx = session.beginTransaction();
            result = session.createQuery("FROM Employee").list();
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

    public static Employee login(String email, String password) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.setCacheMode(CacheMode.IGNORE);
        Transaction tx = null;

        Employee result = null;
        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM Employee where email = :email and password = :password");
            q.setParameter("email", email);
            q.setParameter("password", password);
            result = (Employee) q.list().get(0);
            tx.commit();
        } catch (Exception ex) {
            if (tx != null) {
                tx.rollback();
            }
            ex.printStackTrace();
        } finally {
            session.close();
        }

        return result;
    }

    public static Employee getById(long id) {
        Employee result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            result = (Employee) session.get(Employee.class, id);
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

    public static List<Employee> getByDep(long id) {
        List<Employee> result = null;
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            Query q = session.createQuery("FROM Employee  Where department.id = :dep_id AND role = 'Normal'");
            q.setParameter("dep_id", id);
            result = q.list();
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
