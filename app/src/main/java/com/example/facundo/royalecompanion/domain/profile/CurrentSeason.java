
package com.example.facundo.royalecompanion.domain.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CurrentSeason {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("trophies")
    @Expose
    private Integer trophies;
    @SerializedName("bestTrophies")
    @Expose
    private Integer bestTrophies;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getTrophies() {
        return trophies;
    }

    public void setTrophies(Integer trophies) {
        this.trophies = trophies;
    }

    public Integer getBestTrophies() {
        return bestTrophies;
    }

    public void setBestTrophies(Integer bestTrophies) {
        this.bestTrophies = bestTrophies;
    }

}
