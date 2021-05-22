package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FirePlanInsuranceRequest {

    @SerializedName("name")
    private String name;
    @SerializedName("payment")
    private Long payment;
    @SerializedName("expiration_date")
    private int expiration_date;
}
