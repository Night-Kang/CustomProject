package com.night.customproject.model;

import android.content.Context;
import android.content.SharedPreferences;

import com.night.customproject.utils.Constants;

/**
 * Created by Night on 7/12/16.
 * Description:用户状态数据
 */
public class UserInfo {

    private static final String TAG = UserInfo.class.getSimpleName();
    private String id;
    private String nickName;    // 呢称
    private String avatar;      // 头像
    private String sign;      // 签名/密码

    private int logLevel;           // 日志等级

    private UserInfo() {
    }

    private static class UserInfoHolder {
        public static final UserInfo INSTANCE = new UserInfo();
    }

    public static UserInfo getInstance() {
        return UserInfoHolder.INSTANCE;
    }

    //存储用户信息
    public void saveUserInfo(Context context) {
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_INFO, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(Constants.USER_ID, id);
        editor.putString(Constants.USER_NICK, nickName);
        editor.putString(Constants.USER_AVATAR, avatar);
        editor.putString(Constants.USER_SIGN, sign);
        editor.commit();
    }

    //用户退出清理
    public void clearUserInfoCache(Context context) {
        SharedPreferences settings = context.getSharedPreferences(Constants.USER_INFO, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.clear();
        editor.commit();
    }

    //获取缓存信息
    public void getUserInfoCache(Context context) {
        SharedPreferences sharedata = context.getSharedPreferences(Constants.USER_INFO, 0);
        id = sharedata.getString(Constants.USER_ID, null);
        nickName = sharedata.getString(Constants.USER_NICK, null);
        avatar = sharedata.getString(Constants.USER_AVATAR, null);
        sign = sharedata.getString(Constants.USER_SIGN, null);
//        int level = sharedata.getInt(Constants.LOG_LEVEL, CustomLog.SxbLogLevel.INFO.ordinal());
//        if (level < CustomLog.SxbLogLevel.OFF.ordinal() || level > CustomLog.SxbLogLevel.INFO.ordinal()) {
//            logLevel = CustomLog.SxbLogLevel.INFO;
//        } else {
//            logLevel = CustomLog.SxbLogLevel.values()[level];
//        }
//        CustomLog.setLogLevel(logLevel);
//        CustomLog.i(TAG, " getCache id: " + id);
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 签名/密码
     * @return
     */
    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }
}
