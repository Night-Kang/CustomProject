package com.night.customproject.view;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.night.customproject.R;
import com.night.customproject.common.Constant;
import com.night.customproject.model.UserInfo;
import com.night.customproject.presenters.LoginAssistant;
import com.night.customproject.presenters.viewinterface.LoginView;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends BaseActivity implements View.OnClickListener, LoginView {
    TextView mBtnLogin, mBtnRegister;
    EditText mPassWord, mUserName;
    private static final String TAG = LoginActivity.class.getSimpleName();
    private LoginAssistant mLoginHeloper;
    private final int REQUEST_PHONE_PERMISSIONS = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //严格模式检测可能出现性能问题的地方
        if (Constant.DEVELOPER_MODE) {
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()   // or .detectAll() for all detectable problems
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .detectLeakedClosableObjects()
                    .penaltyLog()
                    .penaltyDeath()
                    .build());
        }
        super.onCreate(savedInstanceState);
        mLoginHeloper = new LoginAssistant(this, this);
        checkPermission();

        //获取个人数据本地缓存
        UserInfo.getInstance().getUserInfoCache(getApplicationContext());
        if (needLogin()) {//本地没有账户需要登录
            initView();
        } else {
            //有账户登录直接IM登录
//            SxbLog.i(TAG, "LoginActivity onCreate");
            mLoginHeloper.imLogin(UserInfo.getInstance().getId(), UserInfo.getInstance().getSign());
        }
    }

    /**
     * 判断是否需要登录
     *
     * @return true 代表需要重新登录
     */
    public boolean needLogin() {
        if (UserInfo.getInstance().getId() != null) {
            return false;//有账号不需要登录
        } else {
            return true;//需要登录
        }

    }

    private void initView() {
        setContentView(R.layout.activity_login);
        mBtnLogin = (TextView) findViewById(R.id.btn_login);
        mUserName = (EditText) findViewById(R.id.username);
        mUserName.setBackgroundResource(R.drawable.login_ui_rounded_rectangle_normal);
        mPassWord = (EditText) findViewById(R.id.password);
        mPassWord.setBackgroundResource(R.drawable.login_ui_rounded_rectangle_normal);
        mBtnRegister = (TextView) findViewById(R.id.registerNewUser);
        mBtnRegister.setOnClickListener(this);
        mBtnLogin.setOnClickListener(this);
    }

    @Override
    protected void onDestroy() {
        mLoginHeloper.onCleanDestroy();
        super.onDestroy();
    }

    @Override
    public void loginSucc() {

    }

    @Override
    public void loginFail() {

    }

    @Override
    public void onClick(View view) {

    }

    //获取手机状态和内存卡
    void checkPermission() {
        final List<String> permissionsList = new ArrayList<>();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if ((checkSelfPermission(Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.READ_PHONE_STATE);
            if ((checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED))
                permissionsList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
            if (permissionsList.size() != 0) {
                requestPermissions(permissionsList.toArray(new String[permissionsList.size()]),
                        REQUEST_PHONE_PERMISSIONS);
            }
        }
    }
}
