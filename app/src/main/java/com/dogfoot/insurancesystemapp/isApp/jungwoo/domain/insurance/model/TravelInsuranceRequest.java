package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model;

import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TravelInsuranceRequest {

    @SerializedName("insurance_id")
    private Long insurance_id;
    @SerializedName("customer_physical")
    private String customer_physical;
    @SerializedName("customer_economical")
    private String customer_economical;
    @SerializedName("customer_environmental")
    private String customer_environmental;
    @SerializedName("safety_rank")
    private Constant.SafetyRank safetyRank;

}
