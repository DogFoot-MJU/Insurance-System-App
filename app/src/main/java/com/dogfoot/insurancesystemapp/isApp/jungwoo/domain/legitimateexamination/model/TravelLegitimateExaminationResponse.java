package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.legitimateexamination.model;

import com.dogfoot.insurancesystemapp.isApp.constants.Constant;
import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class TravelLegitimateExaminationResponse {

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
    @SerializedName("safety_rank")
    private Constant.SafetyRank safety_rank;
}
