package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model;


import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CarDesignInsuranceRequest {

    @SerializedName("id")
    private int id;
    @SerializedName("car_price")
    private String car_price;
    @SerializedName("car_release_date")
    private String car_release_date;
    @SerializedName("driving_distance")
    private String driving_distance;
}
