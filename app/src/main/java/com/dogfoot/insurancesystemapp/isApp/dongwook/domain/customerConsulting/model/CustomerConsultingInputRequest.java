package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerConsultingInputRequest {


    @SerializedName("title")
    private String title;

    @SerializedName("contents")
    private String contents;



}
