
package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DriverDesignInsuranceRequest {

    @SerializedName("id")
    private int id;
    @SerializedName("date_of_license_acquisition")
    private String date_of_license_acquisition;
    @SerializedName("driver_license")
    private String driver_license;
}
