
package com.example.facundo.royalecompanion.domain.profile;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Achievement {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("stars")
    @Expose
    private Integer stars;
    @SerializedName("value")
    @Expose
    private Integer value;
    @SerializedName("target")
    @Expose
    private Integer target;
    @SerializedName("info")
    @Expose
    private String info;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStars() {
        return stars;
    }

    public void setStars(Integer stars) {
        this.stars = stars;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public Integer getTarget() {
        return target;
    }

    public void setTarget(Integer target) {
        this.target = target;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

}
