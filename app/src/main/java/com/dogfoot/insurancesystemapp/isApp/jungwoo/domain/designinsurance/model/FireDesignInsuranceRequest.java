
package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FireDesignInsuranceRequest {
    @SerializedName("id")
    private int id;
    @SerializedName("building_price")
    private String building_price;
    @SerializedName("construction_date")
    private String construction_date;
    @SerializedName("number_of_floors")
    private String number_of_floors;
    @SerializedName("site_area")
    private String site_area;
}
