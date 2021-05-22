package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model;

import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant.DevelopmentState;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CarDesignInsuranceResponse {

    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("payment")
    private Long payment;
    @SerializedName("state")
    private DevelopmentState state;
    @SerializedName("expiration_date")
    private int expiration_date;
    @SerializedName("car_price")
    private Long car_price;
    @SerializedName("car_release_date")
    private String car_release_date;
    @SerializedName("driving_distance")
    private Long driving_distance;
}
