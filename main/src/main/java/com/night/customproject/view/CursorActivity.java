package com.night.customproject.view;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.night.customproject.R;
import com.night.customproject.adapters.CursorDemoAdapter;
import com.night.customproject.common.database.ormlite.Contact;
import com.night.customproject.common.database.ormlite.ContactController;
import com.night.customproject.common.database.sqlite.ContactHelper;
import com.night.customproject.common.database.sqlite.PersonInfo;

import java.util.ArrayList;
import java.util.List;

public class CursorActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText mUsername;
    private Button mAddButton, mAddPerson;
    private List<String> mNameList;
    private ListView mListView;
    private CursorDemoAdapter mAdapter;

    private ContactHelper mContactHelper;
    private SQLiteDatabase dataBase;
    private String orderBy;//查询数据库的排序

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cursor);

        mNameList = new ArrayList<>();

        mUsername = (EditText) findViewById(R.id.username);
        mAddButton = (Button) findViewById(R.id.addButton);
        mAddButton.setOnClickListener(this);
        mAddPerson = (Button) findViewById(R.id.addPerson);
        mAddPerson.setOnClickListener(this);

        mListView = (ListView) findViewById(R.id.listView);

        initData();
        getDatabaseFromDB();
    }


    private void initData() {
        mContactHelper = new ContactHelper(this);
        dataBase = mContactHelper.getWritableDatabase();

        orderBy = "_id desc";
        Cursor myCursor = dataBase.query(PersonInfo.PERSON_INFO_TABLE, null, null, null, null, null, orderBy);
        mAdapter = new CursorDemoAdapter(CursorActivity.this, myCursor);
        mListView.setAdapter(mAdapter);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addButton:
                Contact contact = new Contact();
                contact.setName(mUsername.getText().toString());
                contact.setAge(18);
                ContactController.addOrUpdate(contact);

                //展示
                getDatabaseFromDB();
                break;

            case R.id.addPerson:

                String name = mUsername.getText().toString();

                if(name.equals("")){
                    Toast.makeText(this, "用户名不能为空!", Toast.LENGTH_SHORT).show();
                    return;
                }

                ContentValues contentValues=new ContentValues();
                contentValues.put(PersonInfo.NAME,name);
                contentValues.put(PersonInfo.PHONENUMBER,"18");
                //把EditText中的文本插入数据库
                dataBase.insert(PersonInfo.PERSON_INFO_TABLE,null,contentValues);
                //根据 _id 降序插叙数据库保证最后插入的在最上面
                Cursor myCursor = dataBase.query(PersonInfo.PERSON_INFO_TABLE,null,null,null,null,null,orderBy);
                //Cursor改变调用chanageCursor()方法
                mAdapter.changeCursor(myCursor);
                break;
            default:
                break;
        }
    }

    //通过OrmLite的方式
    private void getDatabaseFromDB() {
        List<Contact> list = ContactController.queryAll();
        for (int i = 0; i < list.size(); i++) {
            Contact contact = list.get(i);
            Log.e("Night", "-------list:" + contact.toString());
        }
    }
}
