package com.example.carsapp;

import com.google.gson.annotations.SerializedName;

public class CarAttributes {
    @SerializedName("num_models")
    private int num_model;
    @SerializedName("img_url")
    private String img_url;
    @SerializedName("max_car_id")
    private int max_car_id;
    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("avg_horsepower")
    private Double avg_hp;
    @SerializedName("avg_price")
    private Double avg_price;

    public int getNum_model() {
        return num_model;
    }

    public String getImg_url() {
        return img_url;
    }

    public int getMax_car_id() {
        return max_car_id;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getAvg_hp() {
        return avg_hp;
    }

    public Double getAvg_price() {
        return avg_price;
    }
}
