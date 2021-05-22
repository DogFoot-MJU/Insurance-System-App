package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DriverInsuranceResponse {

    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
}
