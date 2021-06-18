
package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.designinsurance.model;

import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant.DriverLicence;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DriverDesignInsuranceRequest {

    @SerializedName("id")
    private Long id;
    @SerializedName("date_of_license_acquisition")
    private String date_of_license_acquisition;
    @SerializedName("driver_licence")
    private DriverLicence driver_license;
}
