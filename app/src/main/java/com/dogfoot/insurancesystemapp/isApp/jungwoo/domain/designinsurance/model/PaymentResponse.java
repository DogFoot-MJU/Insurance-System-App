package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class PaymentResponse {

    @SerializedName("payment")
    private Long payment;
}
