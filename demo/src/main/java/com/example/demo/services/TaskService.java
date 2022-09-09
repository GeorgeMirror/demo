package com.example.demo.services;

import com.example.demo.db.Task;
import com.example.demo.repositories.H2Repository;
import com.example.demo.repositories.ListRepository;
import com.example.demo.repositories.Repository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
@Service
public class TaskService {
    Repository repository;

    public String modeList(){
        repository = ListRepository.getInstance();
        return "ok";
    }
    public String modeH2(){
        repository = H2Repository.getInstance();
        return "ok";
    }

    public String create(Task task){
        if (task.getId()<0){
           return "Плохой id";
        }
        return repository.create(task);
    }

    public String update(Task task){
        return repository.update(task);
    }

    public String delete(int id){
        return repository.delete(id);
    }

    public List<Task> show(){
        List<Task> list = repository.show();
        Collections.sort(list, (o1, o2) -> {
            if(o1.getDate().isBefore(o2.getDate())){
                return 1;
            }
            if(o1.getDate().isAfter(o2.getDate())){
                return -1;
            }
            return 0;
        });
        return list;
    }

    public Object show(int id){
        Task task = repository.show(id);
        if (task==null){
            return "Нет элемента с таким индексом в базе данных";
        }
        return task;
    }
    public void produce(){
        repository.produce();
    }
    public String produce(int i){
        if (i<1){
            return "Невозможно создать менее одного эдемента";
        }
        if (i>500){
            repository.produce(500);
            return "Введено количество более 500, будет создано 500";
        }
        return repository.produce(i);
    }

    public void start() {
        produce();
        System.out.println(show());
        delete(3);
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Task task = new Task("Дела", "Делать дела", 1);
        update(task);
        System.out.println(show());
    }
}
