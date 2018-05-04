package com.example.buyukdemircioglug.landslidealert.infoform;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

public class LandslideInfo implements Parcelable {

    private String username;
    private String name;
    private String surname;
    private String eventDate;
    private String eventTime;
    private String eventLocation;
    private boolean anyInjuryOrDeath;
    private String damageDescription;
    private String otherObservations;

    public LandslideInfo(@NonNull String username,
                         @NonNull String name,
                         @NonNull String surname,
                         @NonNull String eventDate,
                         @NonNull String eventTime,
                         @NonNull String eventLocation,
                         boolean anyInjuryOrDeath,
                         @NonNull String damageDescription,
                         @NonNull String otherObservations) {

        this.username = username;
        this.name = name;
        this.surname = surname;
        this.eventDate = eventDate;
        this.eventTime = eventTime;
        this.eventLocation = eventLocation;
        this.anyInjuryOrDeath = anyInjuryOrDeath;
        this.damageDescription = damageDescription;
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

    public String getEventDate() {
        return eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    public boolean isAnyInjuryOrDeath() {
        return anyInjuryOrDeath;
    }

    public String getDamageDescription() {
        return damageDescription;
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
        dest.writeString(this.eventDate);
        dest.writeString(this.eventTime);
        dest.writeString(this.eventLocation);
        dest.writeByte(this.anyInjuryOrDeath ? (byte) 1 : (byte) 0);
        dest.writeString(this.damageDescription);
        dest.writeString(this.otherObservations);
    }

    protected LandslideInfo(Parcel in) {
        this.username = in.readString();
        this.name = in.readString();
        this.surname = in.readString();
        this.eventDate = in.readString();
        this.eventTime = in.readString();
        this.eventLocation = in.readString();
        this.anyInjuryOrDeath = in.readByte() != 0;
        this.damageDescription = in.readString();
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
