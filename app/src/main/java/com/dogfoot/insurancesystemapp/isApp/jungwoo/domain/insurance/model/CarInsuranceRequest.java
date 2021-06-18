package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CarInsuranceRequest {

    @SerializedName("insurance_id")
    private Long insurance_id;
    @SerializedName("customer_physical")
    private String customer_physical;
    @SerializedName("customer_economical")
    private String customer_economical;
    @SerializedName("customer_environmental")
    private String customer_environmental;
    @SerializedName("customer_car_price")
    private Long customer_car_price;
    @SerializedName("customer_car_release_date")
    private String customer_car_release_date;
    @SerializedName("customer_driving_distance")
    private Long customer_driving_distance;
}
