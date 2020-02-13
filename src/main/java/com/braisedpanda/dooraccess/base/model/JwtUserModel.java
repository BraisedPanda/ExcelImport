package com.braisedpanda.dooraccess.base.model;


import lombok.Data;
import lombok.Getter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author: chuanbin
 * @description:
 * @create: 2019-07-18
 **/
@Data
public class JwtUserModel implements Serializable{

    private static final long serialVersionUID = 8950850785245868906L;

    private Integer id;
    private String name;
    private String account;
    private String legalPersonCode;
    private Integer status;
    private Integer identityType;
    private String legalPersonName;
    private List<String> access;
    private Date validateTime;
    private String ip;
    private Integer subsystemid;
    /**
     * @Deprecated 是否是超级管理员
     * @Author: JiC
     * @date: 2020/1/4
     */
    public boolean isSuperAdmin() {
        return Identity.SUPER_ADMIN.type == this.identityType;
    }

    @Getter
    public enum Identity {
        SUPER_ADMIN(0, "超级管理员"),
        ORDINARY_ADMIN(1, "普通管理员"),
        OPERATOR_ADMIN(2, "操作员")
        ;

        private final int type;

        private final String describe;

        Identity(int type, String describe) {
            this.type = type;
            this.describe = describe;
        }
    }

}
