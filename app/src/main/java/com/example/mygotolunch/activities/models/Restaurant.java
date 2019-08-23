package com.example.mygotolunch.activities.models;

import android.location.Location;

import androidx.annotation.Nullable;

public class Restaurant {
    private String name;
    private Location location;
    private int rating;
    @Nullable
    private long phoneNumber;
    private int nbOfLike;

    public Restaurant(String name, Location location, int rating, int nbOfLike) {
        this.name = name;
        this.location = location;
        this.rating = rating;
        this.nbOfLike = nbOfLike;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getRating() {
        return rating;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public int getNbOfLike() {
        return nbOfLike;
    }

    public void setNbOfLike(int nbOfLike) {
        this.nbOfLike = nbOfLike;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}
