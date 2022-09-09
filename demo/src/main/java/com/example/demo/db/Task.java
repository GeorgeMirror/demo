package com.example.demo.db;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table (name = "TASKS")
public class Task {
    @Column(name = "task_name")
    private String name;
    @Column(name = "task_description")
    private String description;
    @Column(name = "task_date")
    private LocalDateTime date;
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    //Конструктор
    public Task() {
    }
    public Task(String name, String description, int id){
        if (!("".equals(name))&&!("".equals(description))){
            setName(name);
            setDescription(description);
            setDate();
            setId(id);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate() {
        this.date = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", id=" + id +
                '}';
    }
}
