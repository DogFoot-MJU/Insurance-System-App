
package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TravelDesignInsuranceResponse {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("payment")
    private String payment;
    @SerializedName("state")
    private String state;
    @SerializedName("safety_rank")
    private String safety_rank;
}
