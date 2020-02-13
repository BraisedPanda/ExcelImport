package com.braisedpanda.dooraccess.base.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 返回分页数据 
 * @author JiC
 * @date 2019-07-04 14:16
 */
@Data
@Accessors(chain = true)
public class ResultPage<T> implements Serializable {

    private static final long serialVersionUID = 3815532976698056439L;

    private List<T> data;

    private Long total;

    private int pages;
}
