package com.meatshop.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class TwitterStatusDTO {
    @SerializedName("statuses")
    private List<TwitterStatus> statusList;

    public List<TwitterStatus> getStatusList() {
        return statusList;
    }
}
