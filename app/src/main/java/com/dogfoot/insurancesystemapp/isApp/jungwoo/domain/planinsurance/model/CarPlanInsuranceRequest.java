package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CarPlanInsuranceRequest {

    @SerializedName("name")
    private String name;
    @SerializedName("payment")
    private String payment;
}
