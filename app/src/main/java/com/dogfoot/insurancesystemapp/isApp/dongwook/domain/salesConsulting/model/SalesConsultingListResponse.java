package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.salesConsulting.model;


import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SalesConsultingListResponse {

    @SerializedName("id")
    private Long id;

    @SerializedName("title")
    private String title;

    @SerializedName("writer")
    private String writer;

    @SerializedName("state")
    private String state;

    @SerializedName("creation_date")
    private String creation_date;
}
