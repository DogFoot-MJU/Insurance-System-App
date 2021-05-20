package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.planinsurance.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Pagination<T> {

    @SerializedName("total_page")
    private int total_page;
    @SerializedName("current_page")
    private int current_page;
    @SerializedName("total_elements")
    private long total_elements;
    @SerializedName("current_elements")
    private int current_elements;
    @SerializedName("has_previous")
    private boolean has_previous;
    @SerializedName("has_next")
    private boolean has_next;
    @SerializedName("data")
    private T data;
    @SerializedName("last")
    private boolean last;

}
