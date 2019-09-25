package com.muban.demo.constant.code;


import com.muban.demo.util.BusinessCode;

public enum BaseCodeEnum implements BusinessCode {
    SC_OK(200, "成功"),

    SC_BAD_REQUEST(400, "错误请求"),

    SC_UNAUTHORIZED(401, "需要登录"),

    SC_PAYMENT_REQUIRED(402, "需要支付"),

    SC_ILLEGAL_WORDS(404, "非法字符"),
    
	SC_TOOMORE_WORDS(405, "字符超过限制"),
	
	SC_IMAGEKEY_NULL(406, "图片imageKey为空"),
	
    SC_CAPTHCAL_REQUIRED(407, "需要验证码"),
    
    SC_CAPTHCAL_INVALID(408, "验证码错误"),

    SC_CONFLICT(409, ""),

    SC_INTERNAL_SERVER_ERROR(500, "服务器错误"),

    SC_SERVICE_UNAVAILABLE(503, "服务器忙"),

    SC_PARAMETER_MISS(601, "参数缺失"),
    
    SC_PARAMETER_INVALID(602, "参数错误"),
    
    SC_NOT_EXISTS(603, "不存在"),
    
    SC_NOT_ALLOWED(703, "不允许");

    private int code;

    private String msg;

    private BaseCodeEnum(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
