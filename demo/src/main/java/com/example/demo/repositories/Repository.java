package com.example.demo.repositories;

import com.example.demo.db.Task;

import java.util.List;

public interface Repository {
    String create(Task task);
    String update(Task task);
    String delete(int id);
    List<Task> show();
    Task show(int id);
    void produce();
    String produce(int i);
}
