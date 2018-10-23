package com.example.facundo.royalecompanion;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class UpcomingChestsResponse {

    @SerializedName("items")
    @Expose
    private List<ChestItem> items = null;

    public List<ChestItem> getItems() {
        return items;
    }

    public void setItems(List<ChestItem> items) {
        this.items = items;
    }

}