package com.example.jaggia.wheresmystuff9.model.item_system;

import android.location.Location;
import android.os.Bundle;
import android.os.Parcel;

public class MyLocation extends Location {
    public MyLocation(){
        super("default location");
    }
    public MyLocation(String s){
        super(s);
    }

    public MyLocation(Location l) {
        super(l);
    }

    @Override
    public void set(Location l) {
        super.set(l);
    }

    @Override
    public String getProvider() {
        return super.getProvider();
    }

    @Override
    public void setProvider(String provider) {
        super.setProvider(provider);
    }

    @Override
    public long getTime() {
        return super.getTime();
    }

    @Override
    public void setTime(long time) {
        super.setTime(time);
    }

    @Override
    public long getElapsedRealtimeNanos() {
        return super.getElapsedRealtimeNanos();
    }

    @Override
    public void setElapsedRealtimeNanos(long time) {
        super.setElapsedRealtimeNanos(time);
    }

    @Override
    public double getLatitude() {
        return super.getLatitude();
    }

    @Override
    public void setLatitude(double latitude) {
        super.setLatitude(latitude);
    }

    @Override
    public double getLongitude() {
        return super.getLongitude();
    }

    @Override
    public void setLongitude(double longitude) {
        super.setLongitude(longitude);
    }

    @Override
    public boolean hasAltitude() {
        return super.hasAltitude();
    }

    @Override
    public double getAltitude() {
        return super.getAltitude();
    }

    @Override
    public void setAltitude(double altitude) {
        super.setAltitude(altitude);
    }

    @Override
    public float getSpeed() {
        return super.getSpeed();
    }

    @Override
    public void setSpeed(float speed) {
        super.setSpeed(speed);
    }

    @Override
    public float getBearing() {
        return super.getBearing();
    }

    @Override
    public void setBearing(float bearing) {
        super.setBearing(bearing);
    }

    @Override
    public float getAccuracy() {
        return super.getAccuracy();
    }

    @Override
    public void setAccuracy(float accuracy) {
        super.setAccuracy(accuracy);
    }

    @Override
    public Bundle getExtras() {
        return super.getExtras();
    }

    @Override
    public void setExtras(Bundle extras) {
        super.setExtras(extras);
    }

    @Override
    public int describeContents() {
        return super.describeContents();
    }

    @Override
    public void writeToParcel(Parcel parcel, int flags) {
        super.writeToParcel(parcel, flags);
    }

    @Override
    public boolean isFromMockProvider() {
        return super.isFromMockProvider();
    }
}
