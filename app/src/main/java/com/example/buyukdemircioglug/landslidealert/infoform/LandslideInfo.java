package com.example.buyukdemircioglug.landslidealert.infoform;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class LandslideInfo implements Parcelable {

    private String username;
    private String name;
    private String surname;
    private String eventLocation;
    private String eventTime;
    private String damageDesctiption;
    private String otherObservations;

    public LandslideInfo(@NonNull String username,
                         @NonNull String name,
                         @NonNull String surname,
                         @NonNull String eventLocation,
                         @NonNull String eventTime,
                         @NonNull String damageDesctiption,
                         @NonNull String otherObservations) {

        this.username = username;
        this.name = name;
        this.surname = surname;
        this.eventLocation = eventLocation;
        this.eventTime = eventTime;
        this.damageDesctiption = damageDesctiption;
        this.otherObservations = otherObservations;

    }

    public String getUsername() {
        return username;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getDamageDesctiption() {
        return damageDesctiption;
    }

    public String getOtherObservations() {
        return otherObservations;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.username);
        dest.writeString(this.name);
        dest.writeString(this.surname);
        dest.writeString(this.eventLocation);
        dest.writeString(this.eventTime);
        dest.writeString(this.damageDesctiption);
        dest.writeString(this.otherObservations);
    }

    protected LandslideInfo(Parcel in) {
        this.username = in.readString();
        this.name = in.readString();
        this.surname = in.readString();
        this.eventLocation = in.readString();
        this.eventTime = in.readString();
        this.damageDesctiption = in.readString();
        this.otherObservations = in.readString();
    }

    public static final Creator<LandslideInfo> CREATOR = new Creator<LandslideInfo>() {
        @Override
        public LandslideInfo createFromParcel(Parcel source) {
            return new LandslideInfo(source);
        }

        @Override
        public LandslideInfo[] newArray(int size) {
            return new LandslideInfo[size];
        }
    };
}
