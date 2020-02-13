package com.braisedpanda.dooraccess.base.util;

import com.braisedpanda.dooraccess.base.service.Fn;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * @ClassName: ParamValidator
 * @Description: 参数验证
 * @author JiC
 * @date 2018年9月20日
 */
public final class ParamValidator {

	private ParamValidator() { throw new IllegalAccessError();}

	/**
	 * @Description: lambda 表达式排除指定字段
	 * @author JiC
	 * @date 2019/7/5
	*/
	public static <T, E> boolean beanNotKeyParamVerify(T t, Fn<T, E> ...columns) {
		String[] strings = TypeConversion.fnToFieldNames(columns);
		return mapNotKeyParamVerify(BeanUtils.beanToMap(t), strings);
	}

	/**
	 * @Description: bena 数据是否为空 排除指定字段
	 * @author JiC
	 * @date 2019/7/5
	*/
	public static <T> boolean beanNotKeyParamVerify(T t, String ...keys) {
		return mapNotKeyParamVerify(BeanUtils.beanToMap(t), keys);
	}

	/**
	 * @Description: map数据是否为空,排除指定key
	 * @param map
	 * @param keys
	 * @return
	 * @return boolean
	 * @throws
	 * @author JiC
	 * @date 2018年9月21日
	 */
	@SuppressWarnings("unchecked")
    public static boolean mapNotKeyParamVerify(Map<String, Object> map, String ...keys) {
		Assert.noNullElements(keys, "指定参数不能为空");

		Map<String, Object> m = (Map<String, Object>) ((HashMap<String, Object>) map).clone();
		for(String key : keys) {
			m.remove(key);
		}

		return mapParamVerify(m);
	}

	/**
	 * @Description: map 参数验证非空验证
	 * @return
	 * @return boolean
	 * @throws
	 * @author JiC
	 * @date 2018年9月20日
	 */
	public static boolean mapParamVerify(Map<String, Object> map) {

		if(map == null || map.isEmpty()) { return false;}

		return objsParamVerify(map.values().toArray());
	}

	/**
	 * @Description: lambda表达式 指定key在bean中是否为空
	 * @author JiC
	 * @date 2019/7/5
	*/
	public static <T, E> boolean beanIsKeyParamVerify(T t, Fn<T, E> ...column) {
		String[] columnName = TypeConversion.fnToFieldNames(column);
		return mapIsKeyParamVerify(BeanUtils.beanToMap(t), columnName);
	}

	/**
	 * @Description: 指定key在bean中是否为空
	 * @author JiC
	 * @date 2019/7/5
	*/
	public static <T> boolean beanIsKeyParamVerify(T t, String ...keys) {
		return mapIsKeyParamVerify(BeanUtils.beanToMap(t), keys);
	}
	
	/**
	 * @Description: 指定key在map中数据是否为空
	 * @param map
	 * @param keys
	 * @return
	 * @return boolean 
	 * @throws
	 * @author JiC 
	 * @date 2018年9月20日
	 */
	public static boolean mapIsKeyParamVerify(Map<String, Object> map, String ...keys) {
		
		if(!isContainsMapKey(map, keys)) {return false;}
		
		Object obj;
		for(String key : keys) {
			obj = map.get(key);
			if(StringUtils.isEmpty(obj)) {return false;}
		}
		
		return true;
	}
	
	/**
	 * @Description: map中是否存在指定key
	 * @param map
	 * @param keys
	 * @return
	 * @return boolean 
	 * @throws
	 * @author JiC 
	 * @date 2018年9月20日
	 */
	public static boolean isContainsMapKey(Map<String, Object> map, String ...keys) {
		if(map == null || map.isEmpty()) { return false;}
		Set<String> set = map.keySet();
		return set.containsAll(Arrays.asList(keys));
	}
	
	/**
	 * @Description: Object 参数非空验证
	 * @param obj
	 * @return
	 * @return boolean 
	 * @throws
	 * @author JiC 
	 * @date 2018年9月20日
	 */
	public static boolean objsParamVerify(Object ...obj) {
		int len;
		if(obj == null || (len = obj.length) <= 0) {
			return false;
		}
		Object str;
		for(int i = 0; i < len; i++) {
			str = obj[i];
			if (str == null) {
				return false;
			}else if (str instanceof String && StringUtils.isEmpty(str)) {
				return false;
			}
		}
		return true;
	}
}
