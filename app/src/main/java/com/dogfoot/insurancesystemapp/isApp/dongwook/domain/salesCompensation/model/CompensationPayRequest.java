package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesCompensation.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompensationPayRequest {

    @SerializedName("accident_id")
    private Long accident_id;

    @SerializedName("compensation_amount")
    private Long compensation_amount;

}
