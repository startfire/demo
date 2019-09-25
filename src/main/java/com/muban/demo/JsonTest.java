package com.muban.demo;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.muban.demo.model.Course;
import com.muban.demo.model.Person;
import com.muban.demo.model.Student;
import com.muban.demo.model.Teacher;

import java.util.*;

public class JsonTest {
    public static void main(String[] args) {
        //json字符串-简单对象型
        String JSON_OBJ_STR = "{\"studentName\":\"lily\",\"studentAge\":12}";
//json字符串-数组类型
        String JSON_ARRAY_STR = "[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]";
//复杂格式json字符串
        String COMPLEX_JSON_STR = "{\"teacherName\":\"crystall\",\"teacherAge\":27,\"course\":{\"courseName\":\"english\",\"code\":1270},\"students\":[{\"studentName\":\"lily\",\"studentAge\":12},{\"studentName\":\"lucy\",\"studentAge\":15}]}";

        JSONObject jsonObject = JSON.parseObject(JSON_OBJ_STR);
        //JSONObject jsonObject1 = JSONObject.parseObject(JSON_OBJ_STR); //因为JSONObject继承了JSON，所以这样也是可以的
        System.out.println(jsonObject.getString("studentName") + ":" + jsonObject.getInteger("studentAge"));


/**
 * json字符串-数组类型与JSONArray之间的转换
 */

        JSONArray jsonArray = JSON.parseArray(JSON_ARRAY_STR);
        //JSONArray jsonArray1 = JSONArray.parseArray(JSON_ARRAY_STR);//因为JSONArray继承了JSON，所以这样也是可以的
        //遍历方式1
        int size = jsonArray.size();
        for (int i = 0; i < size; i++) {
            JSONObject jsonObject1 = jsonArray.getJSONObject(i);
            System.out.println(jsonObject1.getString("studentName") + ":" + jsonObject.getInteger("studentAge"));
        }
        //遍历方式2
        for (Object obj : jsonArray) {
            JSONObject jsonObject2 = (JSONObject) obj;
            System.out.println(jsonObject2.getString("studentName") + ":" + jsonObject.getInteger("studentAge"));


/**
 * 复杂json格式字符串与JSONObject之间的转换
 */
            JSONObject jsonObjectx = JSON.parseObject(COMPLEX_JSON_STR);
            //JSONObject jsonObject1 = JSONObject.parseObject(COMPLEX_JSON_STR);//因为JSONObject继承了JSON，所以这样也是可以的

            String teacherName = jsonObjectx.getString("teacherName");
            Integer teacherAge = jsonObjectx.getInteger("teacherAge");
            JSONObject course = jsonObjectx.getJSONObject("course");
            JSONArray students = jsonObjectx.getJSONArray("students");

System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>");

            Student student = JSON.parseObject(JSON_OBJ_STR, new TypeReference<Student>() {});
            //Student student1 = JSONObject.parseObject(JSON_OBJ_STR, new TypeReference<Student>() {});//因为JSONObject继承了JSON，所以这样也是可以的

            System.out.println(student.getStudentName()+":"+student.getStudentage());


            ArrayList<Student> studentss= JSON.parseObject(JSON_ARRAY_STR, new TypeReference<ArrayList<Student>>() {});
            //ArrayList<Student> students1 = JSONArray.parseObject(JSON_ARRAY_STR, new TypeReference<ArrayList<Student>>() {});//因为JSONArray继承了JSON，所以这样也是可以的
            for (Student student1 : studentss) {
                System.out.println(student1.getStudentName()+":"+student1.getStudentage());
            }



            Teacher teacher = JSON.parseObject(COMPLEX_JSON_STR, new TypeReference<Teacher>() {});
            //Teacher teacher1 = JSON.parseObject(COMPLEX_JSON_STR, new TypeReference<Teacher>() {});//因为JSONObject继承了JSON，所以这样也是可以的
            String teacherNames = teacher.getTeacherName();
            Integer teacherAges = teacher.getTeacherAge();
            Course courses = teacher.getCourse();
            List<Student> studentsss = teacher.getStudents();
            System.out.println(teacherNames);
            System.out.println(teacherAges);
            System.out.println(courses);
            System.out.println(">>>>>>>>");
            for(  Student s:studentsss){
            System.out.println(s);
            }
        }





        ArrayList<Person> array = new ArrayList<Person>();

        Person p1 = new Person("Tom1");
        Person p2 = new Person("Tom2");
        Person p3 = new Person("Tom3");
        Person p4 = new Person("Tom4");

        array.add(p1);
        array.add(p2);
        array.add(p3);
        array.add(p4);

        Iterator<Person> iterator = array.iterator();


        System.out.println("\r\n" + "-----利用Lambda表达式的foreach-----" + "\r\n");
        array.forEach(obj -> System.out.println(obj.getName()));

        while(iterator.hasNext()){
            System.out.println(iterator.next().getName()); //输出的是wang，而不是tom
        }

        Map map = new HashMap();
        for(int i = 0; i < 10; i ++){
            map.put(i, String.valueOf("map" + i));
        }
        Iterator iterMap = map.entrySet().iterator();
        while(iterMap.hasNext()){
            Map.Entry strMap = (Map.Entry)iterMap.next();
            System.out.println("key为:" + strMap.getKey() +
                    ", value为:" + strMap.getValue());
        }


    }





}