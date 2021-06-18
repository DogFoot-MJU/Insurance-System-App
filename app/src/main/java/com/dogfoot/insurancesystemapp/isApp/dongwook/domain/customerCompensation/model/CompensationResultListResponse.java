package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompensationResultListResponse {
    @SerializedName("accident_id")
    private Long accident_id;

    @SerializedName("contract_id")
    private Long contract_id;

    @SerializedName("insurance_name")
    private String insurance_name;

    @SerializedName("user_name")
    private String user_name;

    @SerializedName("email")
    private String email;

    @SerializedName("state")
    private String state;

    @SerializedName("accident_apply_date")
    private String accident_apply_date;
}
