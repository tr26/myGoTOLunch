package com.example.mygotolunch.activities.models;

import androidx.annotation.Nullable;

public class User {
    private String uid;
    private String username;
    @Nullable
    private Boolean isArrived;
    private Boolean hasMadeHisChoice;
    @Nullable
    private String urlPicture;
    @Nullable
    private String idRestaurantChoosen;

    public User(){ }

    public User(String uid, String username, @Nullable String urlPicture, @Nullable String idRestaurantChoosen) {
        this.uid = uid;
        this.username = username;
        this.urlPicture = urlPicture;
        this.hasMadeHisChoice = false;
        this.idRestaurantChoosen = idRestaurantChoosen;
    }

    @Nullable
    public String getIdRestaurantChoosen() {
        return idRestaurantChoosen;
    }

    public void setIdRestaurantChoosen(@Nullable String idRestaurantChoosen) {
        this.idRestaurantChoosen = idRestaurantChoosen;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Nullable
    public Boolean getArrived() {
        return isArrived;
    }

    public void setArrived(@Nullable Boolean arrived) {
        isArrived = arrived;
    }

    public Boolean getHasMadeHisChoice() {
        return hasMadeHisChoice;
    }

    public void setHasMadeHisChoice(Boolean hasMadeHisChoice) {
        this.hasMadeHisChoice = hasMadeHisChoice;
    }

    @Nullable
    public String getUrlPicture() {
        return urlPicture;
    }

    public void setUrlPicture(@Nullable String urlPicture) {
        this.urlPicture = urlPicture;
    }
}
