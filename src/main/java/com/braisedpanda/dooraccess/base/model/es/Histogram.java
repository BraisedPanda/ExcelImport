package com.braisedpanda.dooraccess.base.model.es;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

import java.io.Serializable;

/**
 * @Description 统计
 * @author JiC
 * @date 2020-01-11 15:18
 */
@Data
public class Histogram implements Serializable {
    private static final long serialVersionUID = -8512544158176404499L;

    @JSONField(name = "key_as_string")
    private String keyAsString;

    @JSONField(name = "key")
    private Long key;

    @JSONField(name = "doc_count")
    private Integer docCount;

    @JSONField(name = "sum")
    private Sum sum;
}
