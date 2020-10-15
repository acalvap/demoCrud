/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dao;

import com.model.pojo.TktTickets;
import com.util.HibernateUtil;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author anthony.calva
 */
public class TktTicketsDAO {
    
    //Session session;
    public List<TktTickets> AllUsers() {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        List<TktTickets> daoAllTickets = null;
        try {
            ses.beginTransaction();
            daoAllTickets = ses.createCriteria(TktTickets.class).list();
            int count = daoAllTickets.size();
            ses.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            ses.getTransaction().rollback();
        }
        ses.close();
        return daoAllTickets;
    }
    
    public Integer getId() {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        String hql = "select max(codigoTicket) from TktTickets";
        Query q = ses.createQuery(hql);
        List<Integer> results = q.list();
        Integer userId = 1;
        if (results.get(0) != null) {
            userId = results.get(0) + 1;
        }
        ses.flush();
        ses.close();
        return userId;
    }
    
    public List<TktTickets> SearchByCodigoTicket(Integer codigoTicket) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        List<TktTickets> daoSearchList = null;
        try {
            ses.beginTransaction();
            Query q = ses.createQuery("from TktTickets where codigoTicket = :codigoTicket");
            q.setParameter("codigoTicket", codigoTicket);
            daoSearchList = q.list();
            int count = daoSearchList.size();
            ses.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            ses.getTransaction().rollback();
        } finally {
            ses.close();
        }
        return daoSearchList;
    }
    
    public void add(TktTickets tickets) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        try {
            ses.beginTransaction();
            ses.merge(tickets);
            ses.flush();
            ses.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            ses.getTransaction().rollback();
        }
        ses.close();
    }
    
    public void delete(TktTickets tickets) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        try {
            ses.beginTransaction();
            ses.delete(tickets);
            ses.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            ses.getTransaction().rollback();
        }
        ses.close();
    }
    
    public void update(TktTickets user) {
        Session ses = HibernateUtil.getSessionFactory().openSession();
        try {
            ses.beginTransaction();
            ses.update(user);
            ses.flush();
            ses.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            ses.getTransaction().rollback();
        }
        ses.close();
    }
    
}
