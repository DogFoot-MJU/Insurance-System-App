package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CarPlanInsuranceResponse {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("payment")
    private String payment;
    @SerializedName("expiration_date")
    private int expiration_date;
    @SerializedName("state")
    private String state;
}
