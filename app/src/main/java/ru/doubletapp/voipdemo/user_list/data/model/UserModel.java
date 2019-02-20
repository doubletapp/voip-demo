package ru.doubletapp.voipdemo.user_list.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class UserModel implements Parcelable {

    private String mName;
    private boolean isOnline;

    private UserModel(Parcel in) {
        mName = in.readString();
        isOnline = in.readByte() != 0;
    }

    public UserModel() {}

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public boolean isOnline() {
        return isOnline;
    }

    public void setOnline(boolean online) {
        isOnline = online;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeByte((byte) (isOnline ? 1 : 0));
    }

    public static final Creator<UserModel> CREATOR = new Creator<UserModel>() {
        @Override
        public UserModel createFromParcel(Parcel in) {
            return new UserModel(in);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };
}
