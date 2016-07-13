package com.night.customproject.presenters;

/**
 * Created by Night on 7/12/16.
 * Description:页面展示逻辑基类?
 */
public abstract class Presenter {
    //销去持有外部的mContext;
    public abstract void onCleanDestroy();
}
