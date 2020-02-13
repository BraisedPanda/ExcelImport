package com.braisedpanda.dooraccess.base.model.es;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 汇总
 * @author JiC
 * @date 2020-01-11 15:15
 */
@Data
public class Sum implements Serializable {

    private static final long serialVersionUID = 2966350314752934199L;

    @JSONField(name = "value")
    private Double value;
}
