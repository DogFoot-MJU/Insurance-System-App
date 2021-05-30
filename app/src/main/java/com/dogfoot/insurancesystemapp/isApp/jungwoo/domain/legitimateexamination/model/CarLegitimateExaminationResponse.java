package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.model;

import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class CarLegitimateExaminationResponse {


    @SerializedName("id")
    private Long id;
    @SerializedName("user_name")
    private String user_name;
    @SerializedName("email")
    private String email;
    @SerializedName("insurance_id")
    private Long insurance_id;
    @SerializedName("insurance_name")
    private String insurance_name;
    @SerializedName("customer_physical")
    private String customer_physical;
    @SerializedName("customer_economical")
    private String customer_economical;
    @SerializedName("customer_environmental")
    private String customer_environmental;
    @SerializedName("calculated_payment")
    private Long calculated_payment;
    @SerializedName("expiration_date")
    private String expiration_date;
    @SerializedName("uw_due_process_type")
    private Constant.UwDueProcessType uw_due_process_type;
    @SerializedName("customer_car_price")
    private Long customer_car_price;
    @SerializedName("customer_car_release_date")
    private String customer_car_release_date;
    @SerializedName("customer_driving_distance")
    private Long customer_driving_distance;

}
