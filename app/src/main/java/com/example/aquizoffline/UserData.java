package com.example.aquizoffline;

import android.os.Parcel;
import android.os.Parcelable;

public class UserData extends Object implements Parcelable, Comparable{

    private String username;
    private int score;

    public UserData(String username, int score) {
        this.username = username;
        this.score = score;
    }

    public String getUsername() {
        return username;
    }

    public int getScore() {
        return score;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(username);
        dest.writeInt(score);
    }

    UserData (Parcel parcel){
        this.username = parcel.readString();
        this.score = parcel.readInt();
    }

    public static Creator<UserData> CREATOR = new Creator<UserData>() {

        @Override
        public UserData createFromParcel(Parcel source) {
            return new UserData(source);
        }

        @Override
        public UserData[] newArray(int size) {
            return new UserData[size];
        }

    };

    @Override
    public int compareTo(Object o) {
        return score - ((UserData) o).getScore();
    }
}
