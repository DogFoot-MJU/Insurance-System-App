package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CarInsuranceResponse {

    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("payment")
    private Long payment;
    @SerializedName("physical")
    private String physical;
    @SerializedName("economical")
    private String economical;
    @SerializedName("environmental")
    private String environmental;
    @SerializedName("car_price")
    private Long car_price;
    @SerializedName("car_release_date")
    private String car_release_date;
    @SerializedName("driving_distance")
    private Long driving_distance;
    @SerializedName("available_sale")
    private boolean available_sale;
}
