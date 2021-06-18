package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model;


import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerConsultingViewResponse {

    @SerializedName("id")
    private Long id;

    @SerializedName("title")
    private String title;

    @SerializedName("contents")
    private String contents;

    @SerializedName("writer")
    private String writer;

    @SerializedName("state")
    private String state;

    @SerializedName("creation_date")
    private String creation_date;
}
