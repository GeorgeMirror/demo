package com.example.demo.services;

import com.example.demo.db.Task;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class H2TaskServiceTest {
    TaskService taskService = new TaskService();
    @Order(1)
    @Test
    void show_error() {
        taskService.modeH2();
        List<Task> list = taskService.show();
        assertThat(list.isEmpty()).isEqualTo(true);
    }

    @Test
    void create_success() {
        taskService.modeH2();
        Task requestTask = new Task("name","description",1000);
        String res = taskService.create(requestTask);
        assertThat(res).isEqualTo("ок");
    }

    @Test
    void create_error() {
        taskService.modeH2();
        Task requestTask = new Task("name","description",1);
        taskService.create(requestTask);
        String res = taskService.create(requestTask);
        assertThat(res).isEqualTo("Такой id уже есть в базе. Воспользуйтесь изменением.");
    }

    @Test
    void create_wrong_id() {
        taskService.modeH2();
        Task requestTask = new Task("name","description",-5);
        String res = taskService.create(requestTask);
        assertThat(res).isEqualTo("Плохой id");
    }

    @Test
    void update_success() {
        taskService.modeH2();
        Task requestTask = new Task("name","description",1);
        taskService.create(requestTask);
        requestTask.setName("name1");
        String res = taskService.update(requestTask);
        assertThat(res).isEqualTo("ок");
    }

    @Test
    void update_error() {
        taskService.modeH2();
        Task requestTask = new Task("name","description",1);
        taskService.create(requestTask);
        Task requestTask1 = new Task("name1", "description1", 99);
        String res = taskService.update(requestTask1);
        assertThat(res).isEqualTo("Нет такого id в базе данных. Воспользуйтесь созданием.");
    }

    @Test
    void delete_success() {
        taskService.modeH2();
        Task requestTask = new Task("name","description",1);
        taskService.create(requestTask);
        String res = taskService.delete(1);
        assertThat(res).isEqualTo("ок");
    }
    @Test
    void delete_error() {
        taskService.modeH2();
        String res = taskService.delete(2);
        assertThat(res).isEqualTo("Нет такого id в базе данных");
    }

    @Test
    void show_success() {
        taskService.modeH2();
        Task requestTask = new Task("name","description",1);
        taskService.create(requestTask);
        List<Task> list = taskService.show();
        assertThat(list.get(0).getId()).isEqualTo(requestTask.getId());
    }

    @Test
    void produce_error() {
        taskService.modeH2();
        String res = taskService.produce(-10);
        assertThat(res).isEqualTo("Невозможно создать менее одного эдемента");
    }

    @Test
    void produce_success() {
        taskService.modeH2();
        String res = taskService.produce(10);
        assertThat(res).isEqualTo("ок");
    }
    @Test
    void produce_many() {
        taskService.modeH2();
        String res = taskService.produce(5000);
        assertThat(res).isEqualTo("Введено количество более 500, будет создано 500");
    }
}
