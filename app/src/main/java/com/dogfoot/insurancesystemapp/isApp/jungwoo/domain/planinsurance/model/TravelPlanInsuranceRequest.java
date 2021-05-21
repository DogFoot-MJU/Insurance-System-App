package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TravelPlanInsuranceRequest {

    @SerializedName("name")
    private String name;
    @SerializedName("payment")
    private Long payment;
}
