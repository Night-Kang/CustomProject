package com.night.customproject.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by night on 23/02/2017.
 * Description: 使用Parcelable来格式化
 */

public class UserWithParcelable  implements Parcelable{

    private String username;
    private int age;

    protected UserWithParcelable(Parcel in) {
        username = in.readString();
        age = in.readInt();
    }

    public static final Creator<UserWithParcelable> CREATOR = new Creator<UserWithParcelable>() {
        @Override
        public UserWithParcelable createFromParcel(Parcel in) {
            return new UserWithParcelable(in);
        }

        @Override
        public UserWithParcelable[] newArray(int size) {
            return new UserWithParcelable[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public UserWithParcelable(String username, int age) {
        this.username = username;
        this.age = age;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeInt(age);
    }

    @Override
    public String toString() {
        return "UserWithParcelable{" +
                "username='" + username + '\'' +
                ", age=" + age +
                '}';
    }
}
