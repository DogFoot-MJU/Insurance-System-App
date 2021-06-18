
package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model;

import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FireDesignInsuranceRequest {
    @SerializedName("id")
    private Long id;
    @SerializedName("building_price")
    private Long building_price;
    @SerializedName("construction_date")
    private String construction_date;
    @SerializedName("number_of_floors")
    private Integer number_of_floors;
    @SerializedName("site_area")
    private Long site_area;
}
