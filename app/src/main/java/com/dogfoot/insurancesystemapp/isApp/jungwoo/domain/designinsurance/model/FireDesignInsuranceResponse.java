
package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FireDesignInsuranceResponse {

    @SerializedName("id")
    private int id;
    @SerializedName("name")
    private String name;
    @SerializedName("payment")
    private String payment;
    @SerializedName("state")
    private String state;
    @SerializedName("building_price")
    private String building_price;
    @SerializedName("construction_date")
    private String construction_date;
    @SerializedName("site_area")
    private String site_area;
    @SerializedName("number_of_floors")
    private String number_of_floors;
}
