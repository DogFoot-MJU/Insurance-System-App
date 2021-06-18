package com.dogfoot.insurancesystemapp.isApp.dongwook.domain.customerConsulting.model;


import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CustomerConsultingListResponse {

    @SerializedName("id")
    private Long id;

    @SerializedName("title")
    private String title;

    @SerializedName("writer")
    private String writer;

    @SerializedName("state")
    private String state;

    @SerializedName("answer_id")
    private String answer_id;

    @SerializedName("creation_date")
    private String creation_date;
}
