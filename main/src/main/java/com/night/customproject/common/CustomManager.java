package com.night.customproject.common;

import java.io.Serializable;

/**
 * Created by Night on 7/7/16.
 * Description: 创建一个单例
 */
public class CustomManager implements Serializable {

    private static class CustomManagerHolder {
        /**
         * 单例对象实例
         */
        static final CustomManager instance = new CustomManager();
    }

    /**
     * private的构造函数用于避免外界直接使用new来实例化对象
     */
    private CustomManager() {}

    public static CustomManager getInstance() {
        return CustomManagerHolder.instance;
    }

    /**
     * readResolve方法应对单例对象被序列化时候
     * @return
     */
    private Object readResolve() {
        return getInstance();
    }
}
