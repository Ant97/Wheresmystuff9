package com.example.jaggia.wheresmystuff9.Model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by James on 6/14/2017.
 *
 * Represents a single User which has an id, username, and password
 *
 * We are passing this object in a bundle between intents, so we implement
 * the Parcelable interface.
 *
 */

public class User implements Parcelable{
    //static variable to create unique id
    private static int Next_ID;

    /**unique id number*/
    private int _id;
    /**unique username*/
    private String _username;
    /**the user's password*/
    private String _password;
    /*ErrorCode returned when creating the user*/
    //private ErrorCode _errorCode;

    /**no parameter constructor for controller*/
    public User(){
        this("default username" , "default password");
    }

    /** makes a new User
     *
     * @param username the user's public name
     * @param password the user's password
     * _id is a unique identifier for the user
     *                 ErrorCodes:
     *                      ILLEGALUSERNAME means the username given is illegal
     *                      ILLEGALPASSWORD means the password given is illegal
     *                      SUCCESS means the user was created with no issues
     */
    public User(String username, String password){
       /* if(null == username){
            _errorCode = ErrorCode.ILLEGALUSERNAME;
        }else if(null == password){
            _errorCode = ErrorCode.ILLEGALPASSWORD;
        }
        _errorCode = ErrorCode.SUCCESS;*/
        _id = Next_ID++;
        _username = username;
        _password = password;
    }

    /** ********************************************
     * the getters and setters
     */
    public int getId(){
        return _id;
    }
    public String getUsername(){
        return _username;
    }
    public String getPassword(){
        return _password;
    }
    //public ErrorCode getErrorCode() {return _errorCode; }

    public void setUsername(String username){
        _username = username;
    }
    public void setPassword(String password){
        _password = password;
    }

    /* ********************************************
     * Overridden methods
     * toString(), describeContents(), writeToParcel()
     *
     */
    //prints the user's username
    @Override
    public String toString(){
        return _username;
    }
    //needed to implement parcel
    @Override
    public int describeContents(){
        return 0;
    }
    //method for parcel
    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(_username);
        dest.writeString(_password);
        dest.writeInt(_id);
    }

    //method for parcel
    private User(Parcel in){
        _username = in.readString();
        _password = in.readString();
        _id = in.readInt();
    }

    //method for parcel
    public static final Parcelable.Creator<User> CREATOR
            = new Parcelable.Creator<User>() {
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
