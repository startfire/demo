package com.muban.demo.controller;

import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

public class AA {

    public static final String Constapp_key="3353724356596479603";
    public static final String Constapp_secret="e14bf46fd871be3257d11988288f04f4";
    public static final String Constv="1";
    public static final String Constmenthod="order.list";
    public static final String Consturl="http://openapi.jinritemai.com";
    //获得sign签名
    public static String getLBSign(String app_key,String method,String param_json,
                                   String timestamp,String v,String app_secret){
        StringBuilder stringBUilder = new StringBuilder();
        // 按照要求把参数拼接
        stringBUilder.append("app_key").append(app_key).append("method").append(method).
                append("param_json").append(param_json).append("timestamp").append(timestamp).append("v").append(v);
        String strBuild = stringBUilder.toString();
        // 拼接app_secret,得到未加密的sign
        String signUnlock = app_secret+strBuild+app_secret;
        // 使用MD5加密得到加密的sign
        String sign = DigestUtils.md5DigestAsHex(signUnlock.getBytes());
        return sign;
    }


    public static String urlEncode(Map<String,String> data) {
        //将map里的参数变成像 showapi_appid=###&showapi_sign=###&的样子
        StringBuilder sb = new StringBuilder();
        for (Map.Entry i : data.entrySet()) {
            try {
                sb.append(i.getKey()).append("=").append(URLEncoder.encode(i.getValue() + "", "utf-8")).append("&");//URLEncoder.encode
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
        String urlParam = sb.toString().substring(0, sb.length() - 1);
        return urlParam;
    }

    /**
     * 对单层json进行key字母排序
     * @param json
     * @return
     */
    public static JSONObject getSortJson(JSONObject json){
        Iterator<String> iteratorKeys = json.keys();
        SortedMap map = new TreeMap();
        while (iteratorKeys.hasNext()) {
            String key = iteratorKeys.next().toString();
            String vlaue = json.optString(key);
            map.put(key, vlaue);
        }
        JSONObject json2 = JSONObject.fromObject(map);
        return json2;
    }



    public static String getHttpResult(String strUrl, Map<String,String> mapParam) {
        //buffer用于接受返回的字符
        StringBuffer buffer = new StringBuffer();
        String paramEncodeStr = urlEncode(mapParam);
        try {
            URL restServiceURL = new URL(strUrl + "?" + paramEncodeStr);
            System.out.println(strUrl + "?" + paramEncodeStr);
            HttpURLConnection httpConnection = (HttpURLConnection) restServiceURL.openConnection();
            httpConnection.setRequestMethod("GET");
            httpConnection.setRequestProperty("Accept", "application/json");
            if (httpConnection.getResponseCode() != 200) {
                throw new RuntimeException("HTTP GET Request Failed with Error code : "
                        + httpConnection.getResponseCode());
            }
            InputStream inStrm = httpConnection.getInputStream();
            byte[] b = new byte[1024];
            int length = -1;
            while ((length = inStrm.read(b)) != -1) {
                buffer.append(new String(b, 0, length));
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "接口调用失败";
    }

    public static Map<String, String> getUrlParam(String method,String param_json,String sign, String timestamp ){
        Map<String ,String> map=new HashMap<>();
        map.put("method",method);
        map.put("param_json",param_json);
        map.put("sign",sign);
        map.put("timestamp",timestamp);
        urlEncode(map);
        return map;
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    public void moveBookService() throws Exception {

    }

//    @RequestMapping("/getOrderList")
//    public String getOrderLists( @RequestBody OrderParam orderParam) {
//        //String method = "order.list";
//        String result = null;
//        // method中的格式为xxx.xxx,把method中.替换成/
//        String urlMethod = Constmenthod.replace(".", "/");
//        String url = Consturl + urlMethod;
//        System.out.println("url:" + url);
//        ObjectMapper objectMapper = new ObjectMapper();
//        // 获得当前时间
//        Date now = new Date();
//        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
//        String timestamp = dateFormat.format(now);
//        try {
//            // 把参数bean转成json
//            String param_json = objectMapper.writeValueAsString(orderParam);
//            System.out.println("param_json:" + param_json);
//            String sign =getLBSign(Constapp_key, Constmenthod, param_json, timestamp,Constv,Constapp_secret);
//            // 得到url路径上的param 就是把url上的参数放到map中便于循环统一使用URLEncoder.encode进行处理
//            Map<String, String> map = getUrlParam(Constmenthod, param_json, sign, timestamp);
//            result = getHttpResult(url, map);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }



    public static void main(String[] args) {
        Map<String, String> map=getUrlParam("order.list","{\"end_time\":\"2018/05/31 16:01:02\",\"is_desc\":\"1\",\"page\":\"0\",\"size\":\"10\",\"start_time\":\"2018/04/01 15:03:58\"}" ,"" ,"2018-06-14 16:06:59");
        System.out.println(map);
        Date now = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式
        String timestamp = dateFormat.format(now);
        Map<String ,String> s=getUrlParam(Constmenthod,"{\"end_time\":\"2018/05/31 16:01:02\",\"is_desc\":\"1\",\"page\":\"0\",\"size\":\"10\",\"start_time\":\"2018/04/01 15:03:58\"}","123123131",timestamp);
        System.out.println(s);
    }
}
