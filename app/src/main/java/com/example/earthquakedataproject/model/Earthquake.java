package com.example.earthquakedataproject.model;

public class Earthquake {

    public String description;
    public String location;
    public String latLong;
    public String depth;
    public Double magnitude;
    public String pubDate;

    public Earthquake() {
        description = "";
        location = "";
        latLong = "";
        depth = "";
        magnitude = 0.00;
        pubDate = "";
    }

    public Earthquake(String location, Double magnitude){
        this.location = location;
        this.magnitude = magnitude;
    }

    public Earthquake(String location, Double magnitude, String latLong, String depth, String pubDate) {
        this.pubDate = pubDate;
        this.magnitude = magnitude;
        this.location = location;
        this.depth = depth;
        this.latLong = latLong;
    }



    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLatLong() {
        return latLong;
    }

    public void setLatLong(String latLong) {
        this.latLong = latLong;
    }

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public Double getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(Double magnitude) {
        this.magnitude = magnitude;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    @Override
    public String toString() {
        return "Earthquake{" +
                "description='" + description + '\'' +
                ", location='" + location + '\'' +
                ", latLong=" + latLong +
                ", depth='" + depth + '\'' +
                ", magnitude=" + magnitude +
                ", pubDate='" + pubDate + '\'' +
                '}';
    }
}
