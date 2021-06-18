package com.dogfoot.insurancesystemapp.isApp.jungwoo.domain.capacitypolicy.model;

import com.google.gson.annotations.SerializedName;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CapacityPolicyUpdateRequest {

    @SerializedName("id")
    private Long id;
    @SerializedName("name")
    private String name;
    @SerializedName("physical")
    private String physical;
    @SerializedName("economical")
    private String economical;
    @SerializedName("environmental")
    private String environmental;
}
