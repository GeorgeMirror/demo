package com.example.demo.hibernate;

import com.example.demo.db.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Config {
    private static SessionFactory sessionFactory;
    private Config(){}
    public static void getSessionFactory(){
        Configuration con = new Configuration().configure();
        con.addAnnotatedClass(Task.class);
        StandardServiceRegistryBuilder sBuilder;
        sBuilder = new StandardServiceRegistryBuilder().applySettings(con.getProperties());
        sessionFactory = con.buildSessionFactory(sBuilder.build());
    }
    public static Session getSession(){
        if (sessionFactory==null) {
            getSessionFactory();
        }
        return sessionFactory.openSession();
    }
}

