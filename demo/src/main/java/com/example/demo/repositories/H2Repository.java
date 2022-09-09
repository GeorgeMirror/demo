package com.example.demo.repositories;

import com.example.demo.db.Task;
import com.example.demo.hibernate.Config;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class H2Repository implements Repository{
    private static H2Repository instance;
    private H2Repository(){}
    public static H2Repository getInstance(){
        if (instance==null){
            instance = new H2Repository();
        }
        return instance;
    }

    @Override
    public String create(Task task) {
        Session session = Config.getSession();
        Transaction transaction = session.beginTransaction();
        Task task1 = session.find(Task.class, task.getId());
        if (task1!=null){
            transaction.commit();
            return "Такой id уже есть в базе. Воспользуйтесь изменением.";
        }
        session.merge(task);
        transaction.commit();
        session.close();
        return "ок";
    }

    @Override
    public String update(Task task) {
        Session session = Config.getSession();
        Transaction tr = session.beginTransaction();
        Task task1 = session.find(Task.class, task.getId());
        if (task1==null){
            return "Нет такого id в базе данных. Воспользуйтесь созданием.";
        }
        task1.setName(task.getName());
        task1.setDescription(task.getDescription());
        task1.setDate();
        tr.commit();
        session.close();
        return "ок";
    }

    @Override
    public String delete(int id) {
        Session session = Config.getSession();
        Transaction tr = session.beginTransaction();
        List<Task> list = session.createQuery("FROM Task", Task.class).list();
        for(Task x : list){
            if (x.getId()==id){
                session.delete(x);
                tr.commit();
                return "ок";
            }
        }
        tr.commit();
        session.close();
        return "Нет такого id в базе данных";
    }

    @Override
    public List<Task> show() {
        Session session = Config.getSession();
        Transaction tr = session.beginTransaction();
        List<Task> list = session.createQuery("FROM Task", Task.class).list();
        tr.commit();
        session.close();
        return list;
    }

    @Override
    public Task show(int id) {
        Session session = Config.getSession();
        Transaction tr =session.beginTransaction();
        Task task = session.find(Task.class, id);
        tr.commit();
        session.close();
        return task;
    }

    @Override
    public void produce() {
        produce(10);
    }

    @Override
    public String produce(int count) {
        Task task;
        for(int i = 0; i < count; i++){
            task = new Task ("Name " + i, "Description " + i, i);
            create(task);
        }
        return "ок";
    }
}
