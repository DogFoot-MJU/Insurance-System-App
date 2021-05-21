
package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model;

import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant.DevelopmentState;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant.DriverLicence;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DriverDesignInsuranceResponse {

    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("payment")
    private Long payment;
    @SerializedName("state")
    private DevelopmentState state;
    @SerializedName("date_of_license_acquisition")
    private String date_of_license_acquisition;
    @SerializedName("driver_license")
    private DriverLicence driver_license;
}
