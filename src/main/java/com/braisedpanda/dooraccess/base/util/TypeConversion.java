package com.braisedpanda.dooraccess.base.util;

import com.braisedpanda.dooraccess.base.exception.BeanUtilsException;
import com.braisedpanda.dooraccess.base.service.Fn;
import com.google.common.collect.Lists;

import java.beans.Introspector;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

/**
 * ClassName: MyTypeConversion
 * @Description: 类型转换
 * @author JiC
 * @date 2017年3月22日
 */
public final class TypeConversion {
	
	/**
	 * @Description: obj 转list
	 * @param obj
	 * @return
	 * @return List<T>
	 * @throws
	 * @author JiC
	 * @date 2018年9月18日
	 */
	public static <T> List<T> objToList(Object obj) {
		if(obj == null) {
			return Lists.newArrayList();
		}
		return (List<T>) obj;
	}

	public static <T> T objToEntity(Object obj) {
		if (obj == null) { return (T) new Object();}
		return (T) obj;
	}

	/**
	 * @Description: obj 转Long
	 * @param obj
	 * @return
	 * @return Long
	 * @throws
	 * @author JiC
	 * @date 2018年9月18日
	 */
	public static long objToLong(Object obj) {
		if(obj == null) {
			return 0;
		}
		return (long) obj;
	}

	/**
	 * @Deprecated obj 转double
	 * @Author: JiC
	 * @date: 2019/9/19
	 */
	public static double objToDouble(Object obj) {
		if (obj == null) {
			return 0.0;
		}
		return (double) obj;
	}

	/**
	 * @Description: obj 转int
	 * @param obj
	 * @return
	 * @return Long
	 * @throws
	 * @author JiC
	 * @date 2018年9月18日
	 */
	public static int objToInteger(Object obj) {
		if(obj == null) {
			return 0;
		}
		return (int) obj;
	}

	/**
	 * @Description: obj 转 String
	 * @param obj
	 * @return
	 * @return String
	 * @throws
	 * @author JiC
	 * @date 2019年1月6日
	 */
	public static String objToString(Object obj) {
	    if (obj == null) {
	    	return "";
		}
	    return obj.toString();
	}

	/**
	 * @Deprecated 获取实体对象字段名称
	 * @param fns
	 * @return: java.lang.String[]
	 * @Author: JiC
	 * @date: 2019/9/2
	 */
	public static <A, B> String[] fnToFieldNames(Fn<A, B> ...fns) {
		return Arrays.asList(fns).stream().map(fun ->
				TypeConversion.fnToFieldName(fun)).toArray(String[]::new);
	}

	/**
	 * @Deprecated 获取实体对象字段名称
	 * @param fn
	 * @return: java.lang.String
	 * @Author: JiC
	 * @date: 2019/9/2
	 */
	public static <A, B> String fnToFieldName(Fn<A, B> fn) {
		try {
			Method method = fn.getClass().getDeclaredMethod("writeReplace");
			method.setAccessible(Boolean.TRUE);
			SerializedLambda serializedLambda = (SerializedLambda) method.invoke(fn);
			String getter = serializedLambda.getImplMethodName();
			if (GET_PATTERN.matcher(getter).matches()) {
				getter = getter.substring(3);
			} else if (IS_PATTERN.matcher(getter).matches()) {
				getter = getter.substring(2);
			}
			return Introspector.decapitalize(getter);
		} catch (ReflectiveOperationException e) {
			throw new BeanUtilsException(e);
		}
	}

	private static final Pattern GET_PATTERN = Pattern.compile("^get[A-Z].*");
	private static final Pattern IS_PATTERN  = Pattern.compile("^is[A-Z].*");

	private TypeConversion() { throw new IllegalAccessError();}
}
