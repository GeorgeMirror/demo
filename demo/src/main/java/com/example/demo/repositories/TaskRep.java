package com.example.demo.repositories;


import com.example.demo.db.Task;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRep extends CrudRepository<Task, Long> {
}
