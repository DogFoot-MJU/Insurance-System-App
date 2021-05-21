
package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model;

import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant.DevelopmentState;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class FireDesignInsuranceResponse {

    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("payment")
    private Long payment;
    @SerializedName("state")
    private DevelopmentState state;
    @SerializedName("building_price")
    private Long building_price;
    @SerializedName("construction_date")
    private String construction_date;
    @SerializedName("site_area")
    private Long site_area;
    @SerializedName("number_of_floors")
    private Integer number_of_floors;
}
