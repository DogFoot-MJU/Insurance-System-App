package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesConsulting.model;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalesConsultingInputRequest {
    @SerializedName("consulting_id")
    private Long consulting_id;

    @SerializedName("title")
    private String title;

    @SerializedName("contents")
    private String contents;



}
