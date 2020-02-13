package com.braisedpanda.dooraccess.base.util;

import com.braisedpanda.dooraccess.base.exception.BeanUtilsException;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import net.sf.cglib.beans.BeanMap;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.util.CollectionUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author JiC
 * @Description: bena 工具类
 * @date 2019/7/4
 */
public final class BeanUtils {

    /**
     * @param bean
     * @return Map<String,Object>
     * @throws
     * @Description: 将对象装换为map
     * @author JiC
     * @date 2017年3月22日
     */
    public static <T> Map<String, Object> beanToMap(T bean) {
        Map<String, Object> map = Maps.newHashMap();
        if (bean != null) {
            BeanMap beanMap = BeanMap.create(bean);
            for (Object key : beanMap.keySet()) {
                map.put(key + "", beanMap.get(key));
            }
        }
        return map;
    }

    /**
     * @param map
     * @param bean
     * @return T
     * @throws
     * @Description: 将map装换为javabean对象
     * @author JiC
     * @date 2017年3月22日
     */
    public static <T> T mapToBean(Map<String, Object> map, T bean) {
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

    /**
     * @param objList
     * @return List<Map<String,Object>>
     * @throws
     * @Description: 将List<T>转换为List<Map<String, Object>>
     * @author JiC
     * @date 2017年3月22日
     */
    public static <T> List<Map<String, Object>> objectsToMaps(List<T> objList) {
        List<Map<String, Object>> list = Lists.newArrayList();
        if (objList != null && objList.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0, size = objList.size(); i < size; i++) {
                bean = objList.get(i);
                map = beanToMap(bean);
                list.add(map);
            }
        }
        return list;
    }

    /**
     * @param maps
     * @param clazz
     * @return List<T>
     * @throws InstantiationException
     * @throws IllegalAccessException
     * @throws
     * @Description: 将List<Map<String,Object>>转换为List<T>
     * @author JiC
     * @date 2017年3月22日
     */
    public static <T> List<T> mapsToObjects(List<Map<String, Object>> maps, Class<T> clazz) throws InstantiationException, IllegalAccessException {
        List<T> list = Lists.newArrayList();
        if (maps != null && maps.size() > 0) {
            Map<String, Object> map = null;
            T bean = null;
            for (int i = 0, size = maps.size(); i < size; i++) {
                map = maps.get(i);
                bean = clazz.newInstance();
                mapToBean(map, bean);
                list.add(bean);
            }
        }
        return list;
    }

    /**
     * @Description: bean 复制
     * List<UserBean> userBeans = userDao.queryUsers();
     * List<UserDTO> userDTOs = BeanUtil.batchTransform(UserDTO.class, userBeans);
     * @author JiC
     * @date 2019/7/4
     */
    public static <T> List<T> batchTransform(final Class<T> clazz, List<?> srcList) {

        if (CollectionUtils.isEmpty(srcList)) {
            return Collections.emptyList();
        }
        return srcList.stream().map(obj -> transform(clazz, obj)).collect(Collectors.toList());
    }

