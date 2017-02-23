package com.night.customproject.common.database.ormlite;

import com.night.customproject.CustomApplication;

/**
 * Created by Night on 9/26/16.
 * Description: 创建一个单例
 */

public class DBController {
    private static DBController instance;
    private OrmLiteHelper mDBHelper;

    private DBController() {
        mDBHelper = new OrmLiteHelper(CustomApplication.getContext());
        mDBHelper.getWritableDatabase();
    }

    public static DBController getInstance() {
        if (instance == null) {
            instance = new DBController();
        }
        return instance;
    }

    public OrmLiteHelper getDB() {
        return mDBHelper;
    }
}
