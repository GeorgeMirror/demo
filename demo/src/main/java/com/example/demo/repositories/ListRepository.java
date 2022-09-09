package com.example.demo.repositories;

import com.example.demo.db.Task;
import org.springframework.transaction.annotation.Transactional;
import java.util.LinkedList;
import java.util.List;

@Transactional
public class ListRepository implements Repository {
    private List<Task> listDB = new LinkedList();

    private static ListRepository instance;
    private ListRepository(){}
    public static ListRepository getInstance(){
        if (instance==null){
            instance = new ListRepository();
        }
        return instance;
    }

    @Override
    public String create(Task task) {
        for(Task x : listDB){
            if(x.getId()==task.getId()){
                return "Такой id уже есть в базе. Воспользуйтесь изменением.";
            }
        }
        listDB.add(task);
        return "ок";
    }

    @Override
    public String update(Task task) {
        for(Task x : listDB){
            if(task.getId()==x.getId()){
                x.setName(task.getName());
                x.setDescription(task.getDescription());
                x.setDate();
                return "ок";
            }
        }
        return "Нет такого id в базе данных. Воспользуйтесь созданием.";
    }

    @Override
    public String delete(int id) {
        int i;
        for(i = 0;i< listDB.size();i++){
            if (id == listDB.get(i).getId()){
                break;
            }
        }
        if (i==listDB.size()){
            return "Нет такого id в базе данных";
        }
        listDB.remove(i);
        return "ок";
    }

    @Override
    public List<Task> show() {
        return listDB;
    }

    @Override
    public Task show(int id) {
        for(Task task : listDB){
            if (id==task.getId()){
                return task;
            }
        }
        return null;
    }

    @Override
    public void produce() {
        produce(10);
    }

    @Override
    public String produce(int count) {
        Task task;
        List<Task> list = new LinkedList<>();
        for(int i = 0; i < count; i++){
            task = new Task ("Name " + i, "Description " + i, i);
            list.add(task);
        }
        listDB = list;
        return "ок";
    }
}
