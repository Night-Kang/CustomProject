package com.night.customproject.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.night.customproject.R;
import com.night.customproject.common.extension.TitleBar;

public class RegisterActivity extends AppCompatActivity implements TitleBar.TitleBarClickListener {

    private TitleBar mTitleBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

//        CustomView view = new CustomView(this);
//        setContentView(view);

        mTitleBar = (TitleBar)findViewById(R.id.titleBar);
        mTitleBar.setOnTitleBarClickListener(this);

        mTitleBar.setButtonVisable(0, true);
    }

    @Override
    public void leftClick() {
        Toast.makeText(RegisterActivity.this,
                "left", Toast.LENGTH_SHORT)
                .show();
    }

    @Override
    public void rightClick() {
        Toast.makeText(RegisterActivity.this,
                "right", Toast.LENGTH_SHORT)
                .show();
    }
}
