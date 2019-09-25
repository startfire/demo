package com.muban.demo.util;

import java.io.Serializable;

public interface ICode extends Serializable {

    int getCode();

    String getMsg();

    void setMsg(String msg);
}
