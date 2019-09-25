package com.muban.demo.controller;

import com.muban.demo.domain.UserName;
import com.muban.demo.domain.UserNameMapper;

import com.muban.demo.mapper.HelloMapper;

import com.muban.demo.util.DateUtil;
import com.muban.demo.util.HttpSimpleClientUtil;
import com.muban.demo.util.JSONResponse;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;



@Controller
public class HellWorld {
@Autowired

private UserNameMapper userNameMapper;
@Autowired
private HelloMapper helloMapper;
    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "helloworld";
    }

    @RequestMapping("/test")
    @ResponseBody
    public JSONResponse test(){
       JSONResponse response=JSONResponse.newResponse().addData("test");
        return response;
    }

    @RequestMapping("/testjson")
    @ResponseBody
    public JSONResponse testjson(){
        HashMap<String,String> map=new HashMap<>();
        map.put("ceshi","1");
        map.put("user","fangjian");
        JSONResponse response=JSONResponse.newResponse().addData(map);
        return response;
    }

    @RequestMapping("/test11")
    @ResponseBody
    public JSONResponse test11(String name){
      UserName u=userNameMapper.findByName(name);
       JSONResponse response=JSONResponse.newResponse().addData(u);
        return response;
    }



    @RequestMapping("/test22")
    @ResponseBody
    public JSONResponse test22(String name){
        UserName u=helloMapper.getByName(name);
        JSONResponse response=JSONResponse.newResponse().addData(u);
        return response;
    }


    @RequestMapping("/test33")
    @ResponseBody
    public String test33(int number,int id){
        helloMapper.addNumber(number,id);
       String s=JSONResponse.newResponse().getMsg();
        return s;
    }


    @RequestMapping("/testinsert")
    @ResponseBody
    public String testinsert(Long id,String name,int age){
        helloMapper.addUserName( id, name, age);
        String s=JSONResponse.newResponse().getMsg();
        return s;
    }

    @RequestMapping("/testadd")
    @ResponseBody
    public String testadd(String name,int age){
        helloMapper.add( name, age);
        String s=JSONResponse.newResponse().getMsg();
        return s;
    }

    @RequestMapping("/testupdate")
    @ResponseBody
    public String testupdate(UserName userName){
        helloMapper.updateUser(userName);
        String s=JSONResponse.newResponse().getMsg();
        return s;
    }

    @RequestMapping("/testexchange")
    @ResponseBody
    public String testexchange(String old,String newid){
        userNameMapper.change("1","2");
        String s=JSONResponse.newResponse().getMsg();
        return s;
    }


    @RequestMapping("/testselect")
    @ResponseBody
    public JSONResponse testselect(String old){
       List<UserName> user=userNameMapper.selectById(old);
        JSONResponse response=JSONResponse.newResponse().addData(user);
        return response;
    }


    @RequestMapping("/testselectall")
    @ResponseBody
    public JSONResponse testselectall(String old){
        List<UserName> userNameList=userNameMapper.selectALLById(old);
        UserName userName=userNameList.get(userNameList.size()-1);
        JSONResponse response=JSONResponse.newResponse().addData(userName);
        return response;
    }


    @RequestMapping("/testselectall88")
    @ResponseBody
    public JSONResponse testselectall1(String name){
//        Map<String, String> systemParamMap5 = new HashMap<String, String>();
//        systemParamMap5.put("access_token", "bff74ff8-bbec-4699-bc4c-529801aefcb4");
//        systemParamMap5.put("app_key", "edb6c3b9ac4847e7584c38e2b630b14f");
//        systemParamMap5.put("v", "1.0");
//        systemParamMap5.put("method", "kaola.common.countries.get");
//        systemParamMap5.put("timestamp", DateUtil.long2StandStr(System.currentTimeMillis()));
//        try {
//            System.out.println(joinWithSortAndSecretone(systemParamMap5));
//            String sign = DigestUtils.md5Hex(joinWithSortAndSecretone(systemParamMap5)).toUpperCase();
//            systemParamMap5.put("sign", sign);
//            System.out.println(sign);
//            String code = HttpSimpleClientUtil.httpPost("http://openapi-test.kaola.com/router", systemParamMap5, 1000, 1000);
//            System.out.println(code);
        Map<String, String> fieldMap = new HashMap<String, String>();
        fieldMap.put("access_token", "bff74ff8-bbec-4699-bc4c-529801aefcb4");
        fieldMap.put("app_key", "edb6c3b9ac4847e7584c38e2b630b14f");
        fieldMap.put("v", "1.0");
        fieldMap.put("method", "kaola.item.addPart");
        fieldMap.put("timestamp", DateUtil.long2StandStr(System.currentTimeMillis()));
        System.out.println(DateUtil.long2StandStr(System.currentTimeMillis()));
        fieldMap.put("name", "ggg");
        fieldMap.put("sub_title", "99");
        fieldMap.put("short_title", "99");
        fieldMap.put("ten_words_desc", "99");
        fieldMap.put("item_NO", "9999");
        fieldMap.put("brand_id", "1022");
        fieldMap.put("category_id", "33710");
        fieldMap.put("original_country_code_id", "100");
        fieldMap.put("gross_weight", "1.23");
        fieldMap.put("item_outer_id", "1234");
//		fieldMap.put("order_id", "11111");
//        String param_sign = sign(fieldMap,null,"8200ee92ec22fcae76e2f00bc5c79247188e0593");
        System.out.println(joinWithSortAndSecretone(fieldMap));
        String param_sign = DigestUtils.md5Hex(joinWithSortAndSecretone(fieldMap)).toUpperCase();
        fieldMap.put("sign", param_sign);
        System.out.println(param_sign);
        try {
            String result = HttpSimpleClientUtil.httpPost("http://openapi-test.kaola.com/router?", fieldMap, 1000, 1000);
            System.out.println(result);
        System.out.println(">>>>>>>>>>>>>>");
            helloMapper.updateUserCode(result,name);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new JSONResponse();
    }



    public static String joinWithSortAndSecretone(Map<String, String> params) {
        Set<String> keySetCopy = params.keySet();
        String[] keyArray = keySetCopy.toArray(new String[keySetCopy.size()]);
        Arrays.sort(keyArray, String.CASE_INSENSITIVE_ORDER);

        StringBuilder sb = new StringBuilder("8200ee92ec22fcae76e2f00bc5c79247188e0593");
        for (String key : keyArray) {
//			if("image_urls".equalsIgnoreCase(key)) {
//				continue;
//			}
            sb.append(key).append(params.get(key));
        }
        sb.append("8200ee92ec22fcae76e2f00bc5c79247188e0593");
        return sb.toString();
    }


    public static void main(String[] args) {
        List<String> list=new ArrayList<>();
        list.add("1");
        list.add("2");
        Iterator<String> lists=list.iterator();
//        while(lists.hasNext()){
//            String item=lists.next();
//            if("1".equalsIgnoreCase(item)){
//               lists.remove();
//               System.out.println(item);
//            }
//        }
//        for(String s:list){
//            if("2".equals(s)){
//                list.remove(s);
//            }
//        }
//        System.out.println(list);



        HashMap<String,String> map=new HashMap<>();
        map.put("1","1");
        map.put("2","2");
        map.put("3","3");
        System.out.println(map.entrySet());
    }
}
