package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model;

import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FireInsuranceRequest {

    @SerializedName("insurance_id")
    private Long insurance_id;
    @SerializedName("customer_physical")
    private String customer_physical;
    @SerializedName("customer_economical")
    private String customer_economical;
    @SerializedName("customer_environmental")
    private String customer_environmental;
    @SerializedName("site_area")
    private Long site_area;
    @SerializedName("building_price")
    private Long building_price;
    @SerializedName("number_of_floors")
    private int number_of_floors;
    @SerializedName("construction_date")
    private String construction_date;
}
