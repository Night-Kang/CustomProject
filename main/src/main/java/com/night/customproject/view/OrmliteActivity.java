package com.night.customproject.view;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OrmLiteBaseActivity;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.night.customproject.common.database.ormlite.Contact;
import com.night.customproject.common.database.ormlite.OrmLiteHelper;

import java.util.List;
import java.util.Random;

/**
 * Created by Night on 9/26/16.
 * Description: 直接向数据库里面写数据,展示在TextView上面
 *              没有纯粹的setContentView(R.layout.main);配对的layout.xml文件
 */

public class OrmLiteActivity extends OrmLiteBaseActivity<OrmLiteHelper> {

    private final String LOG_TAG = getClass().getSimpleName();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(LOG_TAG, "creating " + getClass() + " at " + System.currentTimeMillis());
        TextView tv = new TextView(this);
        doSampleDatabaseStuff("onCreate", tv);
        setContentView(tv);
    }

    /**
     * Do our sample database stuff.
     */
    private void doSampleDatabaseStuff(String action, TextView tv) {
        // get our dao
        RuntimeExceptionDao<Contact, Integer> simpleDao = getHelper().getSimpleDataDao();
        // query for all of the data objects in the database
        List<Contact> list = simpleDao.queryForAll();
        // our string builder for building the content-view
        StringBuilder sb = new StringBuilder();
        sb.append("got ").append(list.size()).append(" entries in ").append(action).append("\n");

        // if we already have items in the database
        int simpleC = 0;
        for (Contact simple : list) {
            sb.append("------------------------------------------\n");
            sb.append("[").append(simpleC).append("] = ").append(simple.getName()).append("\n");
            simpleC++;
        }
        sb.append("------------------------------------------\n");
        for (Contact simple : list) {
            simpleDao.delete(simple);
            sb.append("deleted id ").append(simple.getId()).append("\n");
            Log.i(LOG_TAG, "deleting simple(" + simple.getId() + ")");
            simpleC++;
        }

        int createNum;
        do {
            createNum = new Random().nextInt(3) + 1;
        } while (createNum == list.size());
        for (int i = 0; i < createNum; i++) {
            // create a new simple object
            long millis = System.currentTimeMillis();
            Contact simple = new Contact("zhangsan"+i, i, "Male");
            // store it in the database
            simpleDao.create(simple);
            Log.i(LOG_TAG, "created simple(" + millis + ")");
            // output it
            sb.append("------------------------------------------\n");
            sb.append("created new entry #").append(i + 1).append(":\n");
            sb.append(simple.toString()).append("\n");
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                // ignore
            }
        }

        tv.setText(sb.toString());
        Log.i(LOG_TAG, "Done with page at " + System.currentTimeMillis());
    }
}
