package com.muban.demo.util;



import com.muban.demo.constant.code.BaseCodeEnum;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

public class JSONResponse extends HashMap<String, Object> {

    private static final long serialVersionUID = -6825826605906216823L;

    private BusinessCode codeInterface = BaseCodeEnum.SC_OK;
    private Map<String, Object> data = new HashMap<>();
    public JSONResponse() {
        putCodeMsg();
    }

    private void putCodeMsg() {
        this.put("code", codeInterface.getCode());
        this.put("msg", StringUtils.defaultString(codeInterface.getMsg(), ""));
    }
    public void sedData(Map<String, Object> data) {
        this.data = data;
    }
    public JSONResponse setCodeEnum(BusinessCode codeInterface) {
        this.codeInterface = codeInterface;
        putCodeMsg();
        return this;
    }
    public int getCode() {
        return codeInterface.getCode();
    }

    public String getMsg() {
        return codeInterface.getMsg();
    }

    public JSONResponse addAttribute(String key, Object value) {
        this.put(key, value);
        return this;
    }
    public JSONResponse addData(Object value) {
        this.put("data", value);
        return this;
    }

    public static JSONResponse newResponse() {
        JSONResponse jsonResponse = new JSONResponse();
        return jsonResponse;
    }

    public static JSONResponse newResponse(BusinessCode codeInterface) {
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.setCodeEnum(codeInterface);
        return jsonResponse;
    }

    public static JSONResponse newResponse(BusinessCode codeInterface, String errMsg) {
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.setCodeEnum(codeInterface);
        jsonResponse.put("msg", StringUtils.defaultString(errMsg, ""));
        return jsonResponse;
    }

    public static JSONResponse newResponse(String key, Object value) {
        JSONResponse jsonResponse = new JSONResponse();
        jsonResponse.addAttribute(key, value);
        return jsonResponse;
    }



}
