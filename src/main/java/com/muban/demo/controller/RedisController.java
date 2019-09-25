package com.muban.demo.controller;

import com.muban.demo.model.User;
import com.muban.demo.util.RedisUtil2;
import net.sf.json.util.JSONUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author lijy
 * @create 2019-04-15 22:17
 * @description
 */
@RestController
public class RedisController {
//    @Autowired
//    private RedisUtils redisUtils;

    @Autowired
    private RedisUtil2 redisUtil2;

//    @GetMapping("test-set-string")
//    public String testSetString(String key, String value) {
//        redisUtils.set(key, value,60L);
//        return "success set string";
//    }
//
//    @GetMapping("test-get-string")
//    public String testGetString(String key) {
//        return redisUtils.getString(key);
//    }

    @GetMapping("test-set-string2")
    public String testSetString2(String key, String value) {
        redisUtil2.set(key, value,60L);
        return "success set string2";
    }

//    @GetMapping("test-get-string2")
//    public String testGetString2(String key) {
//        return redisUtils.getString(key);
//    }

    @GetMapping("test-set-obj")
    public String testSetObj() {
        User user = new User("hahaha", 20);
        redisUtil2.set(user.getUsername(), user);
        return "success set obj";
    }

    @GetMapping("test-get-obj")
    public String testGetObj(String key) {
        redisUtil2.set("99",99);
        redisUtil2.decr("99",-1);
        System.out.println(redisUtil2.get("99"));
        return "666";
    }


    @GetMapping("test-get-descobj")
    public Object testGetdescObj(String key) {
        return redisUtil2.get(key);
    }


    public static void main(String[] args) {
        Map<String,Long> map=new HashMap<>();
        map.put("1", (long) 1.99);
        map.put("2", (long) 2.99);
        map.put("3", (long) 3.99);
        List<String> list=new ArrayList<>();
        list.add("1");
        list.add("4");
       System.out.print(map.keySet().containsAll(list));
        System.out.print(list);
        List<String> collected = Stream.of("a", "b", "hello")
                .map(string -> string.toUpperCase())
                .collect(toList());
        System.out.println(collected);

        List<String> collecteds = Stream.of("a", "b", "c")
                .collect(Collectors.toList());
        System.out.println(collecteds);

    }


}
