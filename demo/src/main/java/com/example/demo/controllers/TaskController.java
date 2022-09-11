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
    private volatile boolean f = true;
    @GetMapping("/list")
    public Object modeList(){
        return taskService.modeList();
    }
    @GetMapping("/h2")
    public Object modeH2(){
        return taskService.modeH2();
    }

    @PostMapping
    public Object createTask(@ModelAttribute("Task") Task task){
        while (!f){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        f = false;
        Object res =  taskService.create(task);
        f = true;
        return res;
    }

    @GetMapping("/{id}")
    public Object show(@PathVariable("id") int id) {
        while (!f){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        f = false;
        Object res = taskService.show(id);
        f = true;
        return res;
    }
    @GetMapping
    public Object show() {
        while (!f){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        f = false;
        Object res = taskService.show();
        f = true;
        return res;
    }
    @DeleteMapping("/{id}")
    public Object delete(@PathVariable("id") int id) {
        while (!f){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        f = false;
        Object res = taskService.delete(id);
        f = true;
        System.out.println(res);
        return res;
    }
    @PatchMapping
    public Object update(@ModelAttribute("Task") Task task){
        while (!f){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        f = false;
        Object res = taskService.update(task);
        f = true;
        return res;
    }
    @GetMapping("/produce")
    public Object produce(){
        while (!f){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        f = false;
        taskService.produce();
        f = true;
        return "ок";
    }
    @GetMapping("/produce_{i}")
    public Object produce(@PathVariable("i") int i){
        while (!f){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        f = false;
        Object res = taskService.produce(i);
        f = true;
        return res;
    }

    @GetMapping("/start")
    public void start(){
        while (!f){
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        f = false;
        taskService.start();
        f = true;
    }
}
