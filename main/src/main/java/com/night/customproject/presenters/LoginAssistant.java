package com.night.customproject.presenters;

import android.content.Context;

import com.night.customproject.model.UserInfo;
import com.night.customproject.presenters.viewinterface.LoginView;
import com.night.customproject.presenters.viewinterface.LogoutView;

/**
 * Created by Night on 7/12/16.
 * Description:用户数据信息
 */
public class LoginAssistant extends Presenter{

    private Context mContext;
    private static final String TAG = LoginAssistant.class.getSimpleName();
    private LoginView mLoginView;
    private LogoutView mLogoutView;

    public LoginAssistant(Context context) {
        mContext = context;
    }

    public LoginAssistant(Context context, LoginView loginView) {
        mContext = context;
        mLoginView = loginView;
    }

    public LoginAssistant(Context context, LogoutView logoutView) {
        mContext = context;
        mLogoutView = logoutView;
    }

    /**
     * 登录
     * @param identify 用户ID/手机
     * @param userSig 用户签名/密码
     */
    public void imLogin(String identify, String userSig) {
        //登录成功
        UserInfo.getInstance().setId(identify);
        UserInfo.getInstance().setSign(userSig);
        UserInfo.getInstance().saveUserInfo(mContext.getApplicationContext());
    }

    /**
     * 登出
     */
    public void imLogout() {
        //清除本地用户缓存
        UserInfo.getInstance().clearUserInfoCache(mContext);
    }

    /**
     * 注册成功直接登录
     * @param id
     * @param psw
     * @param verify
     */
    public void imRegister(final String id, final String psw, final String verify) {

    }

    @Override
    public void onCleanDestroy() {
        mLoginView = null;
        mLogoutView = null;
        mContext = null;
    }
}
