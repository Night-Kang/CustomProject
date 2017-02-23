package com.night.customproject.common.database.ormlite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

import java.sql.SQLException;

/**
 * Created by Night on 9/24/16.
 * Description:
 */

public class OrmLiteHelper extends OrmLiteSqliteOpenHelper {

    private int tempAge = 0;
    // name of the database file for your application -- change to something appropriate for your app
    private static final String DATABASE_NAME = "OrmLite.db";
    // any time you make changes to your database objects, you may have to increase the database version
    private static final int DATABASE_VERSION = 4;

    // the DAO object we use to access the SimpleData table
    private Dao<Contact, Integer> contactDao = null;
    private RuntimeExceptionDao<Contact, Integer> contactRuntimeDao = null;

    public OrmLiteHelper(Context context) {
//        super(context, DATABASE_NAME, null, DATABASE_VERSION, R.raw.ormlite_config);
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
//            Log.i(OrmLiteHelper.class.getName(), "onCreate");
            TableUtils.createTable(connectionSource, Contact.class);
        } catch (SQLException e) {
//            Log.e(OrmLiteHelper.class.getName(), "Can't create database", e);
            throw new RuntimeException(e);
        }

        // here we try inserting data in the on-create as a test
//        RuntimeExceptionDao<Contact, Integer> dao = getSimpleDataDao();
//        tempAge++;
//        Contact simple = new Contact("zhangsan"+tempAge, tempAge, "Female");
//        dao.create(simple);
//        Log.i(OrmLiteHelper.class.getName(), "created new entries in onCreate: " + tempAge);
    }

    /**
     * This is called when your application is upgraded and it has a higher version number. This allows you to adjust
     * the various data to match the new version number.
     * <p>
     * 配置数据库升级的
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, ConnectionSource connectionSource, int oldVersion, int newVersion) {
        try {
//            Log.i(OrmLiteHelper.class.getName(), "onUpgrade");
            TableUtils.dropTable(connectionSource, Contact.class, true);
            // after we drop the old databases, we create the new ones
            onCreate(db, connectionSource);
        } catch (SQLException e) {
//            Log.e(OrmLiteHelper.class.getName(), "Can't drop databases", e);
            throw new RuntimeException(e);
        }

        //版本控制,基于DATABASE_VERSION的number
        /*
        if (oldVersion < 2) {
            if (!checkColumnExist(db, "contacts", "uuid")) {
                try {
                    Dao<Contact, String> dao = getDao(Contact.class);
                    dao.executeRaw("ALTER TABLE `contacts` ADD COLUMN uuid CHAR(32);");
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

        if (oldVersion < 3) {
            try {//尝试创建一个新的table, (UserInfo未配置@DatabaseTable等属性)
                TableUtils.createTableIfNotExists(connectionSource, UserInfo.class);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }*/
    }

    /**
     * 方法1：检查某表列是否存在
     *
     * @param db
     * @param tableName  表名
     * @param columnName 列名
     * @return
     */
    private boolean checkColumnExist(SQLiteDatabase db, String tableName
            , String columnName) {
        boolean result = false;
        Cursor cursor = null;
        try {
            //查询一行
            cursor = db.rawQuery("SELECT * FROM " + tableName + " LIMIT 0", null);
            result = cursor != null && cursor.getColumnIndex(columnName) != -1;
        } catch (Exception e) {
            Log.e("TAG", "checkColumnExists1..." + e.getMessage());
        } finally {
            if (null != cursor && !cursor.isClosed()) {
                cursor.close();
            }
        }

        return result;
    }

    /**
     * Returns the Database Access Object (DAO) for our SimpleData class. It will create it or just give the cached
     * value.
     */
    public Dao<Contact, Integer> getDao() throws SQLException {
        if (contactDao == null) {
            contactDao = getDao(Contact.class);
        }
        return contactDao;
    }

    /**
     * Returns the RuntimeExceptionDao (Database Access Object) version of a Dao for our SimpleData class. It will
     * create it or just give the cached value. RuntimeExceptionDao only through RuntimeExceptions.
     */
    public RuntimeExceptionDao<Contact, Integer> getSimpleDataDao() {
        if (contactRuntimeDao == null) {
            contactRuntimeDao = getRuntimeExceptionDao(Contact.class);
        }
        return contactRuntimeDao;
    }

    /**
     * Close the database connections and clear any cached DAOs.
     */
    @Override
    public void close() {
        super.close();
        contactDao = null;
        contactRuntimeDao = null;
    }
}
