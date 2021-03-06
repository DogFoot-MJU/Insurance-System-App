package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model;

import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant.DevelopmentState;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DriverPlanInsuranceResponse {

    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("payment")
    private Long payment;
    @SerializedName("expiration_date")
    private int expiration_date;
    @SerializedName("state")
    private DevelopmentState state;
}
