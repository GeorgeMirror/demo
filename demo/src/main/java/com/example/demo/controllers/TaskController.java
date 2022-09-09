package com.example.demo.controllers;

import com.example.demo.db.Task;
import com.example.demo.services.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "", produces = "application/json")
public class TaskController {
    @Autowired
    TaskService taskService;
    @GetMapping("/list")
    public Object modeList(){
        return taskService.modeList();

    }
    @GetMapping("/h2")
    public Object modeH2(){
        return taskService.modeH2();
    }

    @PostMapping
    public Object createTask(@RequestBody final Task task){
        return taskService.create(task);
    }

    @GetMapping("/{id}")
    public Object show(@PathVariable("id") int id) {
        return taskService.show(id);
    }
    @GetMapping
    public Object show() {
        return taskService.show();
    }
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") int id) {
        return taskService.delete(id);
    }
    @PatchMapping("/{id}")
    public Object update(@ModelAttribute("task") Task task){
        return taskService.update(task);
    }
    @GetMapping("/produce")
    public Object produce(){
        taskService.produce();
        return "ок";
    }
    @GetMapping("/produce_{i}")
    public Object produce(@PathVariable("i") int i){
        return taskService.produce(i);
    }

    @GetMapping("/start")
    public void start(){
        taskService.start();
    }
}
