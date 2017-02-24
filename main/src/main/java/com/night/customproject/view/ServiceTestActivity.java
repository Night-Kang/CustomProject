package com.night.customproject.view;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.night.customproject.R;
import com.night.customproject.service.TestService;

public class ServiceTestActivity extends AppCompatActivity implements View.OnClickListener, ServiceConnection {

    private TextView mTextView;
    private EditText mEditView;

    private Button mStartButton, mStopButton, mSyncButton, mBindButton, mUnbindButton;

    private TestService.Binder bind = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_test);

        mTextView = (TextView) findViewById(R.id.textView);
        mEditView = (EditText) findViewById(R.id.editText);

        mStartButton = (Button) findViewById(R.id.startButton);
        mStartButton.setOnClickListener(this);

        mStopButton = (Button) findViewById(R.id.stopButton);
        mStopButton.setOnClickListener(this);

        mBindButton = (Button) findViewById(R.id.bindButton);
        mBindButton.setOnClickListener(this);

        mUnbindButton = (Button) findViewById(R.id.unbindButon);
        mUnbindButton.setOnClickListener(this);

        mSyncButton = (Button) findViewById(R.id.syncButton);
        mSyncButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()) {
            case R.id.startButton:
                intent.setClass(ServiceTestActivity.this, TestService.class);
                intent.putExtra("data", mEditView.getText().toString());
                startService(intent);
                break;
            case R.id.stopButton:
                intent.setClass(ServiceTestActivity.this, TestService.class);
                stopService(intent);
                break;
            case R.id.bindButton:
                intent.setClass(ServiceTestActivity.this, TestService.class);
                bindService(intent, this, Context.BIND_AUTO_CREATE);
                break;
            case R.id.unbindButon:
                unbindService(this);
                break;
            case R.id.syncButton:
                if (bind != null) {
                    bind.setData(mEditView.getText().toString());
                }
                break;
            default:
                break;
        }
    }

    @Override
    public void onServiceConnected(ComponentName name, IBinder service) {
        bind = (TestService.Binder) service;
        bind.getService().setCallback(new TestService.Callback() {
            @Override
            public void onDataChange(String data) {
                Message message = new Message();
                Bundle bundle = new Bundle();
                bundle.putString("data", data);
                message.setData(bundle);
                handler.sendMessage(message);
            }
        });
    }

    @Override
    public void onServiceDisconnected(ComponentName name) {

    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            mTextView.setText(msg.getData().getString("data"));
        }
    };
}
