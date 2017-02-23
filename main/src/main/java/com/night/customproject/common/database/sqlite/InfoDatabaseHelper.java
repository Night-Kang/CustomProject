package com.night.customproject.common.database.sqlite;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Night on 9/26/16.
 * Description:
 */

public class InfoDatabaseHelper extends SQLiteOpenHelper {

    private static final String dataBaseName="ContactsInfo.db";
    private static final int version=1;

    private static final String DB_CREATE_CONTACTS="create table if not exists "
            +PersonInfo.PERSON_INFO_TABLE+"("+"_id INTEGER primary key autoincrement,"
            + PersonInfo.NAME+" TEXT,"
            +PersonInfo.PHONENUMBER+" TEXT)";

    @SuppressLint("NewApi")
    public InfoDatabaseHelper(Context context) {
        super(context, dataBaseName, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DB_CREATE_CONTACTS);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}