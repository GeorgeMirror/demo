package com.example.demo.api;

import java.time.LocalDateTime;

public class RequestData {
    private String name;
    private String description;
    private String date;
    private String id;

    public RequestData(String name, String description, String date, String id) {
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

    public String getDate() {
        return date;
    }

    public String getId() {
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
