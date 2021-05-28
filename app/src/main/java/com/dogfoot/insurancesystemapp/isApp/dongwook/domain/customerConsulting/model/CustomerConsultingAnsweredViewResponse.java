package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model;


import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerConsultingAnsweredViewResponse {

    @SerializedName("id")
    private Long id;

    @SerializedName("title")
    private String title;

    @SerializedName("contents")
    private String contents;

    @SerializedName("writer")
    private String writer;

    @SerializedName("creation_date")
    private String creation_date;
}
