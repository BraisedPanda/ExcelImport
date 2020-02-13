package com.braisedpanda.dooraccess.base.util;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**@ClassName: SpringContextUtil
 * @Description:
 * @author JiC
 * @date 2019/3/21
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * @Deprecated 实现ApplicationContextAware接口的回调方法，注入上下文对象
     * @param applicationContext
     * @return: void
     * @Author: JiC
     * @date: 2019/7/31
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) {
        SpringContextUtil.applicationContext = applicationContext;
    }

    /**
     * 得到Spring 上下文环境
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        checkApplicationContext();
        return applicationContext;
    }

    /**
     * @Deprecated 通过Spring Bean name 得到Bean
     * @Author: JiC
     * @date: 2019/7/31
     */
    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    /**
     * @Deprecated 通过Spring Bean name 和类型 得到Bean
     * @Author: JiC
     * @date: 2019/7/31
     */
    public static <T> T getBean(String name, Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBean(name, clazz);
    }

    /**
     * @Deprecated 通过类型得到Bean
     * @Author: JiC
     * @date: 2019/7/31
     */
    public static <T> T getBean(Class<T> clazz) {
        checkApplicationContext();
        return applicationContext.getBean(clazz);
    }

    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicaitonContext未注入");
        }
    }
}
