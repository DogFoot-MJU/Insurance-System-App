package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model;


import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FireInsuranceResponse {

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
    private final String environmental;
    @SerializedName("site_area")
    private Long site_area;
    @SerializedName("building_price")
    private Long building_price;
    @SerializedName("number_of_floors")
    private int number_of_floors;
    @SerializedName("construction_date")
    private String construction_date;
    @SerializedName("available_sale")
    private final boolean available_sale;
}