    /**
     * @Description: bean 复制
     * UserBean userBean = new UserBean("username");
     * return BeanUtil.transform(UserDTO.class, userBean);
     * @author JiC
     * @date 2019/7/4
     */
    public static <T> T transform(Class<T> clazz, Object src) {
        if (src == null) {
            return null;
        }
        T instance;
        try {
            instance = clazz.newInstance();
        } catch (Exception e) {
            throw new BeanUtilsException(e);
        }
        org.springframework.beans.BeanUtils.copyProperties(src, instance, getNullPropertyNames(src));
        return instance;
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<>();
        for (PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 用于将一个列表转换为列表中的对象的某个属性映射到列表中的对象
     * <p>
     * <pre>
     *      List<UserDTO> userList = userService.queryUsers();
     *      Map<Integer, userDTO> userIdToUser = BeanUtil.mapByKey("userId", userList);
     * </pre>
     *
     * @param key 属性名
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> mapByKey(String key, List<?> list) {
        Map<K, V> map = new HashMap<>();
        if (CollectionUtils.isEmpty(list)) {
            return map;
        }
        try {
            Class<?> clazz = list.get(0).getClass();
            Field field = deepFindField(clazz, key);
            if (field == null) {
                throw new IllegalArgumentException("Could not find the key");
            }
            field.setAccessible(true);
            for (Object o : list) {
                map.put((K) field.get(o), (V) o);
            }
        } catch (Exception e) {
            throw new BeanUtilsException(e);
        }
        return map;
    }

    /**
     * 根据列表里面的属性聚合
     * <p>
     * <pre>
     *       List<ShopDTO> shopList = shopService.queryShops();
     *       Map<Integer, List<ShopDTO>> city2Shops = BeanUtil.aggByKeyToList("cityId", shopList);
     * </pre>
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, List<V>> aggByKeyToList(String key, List<?> list) {
        Map<K, List<V>> map = new HashMap<>();
        if (CollectionUtils.isEmpty(list)) {
            return map;
        }
        try {
            Class<?> clazz = list.get(0).getClass();
            Field field = deepFindField(clazz, key);
            if (field == null) {
                throw new IllegalArgumentException("Could not find the key");
            }
            field.setAccessible(true);
            for (Object o : list) {
                K k = (K) field.get(o);
                map.computeIfAbsent(k, k1 -> new ArrayList<>());
                map.get(k).add((V) o);
            }
        } catch (Exception e) {
            throw new BeanUtilsException(e);
        }
        return map;
    }

    /**
     * 用于将一个对象的列表转换为列表中对象的属性集合
     * <p>
     * <pre>
     *     List<UserDTO> userList = userService.queryUsers();
     *     Set<Integer> userIds = BeanUtil.toPropertySet("userId", userList);
     * </pre>
     */
    @SuppressWarnings("unchecked")
    public static <K> Set<K> toPropertySet(String key, List<?> list) {
        Set<K> set = new HashSet<>();
        if (CollectionUtils.isEmpty(list)) {
            return set;
        }
        try {
            Class<?> clazz = list.get(0).getClass();
            Field field = deepFindField(clazz, key);
            if (field == null) {
                throw new IllegalArgumentException("Could not find the key");
            }
            field.setAccessible(true);
            for (Object o : list) {
                set.add((K) field.get(o));
            }
        } catch (Exception e) {
            throw new BeanUtilsException(e);
        }
        return set;
    }


    private static Field deepFindField(Class<?> clazz, String key) {
        Field field = null;
        while (!Object.class.isAssignableFrom(clazz)) {
            try {
                field = clazz.getDeclaredField(key);
                if (field != null) {
                    break;
                }
            } catch (Exception e) {
                clazz = clazz.getSuperclass();
            }
        }
        return field;
    }

    /**
     * 获取某个对象的某个属性
     */
    public static Object getProperty(Object obj, String fieldName) {
        try {
            Field field = deepFindField(obj.getClass(), fieldName);
            if (field != null) {
                field.setAccessible(true);
                return field.get(obj);
            }
        } catch (Exception e) {
            throw new BeanUtilsException(e);
        }
        return null;
    }

    /**
     * 设置某个对象的某个属性
     */
    public static void setProperty(Object obj, String fieldName, Object value) {
        try {
            Field field = deepFindField(obj.getClass(), fieldName);
            if (field != null) {
                field.setAccessible(true);
                field.set(obj, value);
            }
        } catch (Exception e) {
            throw new BeanUtilsException(e);
        }
    }

    /**
     * @Deprecated 创建对象
     * @param clazz
     * @return: T
     * @Author: JiC
     * @date: 2019/8/1
     */
    public static <T> T newInstance(Class<T> clazz) {
        try {
            return clazz.newInstance();
        } catch (InstantiationException e) {
            throw new BeanUtilsException("实例化对象异常");
        } catch (IllegalAccessException e) {
            throw new BeanUtilsException("非法访问异常");
        }
    }

    private BeanUtils() {}
}
