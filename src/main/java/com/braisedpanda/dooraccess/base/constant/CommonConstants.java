package com.braisedpanda.dooraccess.base.constant;

/**
 * 公共常量类 
 * @author JiC
 * @date 2019-06-27 14:53
 */
public final class CommonConstants {

    /**
     * @Deprecated 每页显示数
     * @Author: JiC
     * @date: 2019/7/16
     */
    public final static int PAGE_SIZE = 10;

    /**
     * @Deprecated 菜单顶级节点
     * @Author: JiC
     * @date: 2019/7/16
     */
    public final static int MENU_TOP_NODE = -1;

    /**
     * @Deprecated 显示
     * @Author: JiC
     * @date: 2019/7/16
     */
    public final static int IS_SHOW = 1;

    /**
     * @Deprecated 隐藏
     * @Author: JiC
     * @date: 2019/7/16
     */
    public final static int IS_HIDDEN = 0;

    /**
     * @Deprecated 开始时间字段值
     * @Author: JiC
     * @date: 2019/9/16
     */
    public final static String START_TIME_NAME = "startTime";

    /**
     * @Deprecated 结束时间字段值
     * @Author: JiC
     * @date: 2019/9/16
     */
    public final static String END_TIME_NAME = "endTime";

    /**
     * @Deprecated 顶级法人
     * @Author: JiC
     * @date: 2019/9/21
     */
    public final static String TOP_LEGAL_PERSON = "10000000000";

    /**
     * @Deprecated 顶级图表法人
     * @Author: JiC
     * @date: 2019/9/21
     */
    public final static String GRAPH_LEGAL_PERSON = "00000000000";

    public final static String JWT_USER_KEY = "jwtUser";

    private CommonConstants() {
        throw new IllegalAccessError();
    }
}
