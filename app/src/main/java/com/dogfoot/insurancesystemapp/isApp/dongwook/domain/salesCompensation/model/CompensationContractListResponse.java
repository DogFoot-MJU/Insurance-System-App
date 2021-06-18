package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompensationContractListResponse {

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
