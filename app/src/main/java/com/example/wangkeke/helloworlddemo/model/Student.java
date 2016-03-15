package com.example.wangkeke.helloworlddemo.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by wangkeke on 2016/3/4.
 *
 * 右键--Generate--Parcelable
 */
public class Student implements Parcelable {

    private String username;

    private String age;

    public Student(String username, String age) {
        this.username = username;
        this.age = age;
    }


    /*以下自动生成*/
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.age);
    }

    protected Student(Parcel in) {
        this.username = in.readString();
        this.age = in.readString();
    }

    public static final Parcelable.Creator<Student> CREATOR = new Parcelable.Creator<Student>() {
        public Student createFromParcel(Parcel source) {
            return new Student(source);
        }

        public Student[] newArray(int size) {
            return new Student[size];
        }
    };
}
