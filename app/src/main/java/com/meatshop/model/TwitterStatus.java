package com.meatshop.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class TwitterStatus implements Parcelable {
    @SerializedName("created_at")
    private String dateCreated;
    private String text;
    private TwitterUser user;

    protected TwitterStatus(Parcel in) {
        dateCreated = in.readString();
        text = in.readString();
    }

    public static final Creator<TwitterStatus> CREATOR = new Creator<TwitterStatus>() {
        @Override
        public TwitterStatus createFromParcel(Parcel in) {
            return new TwitterStatus(in);
        }

        @Override
        public TwitterStatus[] newArray(int size) {
            return new TwitterStatus[size];
        }
    };

    public String getDateCreated() {
        return dateCreated;
    }

    public String getText() {
        return text;
    }

    public TwitterUser getUser() {
        return user;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(dateCreated);
        parcel.writeString(text);
    }

    public static class TwitterUser {
        private String name, location;
        @SerializedName("screen_name")
        private String screenName;
        @SerializedName("profile_image_url")
        private String profileImageURL;

        public String getProfileImageURL() {
            return profileImageURL;
        }

        public String getScreenName() {
            return screenName;
        }

        public String getLocation() {
            return location;
        }

        public String getName() {
            return name;
        }
    }
}
