package com.example.demo.api;

import com.example.demo.db.Task;
import com.example.demo.hibernate.Config;
import com.example.demo.services.TaskService;
import io.restassured.http.ContentType;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.Order;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static io.restassured.RestAssured.given;

public class ListTaskTest {
    TaskService taskService = new TaskService();
    private final static String URL = "http://localhost:8080/";
    @Test
    public void check_show_data_sorted_test(){
        taskService.modeList();
        //После запуска приложения и, желательно, создания нескольких строк в базе
        List<TaskData> tasks = given()
                .when()
                .contentType(ContentType.JSON)
                .get(URL)
                .then().log().all()
                .extract().body().jsonPath().getList("", TaskData.class);
        Session session = Config.getSession();
        Transaction tr = session.beginTransaction();
        List<Task> list = session.createQuery("FROM Task", Task.class).list();
        tr.commit();
        session.close();
        Collections.sort(list, (o1, o2) -> {
            if(o1.getDate().isBefore(o2.getDate())){
                return 1;
            }
            if(o1.getDate().isAfter(o2.getDate())){
                return -1;
            }
            return 0;
        });
        boolean equalsLists = true;
        while(!list.isEmpty()){
            Task task = list.get(0);
            if(!tasks.isEmpty()){
                TaskData taskData = tasks.get(0);
                if ((task.getId()==taskData.getId())
                    &&(task.getDate().equals(taskData.getDate()))
                    &&(task.getName().equals(taskData.getName()))
                    &&(task.getDescription().equals(taskData.getDescription()))
                ){
                    list.remove(0);
                    tasks.remove(0);
                }else{
                    equalsLists = false;
                    break;
                }
            }else{
                equalsLists = false;
                break;
            }
        }
        Assert.assertTrue(equalsLists);
    }
    @Order(1)
    @Test
    public void create_success(){
        taskService.modeList();
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(URL);

        List<NameValuePair> params = new ArrayList<NameValuePair>(4);
        params.add(new BasicNameValuePair("name", "Test"));
        params.add(new BasicNameValuePair("description", "Test description!"));
        params.add(new BasicNameValuePair("date", LocalDateTime.now().toString()));
        params.add(new BasicNameValuePair("id", "1999"));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        try {
            Assert.assertTrue(EntityUtils.toString(entity).equals("ок"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Order(2)
    @Test
    public void create_same_obj(){
        taskService.modeList();
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(URL);

        List<NameValuePair> params = new ArrayList<NameValuePair>(4);
        params.add(new BasicNameValuePair("name", "Test"));
        params.add(new BasicNameValuePair("description", "Test description!"));
        params.add(new BasicNameValuePair("date", LocalDateTime.now().toString()));
        params.add(new BasicNameValuePair("id", "1999"));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        try {
            Assert.assertTrue(EntityUtils.toString(entity).equals("Такой id уже есть в базе. Воспользуйтесь изменением."));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void create_negative_obj(){
        taskService.modeList();
        HttpClient httpclient = HttpClients.createDefault();
        HttpPost httppost = new HttpPost(URL);

        List<NameValuePair> params = new ArrayList<NameValuePair>(4);
        params.add(new BasicNameValuePair("name", "Test"));
        params.add(new BasicNameValuePair("description", "Test description!"));
        params.add(new BasicNameValuePair("date", LocalDateTime.now().toString()));
        params.add(new BasicNameValuePair("id", "-9"));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        HttpResponse response = null;
        try {
            response = httpclient.execute(httppost);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        try {
            Assert.assertTrue(EntityUtils.toString(entity).equals("Плохой id"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Order(3)
    @Test
    public void delete_success(){
        taskService.modeList();
        HttpClient httpClient = HttpClients.createDefault();
        HttpDelete httpDelete = new HttpDelete(URL+"/1999");

        HttpResponse response = null;
        try {
            response = httpClient.execute(httpDelete);
        } catch (IOException e) {
            e.printStackTrace();
        }
        HttpEntity entity = response.getEntity();
        try {
            Assert.assertTrue(EntityUtils.toString(entity).equals("ок"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
