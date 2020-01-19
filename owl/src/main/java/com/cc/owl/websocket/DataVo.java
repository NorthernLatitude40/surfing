package com.cc.owl.websocket;

import java.util.Date;

/**
 * @Author: Wayne
 * @Date: 2020/1/16 22:43
 * @Version: 1.0
 */
public class DataVo {
    private int type;
    private Date date;
    private String value;
    private String name;
    public void setType(int type) {
        this.type = type;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setName(String name) {
        this.name = name;
    }
}
