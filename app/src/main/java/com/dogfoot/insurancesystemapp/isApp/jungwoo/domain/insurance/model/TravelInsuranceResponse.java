package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model;

import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;


@Getter
@AllArgsConstructor
public class TravelInsuranceResponse {

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
    @SerializedName("safety_rank")
    private Constant.SafetyRank safetyRank;
    @SerializedName("available_sale")
    private final boolean available_sale;
}
