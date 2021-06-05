package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerCompensation.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerContractListResponse {

    @SerializedName("contract_id")
    private Long contract_id;

    @SerializedName("insurance_name")
    private String insurance_name;

    @SerializedName("approve_sate")
    private String approve_state;

    @SerializedName("expiration_date")
    private String expiration_date;
}
