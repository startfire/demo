package com.muban.demo.controller;

import com.muban.demo.model.User;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
    public static void main(String[] args) {
        String s="WX1994";
        System.out.println(s.substring(2,s.length()));
        Date date = new Date(1562193726000L);
        System.out.println(date);

        User user=new User();
        if(user.getUsername()==null){
            System.out.println("为空");
        }
    }
  public static Date strToDateLong(String strDate) {
           SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
           ParsePosition pos = new ParsePosition(0);
           Date strtodate = formatter.parse(strDate, pos);
           return strtodate;
        }
}
