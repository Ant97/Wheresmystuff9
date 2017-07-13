package com.example.jaggia.wheresmystuff9.model.user_system;

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
    private int id;
    /**The users real life name*/
    private String name;
    /**unique username*/
    private String username;
    /**the user's password*/
    private String password;
    /*ErrorCode returned when creating the user*/
    //private ErrorCode _errorCode;
    /**Boolean to tell if user is an Admin*/
    private boolean admin;
    /**String to hold the users's email*/
    private String email;

    /**no parameter constructor for controller*/
    public User(){
        this("default name" , "default username" , "default password");
    }

    /** makes a new User
     *
     * @param name the user's real life name
     * @param username the user's public name
     * @param password the user's password
     * _id is a unique identifier for the user
     */
    public User(String name, String username, String password){

        this.id = Next_ID++;
        this.name = name;
        this.username = username;
        this.password = password;
        this.admin = false;
        this.email = "jamesg166@comcast.net";
    }
    /** makes a new User
     *
     * @param name the user's real life name
     * @param username the user's public name
     * @param password the user's password
     * @param isAdmin boolean controlling whether the user is an Admin
     * _id is a unique identifier for the user
     */
    public User(String name, String username, String password, boolean isAdmin){
        this(name, username,password);
        this.admin = isAdmin;
        this.email = "jamesg166@comcast.net";
    }
    public User(String name, String username, String password, boolean isAdmin, String email){
        this(name, username, password, isAdmin);
        this.email = email;
    }


    /** ********************************************
     * the getters and setters
     */
    public int getId(){
        return this.id;
    }

    /**
     *
     * @return the user's username
     */
    public String getUsername(){
        return this.username;
    }
    /**
     *
     * @return the user's password
     */
    public String getPassword(){
        return this.password;
    }
    /**
     *
     * @return the user's real name
     */
    public String getName() {return this.name;}
    //public ErrorCode getErrorCode() {return _errorCode; }
    public String getEmail() {return this.email;}
    public void setEmail(String email) {this.email = email;}
    public boolean getAdmin(){return this.admin;}
    public void setAdmin(boolean isAdmin){this.admin = isAdmin;}
    /**
     *
     * @param username name to set as the user's username
     */
    public void setUsername(String username){
        this.username = username;
    }
    /**
     *
     * @param password name to set as the user's password
     */
    public void setPassword(String password){
        this.password = password;
    }
    /**
     *
     * @param name name to set as the user's real name
     */
    public void setName(String name){this.name = name; }


    /* ********************************************
     * Overridden methods
     * toString(), describeContents(), writeToParcel()
     *
     */
    //prints the user's username
    @Override
    public String toString(){
        return this.username;
    }
    //needed to implement parcel
    @Override
    public int describeContents(){
        return 0;
    }
    //method for parcel
    @Override
    public void writeToParcel(Parcel dest, int flags){
        dest.writeString(name);
        dest.writeString(username);
        dest.writeString(password);
        dest.writeString(email);
        dest.writeSerializable(admin);
        dest.writeInt(id);
    }

    //method for parcel
    private User(Parcel in){
        name = in.readString();
        username = in.readString();
        password = in.readString();
        email = in.readString();
        admin = (boolean) in.readSerializable();
        id = in.readInt();
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
