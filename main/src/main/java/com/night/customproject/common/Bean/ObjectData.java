package com.night.customproject.common.bean;

/**
 * Created by Night on 7/7/16.
 * Description: 对象数据
 * Note: need set by proguard-rules,序列化优化(FlatBuffers,Nano-Proto-Buffers),Jackson
 */
public class ObjectData {
    private int height, width;

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }
}
