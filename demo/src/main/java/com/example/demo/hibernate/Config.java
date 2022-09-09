package com.example.demo.hibernate;

import com.example.demo.db.Task;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class Config {
    private static Session session;
    private Config(){}
    public static Session getSession(){
        if (session==null){
            Configuration con = new Configuration().configure();
            con.addAnnotatedClass(Task.class);
            StandardServiceRegistryBuilder sBuilder;
            sBuilder = new StandardServiceRegistryBuilder()
                    .applySettings(con.getProperties());
            SessionFactory sf = con.buildSessionFactory(sBuilder.build());
            session = sf.openSession();
        }
        return session;
    }
}
