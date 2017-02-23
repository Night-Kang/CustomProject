package com.night.customproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.night.customproject.model.User;
import com.night.customproject.model.UserWithParcelable;
import com.night.customproject.view.BaseActivity;
import com.night.customproject.view.LoginActivity;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);

        Intent intent = new Intent(this, LoginActivity.class);
        intent.putExtra("user", new User("username", 3));

        intent.putExtra("userParcelable", new UserWithParcelable("user", 34));
        startActivity(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
