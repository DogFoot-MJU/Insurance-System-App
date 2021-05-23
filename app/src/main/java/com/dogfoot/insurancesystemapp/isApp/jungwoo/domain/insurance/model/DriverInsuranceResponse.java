package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.insurance.model;

import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.dogfoot.insurancesystemapp.isApp.constants.Constant.DriverLicence;
import com.google.gson.annotations.SerializedName;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DriverInsuranceResponse {

    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("payment")
    private final Long payment;
    @SerializedName("physical")
    private final String physical;
    @SerializedName("economical")
    private final String economical;
    @SerializedName("environmental")
    private final String environmental;
    @SerializedName("date_of_license_acquisition")
    private final String date_of_license_acquisition;
    @SerializedName("driver_licence")
    private final DriverLicence driver_licence;
    @SerializedName("available_sale")
    private final boolean available_sale;

}
