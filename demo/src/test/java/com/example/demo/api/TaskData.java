package com.example.demo.api;

import java.time.LocalDateTime;

public class TaskData {
    private String name;
    private String description;
    private LocalDateTime date;
    private Integer id;

    public TaskData(String name, String description, LocalDateTime date, Integer id) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Integer getId() {
        return id;
    }

    @Override
    public String toString() {
        return "TaskData{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", id=" + id +
                '}';
    }
}
