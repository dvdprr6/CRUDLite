package com.example.david.crudlite.model;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.david.crudlite.table.UserTable;

import java.util.UUID;

/*
 * This class represent data in the Users table (see UserTable class)
 */
public class UserItem implements Parcelable {

    private String userId;
    private String firstName;
    private String lastName;
    private String email;

    public UserItem(){}

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        if(userId == null){
            userId = UUID.randomUUID().toString();
        }
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public ContentValues toValues(){
        ContentValues values = new ContentValues(4);

        values.put(UserTable.COLUMN_ID, this.userId);
        values.put(UserTable.COLUMN_FIRST_NAME, this.firstName);
        values.put(UserTable.COLUMN_LAST_NAME, this.lastName);
        values.put(UserTable.COLUMN_EMAIL, this.email);

        return values;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.userId);
        dest.writeString(this.firstName);
        dest.writeString(this.lastName);
        dest.writeString(this.email);
    }

    protected UserItem(Parcel in){
        this.userId = in.readString();
        this.firstName = in.readString();
        this.lastName = in.readString();
        this.email = in.readString();
    }

    public static final Parcelable.Creator<UserItem> CREATOR = new Parcelable.Creator<UserItem>(){
        @Override
        public UserItem createFromParcel(Parcel source){
            return new UserItem(source);
        }

        @Override
        public UserItem[] newArray(int size){
            return new UserItem[size];
        }
    };
}
