package com.braisedpanda.dooraccess.domian;

import com.braisedpanda.dooraccess.domian.po.User;
import lombok.Data;
import org.apache.poi.ss.formula.functions.T;

import java.util.List;
@Data
public class ObjectResponse {

    private String msg;
    private Integer code;
    private List<User> data;

    public ObjectResponse(String msg, Integer code, List<User> data) {
        this.msg = msg;
        this.code = code;
        this.data = data;
    }

    public ObjectResponse(Integer code, List<User> data) {
        this.code = code;
        this.data = data;
    }
}
