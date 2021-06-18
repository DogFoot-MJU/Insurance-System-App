package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerConsultingInputResponse {

    @SerializedName("response_time")
    private String response_time;

    @SerializedName("message")
    private String message;
}
