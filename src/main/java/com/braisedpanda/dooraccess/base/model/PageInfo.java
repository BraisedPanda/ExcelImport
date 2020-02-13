package com.braisedpanda.dooraccess.base.model;

import com.braisedpanda.dooraccess.base.constant.CommonConstants;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Description: 分页请求
 * @author JiC
 * @date 2019/6/27
*/
@Data
@Accessors(chain = true)
public class PageInfo implements Serializable {

    private static final long serialVersionUID = 4893282470341309908L;

    private Integer pageNum = 1;

    private Integer pageSize = CommonConstants.PAGE_SIZE;

    private Boolean count = true;

    private String orderBy;

    private Boolean pageSizeZero;

    private Boolean orderByOnly = false;

    public int getStartSize() {
        int page = this.pageNum <= 0 ? 0 : this.pageNum - 1;
        return this.pageSize * page;
    }

}