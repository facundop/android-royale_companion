
package com.example.facundo.royalecompanion.domain.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LeagueStatistics {

    @SerializedName("currentSeason")
    @Expose
    private CurrentSeason currentSeason;
    @SerializedName("previousSeason")
    @Expose
    private PreviousSeason previousSeason;
    @SerializedName("bestSeason")
    @Expose
    private BestSeason bestSeason;

    public CurrentSeason getCurrentSeason() {
        return currentSeason;
    }

    public void setCurrentSeason(CurrentSeason currentSeason) {
        this.currentSeason = currentSeason;
    }

    public PreviousSeason getPreviousSeason() {
        return previousSeason;
    }

    public void setPreviousSeason(PreviousSeason previousSeason) {
        this.previousSeason = previousSeason;
    }

    public BestSeason getBestSeason() {
        return bestSeason;
    }

    public void setBestSeason(BestSeason bestSeason) {
        this.bestSeason = bestSeason;
    }

}
