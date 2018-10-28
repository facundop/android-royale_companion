
package com.example.facundo.royalecompanion.domain.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentFavouriteCard {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("maxLevel")
    @Expose
    private Integer maxLevel;
    @SerializedName("iconUrls")
    @Expose
    private IconUrls iconUrls;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(Integer maxLevel) {
        this.maxLevel = maxLevel;
    }

    public IconUrls getIconUrls() {
        return iconUrls;
    }

    public void setIconUrls(IconUrls iconUrls) {
        this.iconUrls = iconUrls;
    }

}
