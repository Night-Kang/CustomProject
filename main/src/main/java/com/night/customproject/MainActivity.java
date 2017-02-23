package com.night.customproject;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.night.customproject.view.BaseActivity;
import com.night.customproject.view.OrmLiteActivity;
import com.readystatesoftware.systembartint.SystemBarTintManager;

public class MainActivity extends BaseActivity implements View.OnClickListener {

    private TextView mTestView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);


        mTestView = (TextView)findViewById(R.id.testView);
        mTestView.setOnClickListener(this);

        //first open
        //something about sharePreference
//        Intent intent = new Intent(this, LoginActivity.class);
//        startActivity(intent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        tintManager.setStatusBarTintResource(R.color.main_color);
    }

    @TargetApi(19)
    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.testView:
//                intent = new Intent(this, CategoryActivity.class);
//                intent.addCategory("hello.world");
//                intent = new Intent(MainActivity.this, LayoutInflaterActivity.class);

                //无R.layout.main对应的Activity,RomLite数据库
                intent = new Intent(MainActivity.this, OrmLiteActivity.class);
                //有R.layout.main中使用RomLite数据库
//                intent = new Intent(MainActivity.this, CursorActivity.class);
                startActivity(intent);
                break;
            default:
                break;
        }
    }
}
