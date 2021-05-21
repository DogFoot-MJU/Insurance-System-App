
package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TravelDesignInsuranceRequest {

    @SerializedName("id")
    private int id;
    @SerializedName("safety_rank")
    private String safety_rank;
}
