package com.night.customproject.common.database.ormlite;

import com.j256.ormlite.dao.Dao;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Night on 9/26/16.
 * Description: 封装处理
 */

public class ContactController {
    public static Dao<Contact, String> getDao() throws SQLException {
        return DBController.getInstance().getDB().getDao(Contact.class);
    }

    public static void addOrUpdate(Contact userBean) {
        try {
            getDao().createOrUpdate(userBean);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void delete(String id) {
        try {
            getDao().deleteById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Contact queryById(String id) {
        try {
            return getDao().queryForId(id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<Contact> queryAll() {
        try {
            return (ArrayList<Contact>) getDao().queryForAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void addOrUpdate(ArrayList<Contact> t) {
        try {
            if (t != null && t.size() > 0) {
                for (Contact userBean : t) {
                    getDao().createOrUpdate(userBean);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
