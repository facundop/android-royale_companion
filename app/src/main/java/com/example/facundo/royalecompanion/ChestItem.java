package com.example.facundo.royalecompanion;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ChestItem {

    @SerializedName("index")
    @Expose
    private Integer index;
    @SerializedName("name")
    @Expose
    private String name;

    public Integer getIndex() {
        return index;
    }

    public void setIndex(Integer index) {
        this.index = index;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}