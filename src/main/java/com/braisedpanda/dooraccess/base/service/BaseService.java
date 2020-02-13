package com.braisedpanda.dooraccess.base.service;

import com.braisedpanda.dooraccess.base.model.PageInfo;
import com.braisedpanda.dooraccess.base.model.ResultPage;

import java.util.List;

/**
 * @Description
 * @author JiC
 * @date 2019-09-02 14:53
 */
public interface BaseService<T> {

    /**
     * @Description: 根据实体类对象参数查询一条记录
     * @author JiC
     * @date 2019/6/27
     */
    T getByEntity(T entity);

    T getByExample(Object example);

    /**
     * @Deprecated 根据主键ID 获取数据
     * @param id ID
     * @return: T
     * @Author: JiC
     * @date: 2019/9/2
     */
    T getById(Object id);

    /**
     * @Description: 根据实体类对象参数查询list
     * @author JiC
     * @date 2019/6/29
     */
    List<T> listByEntity(T entity);

    List<T> listByExample(Object example);

    /**
     * @Description: 根据实体类参数获取记录数
     * @author JiC
     * @date 2019/7/4
     */
    int countByEntity(T entity);


    int countByExample(Object example);

    /**
     * @Description: 保存一个实体，null属性也会保存
     * @author JiC
     * @date 2019/6/29
     */
    int insert(T entity);

    /**
     * @Description: 保存一个实体，null属性不会保存
     * @author JiC
     * @date 2019/6/29
     */
    int insertSelective(T entity);

    /**
     * @Description: 根据实体属性作为条件进行删除
     * @author JiC
     * @date 2019/6/29
     */
    int deleteByEntity(T entity);

    /**
     * @Description: 根据主键ID 删除
     * @author JiC
     * @date 2019/7/4
     */
    int deleteById(Object id);

    /**
     * @Description: 根据主键更新属性不为null的值
     * @author JiC
     * @date 2019/6/29
     */
    int updateByIdSelective(T entity);

    /**
     * @Deprecated 分页查询
     * @param entity 查询条件
     * @param pageInfo 分页条件
     * @return: com.braisedpanda.dooraccess.base.model.ResultPage<T>
     * @Author: JiC
     * @date: 2019/9/2
     */
    ResultPage<T> pageByEntity(T entity, PageInfo pageInfo);

    /**
     * @Deprecated 分页查询
     * @param example 查询条件
     * @param pageInfo 分页条件
     * @return: com.braisedpanda.dooraccess.base.model.ResultPage<T>
     * @Author: JiC
     * @date: 2019/9/2
     */
    ResultPage<T> pageByExample(Object example, PageInfo pageInfo);
}
